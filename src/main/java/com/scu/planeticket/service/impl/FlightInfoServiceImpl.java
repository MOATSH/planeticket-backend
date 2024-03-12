package com.scu.planeticket.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.constants.RedisCacheConstant;
import com.scu.planeticket.mapper.FlightInfoMapper;
import com.scu.planeticket.mapper.PredictPriceInfoMapper;
import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.entity.FlightInfo;
import com.scu.planeticket.service.FlightInfoService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 航班信息表 服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@Service
public class FlightInfoServiceImpl extends ServiceImpl<FlightInfoMapper, FlightInfo> implements FlightInfoService {
    @Resource
    private PredictPriceInfoMapper predictPriceInfoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public FlightRecommendDestRespDTO recommendDest(FlightRecommendDestReqDTO reqDTO) {
        // 先查询缓存
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(RedisCacheConstant.FLIGHT_RECOMMEND_DEST + reqDTO.toString()))) {
            return JSONUtil.toBean(stringRedisTemplate.opsForValue().get(RedisCacheConstant.FLIGHT_RECOMMEND_DEST + reqDTO), FlightRecommendDestRespDTO.class);
        } else {
            FlightRecommendDestRespDTO respDTO = FlightRecommendDestRespDTO.builder()
                    .mapInfo(predictPriceInfoMapper.selectRecommendDest(reqDTO))
                    .build();

            // 使用HashMap来按照经纬度分组，并找出每组中价格最低的MapInfo
            Map<String, FlightRecommendDestRespDTO.MapInfo> lowestPriceMapInfoByLocation = new HashMap<>();
            respDTO.getMapInfo().forEach(mapInfo -> {
                String key = mapInfo.getLatitude() + ":" + mapInfo.getLongitude();
                lowestPriceMapInfoByLocation.compute(key, (k, currentLowest) -> {
                    if (currentLowest == null) {
                        return mapInfo;
                    } else {
                        return Float.parseFloat(mapInfo.getPrice()) < Float.parseFloat(currentLowest.getPrice()) ? mapInfo : currentLowest;
                    }
                });
            });
            // 从映射中获取所有值来创建新的mapInfo列表
            List<FlightRecommendDestRespDTO.MapInfo> filteredMapInfo = new ArrayList<>(lowestPriceMapInfoByLocation.values());
            // 设置回respDTO
            respDTO.setMapInfo(filteredMapInfo);

            // 设置缓存
            stringRedisTemplate.opsForValue().set(RedisCacheConstant.FLIGHT_RECOMMEND_DEST + reqDTO, JSONUtil.toJsonStr(respDTO), 30, TimeUnit.MINUTES);

            return respDTO;
        }
    }
}
