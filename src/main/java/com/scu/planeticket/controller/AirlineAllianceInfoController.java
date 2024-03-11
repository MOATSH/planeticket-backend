package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.GetAirlineAllianceInfoDTO;
import com.scu.planeticket.service.AirlineAllianceInfoService;
import com.scu.planeticket.service.AirlineInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@RestController
@RequestMapping("/airlineAllianceInfo")
public class AirlineAllianceInfoController {
    @Resource
    private AirlineAllianceInfoService airlineAllianceInfoService;

    @GetMapping("/list")
    public List<GetAirlineAllianceInfoDTO> getAirlineAllianceInfo() {
        return airlineAllianceInfoService.getAirlineAllianceInfo();
    }
}

