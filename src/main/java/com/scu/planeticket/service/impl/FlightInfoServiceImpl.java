package com.scu.planeticket.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.constants.RedisCacheConstant;
import com.scu.planeticket.mapper.FlightInfoMapper;
import com.scu.planeticket.mapper.PredictPriceInfoMapper;
import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.dto.FlightSearchReqDTO;
import com.scu.planeticket.pojo.dto.FlightSearchRespDTO;
import com.scu.planeticket.pojo.entity.FlightInfo;
import com.scu.planeticket.service.FlightInfoService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Override
    public FlightSearchRespDTO search(FlightSearchReqDTO requestParam) {
        FlightSearchRespDTO respDTO = new FlightSearchRespDTO();
        /*
        1. 找出出发地departureCity的所有机场和目的地destCity的所有机场
        2. departureDate在date时间，且目的地是destCity的机场，出发地是departureCity的机场的所有航班信息
        3. 构建straight
         */
        List<FlightSearchRespDTO.Flight> straightFlights = baseMapper.selectStraight(requestParam);
        List<List<FlightSearchRespDTO.Flight>> structuredFlights = new ArrayList<>();
        straightFlights.forEach(item -> {
            String[] split = item.getDepartureDate().split("\\|\\|");
            item.setDepartureDate(split[0]);
            Duration duration = Duration.parse(item.getTravelDuration());
            item.setTravelDuration(String.format("%d小时%d分钟", duration.toHours(), duration.toMinutes() % 60));
            structuredFlights.add(Collections.singletonList(item));
        });
        respDTO.setStraight(structuredFlights);

        /*
        1. 找出出发地departureCity的所有机场和目的地destCity的所有机场
        2. 找出departure_date在date时间，且目的地是destCity的机场的所有航班信息
        3. 找出departure_date在date时间，且出发地是departureCity的机场的所有航班信息
        4. 如果2中的目的机场和3中的出发机场一致，则保留这条记录（将2和3中的记录合在一个列表中）
        5. 去掉时间上不符合逻辑的记录
        6. 构建结果（unStraight)
         */
        List<List<FlightSearchRespDTO.Flight>> structuredFlights2 = new ArrayList<>();
        List<FlightSearchRespDTO.Flight> unStraight1 = baseMapper.selectUnStraight1(requestParam);
        unStraight1.forEach(item -> {
            String[] split = item.getDepartureDate().split("\\|\\|");
            item.setDepartureDate(split[0]);
            Duration duration = Duration.parse(item.getTravelDuration());
            item.setTravelDuration(String.format("%d小时%d分钟", duration.toHours(), duration.toMinutes() % 60));
        });
        List<FlightSearchRespDTO.Flight> unStraight2 = baseMapper.selectUnStraight2(requestParam);
        unStraight2.forEach(item -> {
            String[] split = item.getDepartureDate().split("\\|\\|");
            item.setDepartureDate(split[0]);
            Duration duration = Duration.parse(item.getTravelDuration());
            item.setTravelDuration(String.format("%d小时%d分钟", duration.toHours(), duration.toMinutes() % 60));
        });
        for (FlightSearchRespDTO.Flight flight : unStraight1) {
            unStraight2.forEach(item -> {
                if (item.getDestAirport().equals(flight.getDepartureAirport()) &&
                        compareDepartureAndArrivalDate(flight.getDepartureDate(), item.getDepartureDate())) {
                    structuredFlights2.add(Arrays.asList(item, flight));
                }
            });
        }
        respDTO.setUnStraight(structuredFlights2);

        return respDTO;
    }

    private Boolean compareDepartureAndArrivalDate(String departureDate, String arrivalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime dateTime1 = OffsetDateTime.parse(departureDate, formatter);
        OffsetDateTime dateTime2 = OffsetDateTime.parse(arrivalDate, formatter);

        return dateTime1.isAfter(dateTime2);
    }
}
