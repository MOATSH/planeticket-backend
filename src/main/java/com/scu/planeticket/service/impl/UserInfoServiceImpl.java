package com.scu.planeticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.planeticket.JsonResult;
import com.scu.planeticket.mapper.UserInfoMapper;
import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-10 10:45
 **/

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    JsonResult<Void> jsonResult = new JsonResult<>();
    @Override
    public void reg(UserInfo userInfo){
        UserInfo result = userInfoMapper.findByUsername(userInfo.getUserName());
        if(result!=null){
            System.out.println("用户名被占用");
        }else{
            String oldPassword = userInfo.getPassword();
            userInfo.setPassword(oldPassword);
            Integer rows = userInfoMapper.insert(userInfo);
            if(rows!= 1){
                System.out.println("注册失败");
            }
        }
    }
}
