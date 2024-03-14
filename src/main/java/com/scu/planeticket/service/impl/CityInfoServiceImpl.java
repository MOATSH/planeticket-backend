package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scu.planeticket.pojo.dto.GetCityInfoListRespDTO;
import com.scu.planeticket.pojo.entity.CityInfo;
import com.scu.planeticket.mapper.CityInfoMapper;
import com.scu.planeticket.service.CityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements CityInfoService {

    @Override
    public List<GetCityInfoListRespDTO> getInfoList() {
        return baseMapper.selectList(null).stream().map(cityInfo -> GetCityInfoListRespDTO.builder()
                .cityCode(cityInfo.getCityCode())
                .title(cityInfo.getCityName())
                .cityId(cityInfo.getCityId())
                .latitude(cityInfo.getLatitude())
                .longitude(cityInfo.getLongitude())
                .build()).collect(Collectors.toList());
    }

    @Override
    public CityInfo getSingleCityInfo(String cityName) {
        return baseMapper.selectOne(new QueryWrapper<CityInfo>().eq("city_name", cityName));
    }

}
