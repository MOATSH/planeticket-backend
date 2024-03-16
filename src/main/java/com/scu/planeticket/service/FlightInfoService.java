package com.scu.planeticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.dto.FlightSearchReqDTO;
import com.scu.planeticket.pojo.dto.FlightSearchRespDTO;
import com.scu.planeticket.pojo.entity.FlightInfo;

import java.io.IOException;

/**
 * <p>
 * 航班信息表 服务类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
public interface FlightInfoService extends IService<FlightInfo> {

    FlightRecommendDestRespDTO recommendDest(FlightRecommendDestReqDTO reqDTO);

    FlightSearchRespDTO search(FlightSearchReqDTO requestParam) throws IOException;
}
