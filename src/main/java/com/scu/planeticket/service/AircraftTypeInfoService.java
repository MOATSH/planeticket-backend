package com.scu.planeticket.service;

import com.scu.planeticket.pojo.dto.GetAircraftTypeInfoDTO;
import com.scu.planeticket.pojo.entity.AircraftTypeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
public interface AircraftTypeInfoService extends IService<AircraftTypeInfo> {
    public ArrayList<GetAircraftTypeInfoDTO> getAircraftTypeInfo();
}
