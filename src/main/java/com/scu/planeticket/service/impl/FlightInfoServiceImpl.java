package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.mapper.FlightInfoMapper;
import com.scu.planeticket.pojo.entity.FlightInfo;
import com.scu.planeticket.service.FlightInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 航班信息表 服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@Service
public class FlightInfoServiceImpl extends ServiceImpl<FlightInfoMapper, FlightInfo> implements FlightInfoService {

}
