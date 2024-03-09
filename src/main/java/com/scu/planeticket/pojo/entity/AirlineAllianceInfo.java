package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Airline_Alliance_Info")
public class AirlineAllianceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "alliance_id", type = IdType.AUTO)
    private Integer allianceId;

    private String allianceName;


}
