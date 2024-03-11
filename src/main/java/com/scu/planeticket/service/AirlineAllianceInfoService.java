package com.scu.planeticket.service;

import com.scu.planeticket.pojo.dto.GetAirlineAllianceInfoDTO;
import com.scu.planeticket.pojo.entity.AirlineAllianceInfo;
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
public interface AirlineAllianceInfoService extends IService<AirlineAllianceInfo> {
    public ArrayList<GetAirlineAllianceInfoDTO> getAirlineAllianceInfo();
}
