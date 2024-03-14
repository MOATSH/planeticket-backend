package com.scu.planeticket.service;

import com.scu.planeticket.pojo.dto.GetCityInfoListRespDTO;
import com.scu.planeticket.pojo.entity.CityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
public interface CityInfoService extends IService<CityInfo> {

    List<GetCityInfoListRespDTO> getInfoList();

    CityInfo getSingleCityInfo(String cityName);

}
