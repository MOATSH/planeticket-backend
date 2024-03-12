package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.mapper.AirportInfoMapper;
import com.scu.planeticket.mapper.CityInfoMapper;
import com.scu.planeticket.mapper.PredictPriceInfoMapper;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeRespDTO;
import com.scu.planeticket.pojo.entity.AirportInfo;
import com.scu.planeticket.pojo.entity.CityInfo;
import com.scu.planeticket.pojo.entity.PredictPriceInfo;
import com.scu.planeticket.service.PredictPriceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 航班价格预测信息表 服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@Service
public class PredictPriceInfoServiceImpl extends ServiceImpl<PredictPriceInfoMapper, PredictPriceInfo> implements PredictPriceInfoService {
    @Resource
    private PredictPriceInfoMapper predictPriceInfoMapper;
    @Resource
    private AirportInfoMapper airportInfoMapper;
    @Resource
    private CityInfoMapper cityInfoMapper;


    @Override
    public FlightRecommendTimeRespDTO recommendTime(FlightRecommendTimeReqDTO requestParam) {
        //获取出发地和目的地的城市信息
        CityInfo departureCityInfo = cityInfoMapper.selectOne(new QueryWrapper<CityInfo>().eq("city_name", requestParam.getDepartureCity()));
        CityInfo destCityInfo = cityInfoMapper.selectOne(new QueryWrapper<CityInfo>().eq("city_name", requestParam.getDestCity()));

        //获取出发地和目的地的机场信息
        List<AirportInfo> departureAirportInfoList = airportInfoMapper.selectList(new QueryWrapper<AirportInfo>().eq("city_id", departureCityInfo.getCityId()));
        List<AirportInfo> destAirportInfoList = airportInfoMapper.selectList(new QueryWrapper<AirportInfo>().eq("city_id", destCityInfo.getCityId()));

        //获取从出发地到目的地的所有航班价格和日期信息
        List<PredictPriceInfo> predictPriceInfoList = new ArrayList<>();
        departureAirportInfoList.forEach(departureAirportInfo -> {
            destAirportInfoList.forEach(destAirportInfo -> {
                List<PredictPriceInfo> tempPredictPriceInfoList = predictPriceInfoMapper.selectList(new QueryWrapper<PredictPriceInfo>()
                        .eq("start_airport", departureAirportInfo.getAirportCode())
                        .eq("dest_airport", destAirportInfo.getAirportCode()));
                predictPriceInfoList.addAll(tempPredictPriceInfoList);
            });
        });

        //只保留同一天的最低价格信息
        Map<String, PredictPriceInfo> lowestPricePredictPriceInfoByDate = new HashMap<>();
        predictPriceInfoList.forEach(predictPriceInfo -> {
            String key = predictPriceInfo.getDate().toString();
            lowestPricePredictPriceInfoByDate.compute(key, (k, currentLowest) -> {
                if (currentLowest == null) {
                    return predictPriceInfo;
                } else {
                    return Float.parseFloat(predictPriceInfo.getPrice()) < Float.parseFloat(currentLowest.getPrice()) ? predictPriceInfo : currentLowest;
                }
            });
        });
        List<PredictPriceInfo> filteredPredictPriceInfo = new ArrayList<>(lowestPricePredictPriceInfoByDate.values());
        filteredPredictPriceInfo.sort(Comparator.comparing(PredictPriceInfo::getDate));

        //构建返回结果
        FlightRecommendTimeRespDTO respDTO = FlightRecommendTimeRespDTO.builder()
                .departureCity(requestParam.getDepartureCity())
                .destCity(requestParam.getDestCity())
                .priceList(new ArrayList<>())
                .build();
        filteredPredictPriceInfo.forEach(predictPriceInfo -> {
            respDTO.getPriceList().add(FlightRecommendTimeRespDTO.PriceList.builder()
                    .date(predictPriceInfo.getDate().getYear() + "-" + predictPriceInfo.getDate().getMonthValue() + "-" + predictPriceInfo.getDate().getDayOfMonth())
                    .price(predictPriceInfo.getPrice())
                    .build());
        });

        return respDTO;
    }
}
