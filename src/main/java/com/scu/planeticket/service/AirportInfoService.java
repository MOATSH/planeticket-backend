package com.scu.planeticket.service;

import com.scu.planeticket.pojo.dto.GetAirPortInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirportInfo;
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
public interface AirportInfoService extends IService<AirportInfo> {

    List<GetAirPortInfoListRespDTO> getInfoList();

}
