package com.scu.planeticket.mapper;

import com.scu.planeticket.pojo.dto.GetAircraftTypeInfoDTO;
import com.scu.planeticket.pojo.entity.AircraftTypeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
public interface AircraftTypeInfoMapper extends BaseMapper<AircraftTypeInfo> {

    @Select("select aircraft_type_name, manufacturer from aircraft_type_info")
    ArrayList<GetAircraftTypeInfoDTO> getAircraftTypeInfo();

}
