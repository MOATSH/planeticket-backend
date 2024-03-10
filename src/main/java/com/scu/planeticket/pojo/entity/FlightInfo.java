package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 航班信息表
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlightInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "flight_id", type = IdType.AUTO)
    private Long flightId;

    /**
     * 起飞机场三字码
     */
    private String startAirport;

    /**
     * 目的机场三字码
     */
    private String destAirport;

    /**
     * 总时长
     */
    private String travelDuration;

    /**
     * 起飞时间
     */
    private String departureDate;

    /**
     * 含税票价（$）
     */
    private BigDecimal totalFare;

    /**
     * 总距离（英里）
     */
    private Integer totalDistance;

    /**
     * 是否经停城市（0-否，1-是）
     */
    @TableField("is_not_stop")
    private Boolean notStop;

    /**
     * 到达时间
     */
    private String arrivalDate;


}
