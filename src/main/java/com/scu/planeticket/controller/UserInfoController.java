package com.scu.planeticket.controller;
import com.scu.planeticket.JsonResult;
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
@ResponseBody
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

    @GetMapping("/reg")
    @ResponseBody
    public boolean reg(UserInfo userInfo){
        userInfoService.reg(userInfo);
        return true;
    }

    @RequestMapping("/login")
    public JsonResult<UserInfo> login(String userName, String password) {
        UserInfo data = userInfoService.login(userName,password);
        String message="登录成功";
        return new JsonResult<>(message, data);
    }

}
