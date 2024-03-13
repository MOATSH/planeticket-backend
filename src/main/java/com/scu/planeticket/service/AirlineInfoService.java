package com.scu.planeticket.service;

import com.scu.planeticket.pojo.dto.GetAirlineInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirlineInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MOATSH
 * @since  2024-03-09
 */
public interface AirlineInfoService extends IService<AirlineInfo> {
List<GetAirlineInfoListRespDTO> getInfoList();
}
