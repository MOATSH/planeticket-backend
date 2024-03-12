package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.GetAircraftTypeInfoDTO;
import com.scu.planeticket.service.AircraftTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
@RequestMapping("/aircraftTypeInfo")
@CrossOrigin
public class AircraftTypeInfoController {
    @Resource
    private AircraftTypeInfoService aircraftTypeInfoService;

    @GetMapping("/list")
    public List<GetAircraftTypeInfoDTO> getAircraftTypeInfo () {
        return aircraftTypeInfoService.getAircraftTypeInfo();
    }
}

