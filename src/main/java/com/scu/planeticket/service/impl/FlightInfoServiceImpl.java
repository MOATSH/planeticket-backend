package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.mapper.FlightInfoMapper;
import com.scu.planeticket.mapper.PredictPriceInfoMapper;
import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.entity.FlightInfo;
import com.scu.planeticket.service.FlightInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public FlightRecommendDestRespDTO recommendDest(FlightRecommendDestReqDTO reqDTO) {
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

        return respDTO;
    }
}
