package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.*;
import com.scu.planeticket.service.FlightInfoService;
import com.scu.planeticket.service.PredictPriceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

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
@Slf4j
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

    @PostMapping("/search")
    public FlightSearchRespDTO search(@RequestBody FlightSearchReqDTO requestParam) {
        try {
            return flightInfoService.search(requestParam);
        } catch (IOException e) {
            log.error("search error", e);
        }
        return null;
    }
}

