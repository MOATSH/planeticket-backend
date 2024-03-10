package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Program: planeticket
 * @Description: user_info实体类
 * @Author: MOATSH
 * @Create: 2024-03-10 10:40
 **/

@Data
public class UserInfo {

    @TableId(value = "city_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String password;

}
