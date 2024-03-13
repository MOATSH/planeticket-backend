package com.scu.planeticket.service.impl;

import com.scu.planeticket.pojo.dto.GetAirPortInfoListRespDTO;
import com.scu.planeticket.pojo.dto.GetAirlineInfoListRespDTO;
import com.scu.planeticket.pojo.entity.AirlineInfo;
import com.scu.planeticket.mapper.AirlineInfoMapper;
import com.scu.planeticket.service.AirlineInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MOATSH
 * @since  2024-03-09
 */
@Service
public class AirlineInfoServiceImpl extends ServiceImpl<AirlineInfoMapper, AirlineInfo> implements AirlineInfoService {
    @Resource
    private AirlineInfoMapper airlineInfoMapper;

    @Override
    public List<GetAirlineInfoListRespDTO> getInfoList() {
        return airlineInfoMapper.getInfoList();
    }
}
