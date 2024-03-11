package com.scu.planeticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu.planeticket.pojo.dto.FlightRecommendDestReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendDestRespDTO;
import com.scu.planeticket.pojo.entity.PredictPriceInfo;

import java.util.List;

/**
 * <p>
 * 航班价格预测信息表 Mapper 接口
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
public interface PredictPriceInfoMapper extends BaseMapper<PredictPriceInfo> {

    List<FlightRecommendDestRespDTO.MapInfo> selectRecommendDest(FlightRecommendDestReqDTO reqDTO);

}
