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
 * @since 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlightInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 航班主键
     */
      @TableId(value = "flight_id", type = IdType.AUTO)
    private Long flightId;

    /**
     * 起始机场三字码
     */
    private String startAirport;

    /**
     * 目的机场三字码
     */
    private String destAirport;

    /**
     * 总飞行时间
     */
    private String travelDuration;

    /**
     * 起飞日期
     */
    private String departureDate;

    /**
     * 总票价
     */
    private BigDecimal totalFare;

    /**
     * 总飞行距离
     */
    private Integer totalDistance;

    /**
     * 是否直飞（0-否，1-是）
     */
    @TableField("is_not_stop")
    private Boolean notStop;

    /**
     * 到达日期
     */
    private String arrivalDate;

    /**
     * 航段起飞时间
     */
    private String segmentDepartureTime;

    /**
     * 航段到达时间
     */
    private String segmentArrivalTime;

    /**
     * 航段起飞机场
     */
    private String segmentDepartureAirport;

    /**
     * 航段到达机场
     */
    private String segmentArrivalAirport;

    /**
     * 航段机型
     */
    private String segmentAircraftType;

    /**
     * 航段距离
     */
    private String segmentDistance;


}
