package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.GetAirPortInfoListRespDTO;
import com.scu.planeticket.pojo.dto.GetAirlineInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirlineInfo;
import com.scu.planeticket.service.AirlineInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/airlineInfo")
public class AirlineInfoController {
    @Resource
    AirlineInfoService airlineInfoService;

    @GetMapping("/list")
    public List<GetAirlineInfoListRespDTO> getAirlineInfoListRespDTOList() {
        return airlineInfoService.getInfoList();
    }
}

