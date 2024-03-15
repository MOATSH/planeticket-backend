package com.scu.planeticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu.planeticket.pojo.dto.FlightSearchReqDTO;
import com.scu.planeticket.pojo.dto.FlightSearchRespDTO;
import com.scu.planeticket.pojo.entity.FlightInfo;

import java.util.List;

/**
 * <p>
 * 航班信息表 Mapper 接口
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
public interface FlightInfoMapper extends BaseMapper<FlightInfo> {

    List<FlightSearchRespDTO.Flight> selectStraight(FlightSearchReqDTO requestParam);

    //找出departure_date在date时间，且目的地是destCity的机场的所有航班信息
    List<FlightSearchRespDTO.Flight> selectUnStraight1(FlightSearchReqDTO requestParam);

    //找出departure_date在date时间，且出发地是departureCity的机场的所有航班信息
    List<FlightSearchRespDTO.Flight> selectUnStraight2(FlightSearchReqDTO requestParam);

}
