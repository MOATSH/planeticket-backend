package com.scu.planeticket.controller;


import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.UserInfoService;
import io.netty.util.internal.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/userInfo")
@CrossOrigin
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;


    @GetMapping("/login")
    public Boolean login(@RequestParam String userName, @RequestParam String password) {
        return userInfoService.login(userName, password);
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody UserInfo requestParam) {
        if (StringUtil.isNullOrEmpty(requestParam.getUserName()) || StringUtil.isNullOrEmpty(requestParam.getPassword())) {
            return Boolean.FALSE;
        }
        return userInfoService.register(requestParam);
    }
}

