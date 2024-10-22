package com.scu.planeticket.mapper;

import com.scu.planeticket.pojo.dto.GetAirPortInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirportInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
public interface AirportInfoMapper extends BaseMapper<AirportInfo> {

    List<GetAirPortInfoListRespDTO> getInfoList();

}
