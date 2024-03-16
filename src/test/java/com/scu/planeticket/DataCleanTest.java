//package com.scu.planeticket;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.scu.planeticket.mapper.FlightInfoMapper;
//import com.scu.planeticket.pojo.entity.FlightInfo;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @Program: planeticket
// * @Description:
// * @Author: MOATSH
// * @Create: 2024-03-15 14:39
// **/
//
//@SpringBootTest
//public class DataCleanTest {
//    @Resource
//    private FlightInfoMapper flightInfoMapper;
//
//
//    @Test
//    public void cleanArrivalDate() {
//        List<FlightInfo> flightInfos = flightInfoMapper.selectList(null);
//        flightInfos.forEach(item -> {
//            String arrivalDate = item.getArrivalDate();
//            String[] split = arrivalDate.split("\\|\\|");
//            item.setArrivalDate(split[split.length - 1]);
//            flightInfoMapper.update(item, new QueryWrapper<FlightInfo>().eq("flight_id", item.getFlightId()));
//        });
//    }
//}
