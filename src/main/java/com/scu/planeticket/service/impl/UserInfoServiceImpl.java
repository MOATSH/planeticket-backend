package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.mapper.UserInfoMapper;
import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public Boolean login(String userName, String password) {
        UserInfo userInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_name", userName).eq("password", password));

        return userInfo != null;
    }

    @Override
    public Boolean register(UserInfo requestParam) {
        return baseMapper.insert(requestParam) != 0;
    }

}
