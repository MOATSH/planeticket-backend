package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

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
public class FlightData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "legId", type = IdType.AUTO)
    private String legid;

    @TableField("searchDate")
    private String searchdate;

    @TableField("flightDate")
    private String flightdate;

    @TableField("startingAirport")
    private String startingairport;

    @TableField("destinationAirport")
    private String destinationairport;

    @TableField("fareBasisCode")
    private String farebasiscode;

    @TableField("travelDuration")
    private String travelduration;

    @TableField("elapsedDays")
    private Integer elapseddays;

    @TableField("isBasicEconomy")
    private Boolean basiceconomy;

    @TableField("isRefundable")
    private Boolean refundable;

    @TableField("isNonStop")
    private Boolean nonstop;

    @TableField("baseFare")
    private Double basefare;

    @TableField("totalFare")
    private Double totalfare;

    @TableField("seatsRemaining")
    private Integer seatsremaining;

    @TableField("totalTravelDistance")
    private Integer totaltraveldistance;

    @TableField("segmentsDepartureTimeEpochSeconds")
    private String segmentsdeparturetimeepochseconds;

    @TableField("segmentsDepartureTimeRaw")
    private String segmentsdeparturetimeraw;

    @TableField("segmentsArrivalTimeEpochSeconds")
    private String segmentsarrivaltimeepochseconds;

    @TableField("segmentsArrivalTimeRaw")
    private String segmentsarrivaltimeraw;

    @TableField("segmentsArrivalAirportCode")
    private String segmentsarrivalairportcode;

    @TableField("segmentsDepartureAirportCode")
    private String segmentsdepartureairportcode;

    @TableField("segmentsAirlineName")
    private String segmentsairlinename;

    @TableField("segmentsAirlineCode")
    private String segmentsairlinecode;

    @TableField("segmentsEquipmentDescription")
    private String segmentsequipmentdescription;

    @TableField("segmentsDurationInSeconds")
    private String segmentsdurationinseconds;

    @TableField("segmentsDistance")
    private String segmentsdistance;

    @TableField("segmentsCabinCode")
    private String segmentscabincode;


}
