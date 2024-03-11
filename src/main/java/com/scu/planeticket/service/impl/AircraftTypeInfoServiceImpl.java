package com.scu.planeticket.service.impl;

import com.scu.planeticket.pojo.dto.GetAircraftTypeInfoDTO;
import com.scu.planeticket.pojo.entity.AircraftTypeInfo;
import com.scu.planeticket.mapper.AircraftTypeInfoMapper;
import com.scu.planeticket.service.AircraftTypeInfoService;
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
public class AircraftTypeInfoServiceImpl extends ServiceImpl<AircraftTypeInfoMapper, AircraftTypeInfo> implements AircraftTypeInfoService {

    @Resource
    private AircraftTypeInfoMapper aircraftTypeInfoMapper;

    @Override
    public ArrayList<GetAircraftTypeInfoDTO> getAircraftTypeInfo() {
        return aircraftTypeInfoMapper.getAircraftTypeInfo();
    }
}
