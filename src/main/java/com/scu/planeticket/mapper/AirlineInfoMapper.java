package com.scu.planeticket.mapper;

import com.scu.planeticket.pojo.entity.AirlineInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
public interface AirlineInfoMapper extends BaseMapper<AirlineInfo> {
    /*@Select("select a.airline_name , a.country, b.alliance_name from airline_info a, airline_alliance_info b" +
            "where a.alliance_id = b.alliance_id")*/
}
