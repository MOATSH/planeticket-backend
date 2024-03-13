package com.scu.planeticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeReqDTO;
import com.scu.planeticket.pojo.dto.FlightRecommendTimeRespDTO;
import com.scu.planeticket.pojo.entity.PredictPriceInfo;

/**
 * <p>
 * 航班价格预测信息表 服务类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
public interface PredictPriceInfoService extends IService<PredictPriceInfo> {

    FlightRecommendTimeRespDTO recommendTime(FlightRecommendTimeReqDTO requestParam);

}
