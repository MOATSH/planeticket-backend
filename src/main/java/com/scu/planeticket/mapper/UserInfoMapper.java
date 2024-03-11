package com.scu.planeticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu.planeticket.pojo.entity.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    //插入一条用户数据
    int insert(UserInfo userInfo);
    //根据用户名查询
    UserInfo findByUsername(String username);
    //根据id删除
    Integer deleteById(Integer id);
    //更新一条数据
    Integer update(UserInfo userInfo);
}
