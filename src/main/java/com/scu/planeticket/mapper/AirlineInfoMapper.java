package com.scu.planeticket.mapper;

import com.scu.planeticket.pojo.dto.GetAirlineInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirlineInfo;
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
public interface AirlineInfoMapper extends BaseMapper<AirlineInfo> {
    List<GetAirlineInfoListRespDTO> getInfoList();
}
