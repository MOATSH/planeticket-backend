package com.scu.planeticket.controller;
import com.scu.planeticket.mapper.UserInfoMapper;
import com.scu.planeticket.pojo.entity.UserInfo;
import com.scu.planeticket.service.UserInfoService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-10 10:47
 **/
@RestController
@CrossOrigin
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoService userInfoService;
    @GetMapping("/{userName}")
    public void findByUsername(@PathVariable String userName){
        userInfoMapper.findByUsername(userName);
    }

    @GetMapping("/register")
    public Boolean reg(UserInfo userInfo){
        return userInfoService.reg(userInfo);
    }

    @GetMapping("/login")
    public Boolean login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        UserInfo data = userInfoService.login(userName,password);

        if (data == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
