package com.scu.planeticket.service.impl;

import com.scu.planeticket.pojo.dto.GetAirPortInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirportInfo;
import com.scu.planeticket.mapper.AirportInfoMapper;
import com.scu.planeticket.service.AirportInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@Service
public class AirportInfoServiceImpl extends ServiceImpl<AirportInfoMapper, AirportInfo> implements AirportInfoService {
    @Resource
    private AirportInfoMapper airportInfoMapper;

    @Override
    public List<GetAirPortInfoListRespDTO> getInfoList() {
        return airportInfoMapper.getInfoList();
    }
}
