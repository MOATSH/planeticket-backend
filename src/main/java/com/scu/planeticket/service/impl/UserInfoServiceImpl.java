package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.mapper.UserInfoMapper;
import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-10 10:45
 **/

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
