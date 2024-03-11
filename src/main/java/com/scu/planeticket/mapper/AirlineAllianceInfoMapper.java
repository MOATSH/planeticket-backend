package com.scu.planeticket.mapper;

import com.scu.planeticket.pojo.dto.GetAirlineAllianceInfoDTO;
import com.scu.planeticket.pojo.entity.AirlineAllianceInfo;
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
public interface AirlineAllianceInfoMapper extends BaseMapper<AirlineAllianceInfo> {
    @Select("SELECT b.alliance_name, COUNT(a.airline_id) AS airline_count\n" +
            "FROM airline_info a\n" +
            "JOIN airline_alliance_info b\n" +
            "ON a.alliance_id = b.alliance_id\n" +
            "GROUP BY b.alliance_id;")
    ArrayList<GetAirlineAllianceInfoDTO> getAirlineAllianceInfo();
}
