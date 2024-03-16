package com.scu.planeticket.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.constants.RedisCacheConstant;
import com.scu.planeticket.mapper.FlightInfoMapper;
import com.scu.planeticket.mapper.PredictPriceInfoMapper;
import com.scu.planeticket.mapper.UserInfoMapper;
import com.scu.planeticket.pojo.dto.*;
import com.scu.planeticket.pojo.entity.FlightInfo;
import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.FlightInfoService;
import com.scu.planeticket.utils.TimeAdder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
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
@Slf4j
public class FlightInfoServiceImpl extends ServiceImpl<FlightInfoMapper, FlightInfo> implements FlightInfoService {
    @Resource
    private PredictPriceInfoMapper predictPriceInfoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserInfoMapper userInfoMapper;


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
    public FlightSearchRespDTO search(FlightSearchReqDTO requestParam) throws IOException {
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

        /*
        1. 根据userName找到userId
        2. 将straight存储为flightInfo列表
        3. 将unStraight存储为flightInfo列表
        4. 发送请求到47.115.220.74:5000
        5. 将返回的结果存储为Flight列表
        6. 构建recommend
         */
        List<FlightInfo> recommendRequestList = new ArrayList<>();
        straightFlights.forEach(item -> recommendRequestList.add(baseMapper.selectById(item.getFlightId())));
        structuredFlights2.forEach(item -> {
            FlightInfo flightInfo1 = baseMapper.selectById(item.get(0).getFlightId());
            FlightInfo flightInfo2 = baseMapper.selectById(item.get(1).getFlightId());
            FlightInfo concat = new FlightInfo();
            concat.setFlightId(0L);
            concat.setStartAirport(flightInfo1.getStartAirport());
            concat.setDestAirport(flightInfo2.getDestAirport());
            concat.setTravelDuration(TimeAdder.addIso8601Durations(flightInfo1.getTravelDuration(), flightInfo2.getTravelDuration()));
            concat.setDepartureDate(flightInfo1.getDepartureDate() + "||" + flightInfo2.getDepartureDate());
            concat.setTotalFare(flightInfo1.getTotalFare().add(flightInfo2.getTotalFare()));
            concat.setTotalDistance(flightInfo1.getTotalDistance() + flightInfo2.getTotalDistance());
            concat.setNotStop(false);
            concat.setArrivalDate(flightInfo2.getArrivalDate());
            concat.setSegmentDepartureTime(flightInfo1.getSegmentDepartureTime()+ "||" + flightInfo2.getSegmentDepartureTime());
            concat.setSegmentArrivalTime(flightInfo1.getSegmentArrivalTime() + "||" + flightInfo2.getSegmentArrivalTime());
            concat.setSegmentDistance(flightInfo1.getSegmentDistance() + "||" + flightInfo2.getSegmentDistance());
            recommendRequestList.add(concat);
        });
        //构建请求
        try {
            RecommendReqDTO reqDTO = RecommendReqDTO.builder()
                    .user_id(userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_name", requestParam.getUserName())).getUserId())
                    .candidate_tickets(recommendRequestList)
                    .build();
            CloseableHttpClient aDefault = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://47.115.220.74:5000/evaluate");
            StringEntity postString = new StringEntity(JSONUtil.toJsonStr(reqDTO), "UTF-8");
            post.setEntity(postString);
            post.setHeader("Content-type", "application/json");
            CloseableHttpResponse recommendResponse = aDefault.execute(post);
            String recommendResponseString = EntityUtils.toString(recommendResponse.getEntity());
            List<FlightInfo> recommendResponseList = JSONUtil.toList(JSONUtil.parseArray(recommendResponseString), FlightInfo.class);
            List<List<FlightSearchRespDTO.Flight>> structuredRecommendFlights = new ArrayList<>();
            recommendResponseList.forEach(item -> {
                FlightSearchRespDTO.Flight flight = new FlightSearchRespDTO.Flight();
                flight.setFlightId(item.getFlightId());
                flight.setDepartureCity(item.getStartAirport());
                flight.setDepartureAirport(item.getStartAirport());
                flight.setDestCity(item.getDestAirport());
                flight.setDestAirport(item.getDestAirport());
                flight.setDepartureDate(item.getDepartureDate());
                flight.setTotalFare(item.getTotalFare().toString());
                flight.setTotalDistance(item.getTotalDistance());
                flight.setTravelDuration(item.getTravelDuration());
                flight.setArrivalDate(item.getArrivalDate());
                String travelDuration = flight.getTravelDuration();
                Duration duration = Duration.parse(travelDuration);
                String[] split = item.getSegmentDepartureTime().split("\\|\\|");
                flight.setDepartureDate(split[0]);
                flight.setTravelDuration(String.format("%d小时%d分钟", duration.toHours(), duration.toMinutes() % 60));
                structuredRecommendFlights.add(Collections.singletonList(flight));
            });
            respDTO.setRecommend(structuredRecommendFlights);
        } catch (Exception e) {
            log.error("recommend error", e);
        }

        return respDTO;
    }

    private Boolean compareDepartureAndArrivalDate(String departureDate, String arrivalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime dateTime1 = OffsetDateTime.parse(departureDate, formatter);
        OffsetDateTime dateTime2 = OffsetDateTime.parse(arrivalDate, formatter);

        return dateTime1.isAfter(dateTime2);
    }
}
