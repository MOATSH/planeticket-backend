package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.dto.GetCityInfoListRespDTO;
import com.scu.planeticket.pojo.entity.CityInfo;
import com.scu.planeticket.service.CityInfoService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/cityInfo")
public class CityInfoController {
    @Resource
    private CityInfoService cityInfoService;

    @GetMapping("/list")
    public List<GetCityInfoListRespDTO> getCityInfoList() {
        return cityInfoService.getInfoList();
    }

    @GetMapping("/getSingle")
    public CityInfo getSingleCityInfo(@RequestParam String cityName) {
        return cityInfoService.getSingleCityInfo(cityName);
    }
}

