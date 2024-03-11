package com.scu.planeticket.service.impl;

import com.scu.planeticket.pojo.dto.GetAirlineAllianceInfoDTO;
import com.scu.planeticket.pojo.entity.AirlineAllianceInfo;
import com.scu.planeticket.mapper.AirlineAllianceInfoMapper;
import com.scu.planeticket.service.AirlineAllianceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@Service
public class AirlineAllianceInfoServiceImpl extends ServiceImpl<AirlineAllianceInfoMapper, AirlineAllianceInfo> implements AirlineAllianceInfoService {
    @Resource
    private AirlineAllianceInfoMapper airlineAllianceInfoMapper;

    @Override
    public ArrayList<GetAirlineAllianceInfoDTO> getAirlineAllianceInfo() {

        return airlineAllianceInfoMapper.getAirlineAllianceInfo();
    }
}
