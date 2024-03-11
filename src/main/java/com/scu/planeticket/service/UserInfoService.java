package com.scu.planeticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.planeticket.pojo.entity.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @Program: planeticket
 * @Description: user_info
 * @Author: MOATSH
 * @Create: 2024-03-10 10:44
 **/

public interface UserInfoService extends IService<UserInfo> {
//注册
void reg(UserInfo userInfo);
}
