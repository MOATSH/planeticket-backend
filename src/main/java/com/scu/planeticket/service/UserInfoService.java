package com.scu.planeticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.planeticket.pojo.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-14
 */
public interface UserInfoService extends IService<UserInfo> {

    Boolean login(String userName, String password);

    Boolean register(UserInfo requestParam);

}
