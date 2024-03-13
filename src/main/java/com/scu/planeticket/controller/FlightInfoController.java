package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeRespDTO;
import com.scu.planeticket.service.FlightInfoService;
import com.scu.planeticket.service.PredictPriceInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 航班信息表 前端控制器
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@RestController
@RequestMapping("/flightInfo")
@CrossOrigin
public class FlightInfoController {
    @Resource
    private FlightInfoService flightInfoService;
    @Resource
    private PredictPriceInfoService predictPriceInfoService;


    @PostMapping("/recommend/destination")
    public FlightRecommendDestRespDTO recommendDest(@RequestBody FlightRecommendDestReqDTO reqDTO) {
        return flightInfoService.recommendDest(reqDTO);
    }

    @PostMapping("/recommend/time")
    public FlightRecommendTimeRespDTO recommendTime(@RequestBody FlightRecommendTimeReqDTO requestParam) {
        return predictPriceInfoService.recommendTime(requestParam);
    }
}

