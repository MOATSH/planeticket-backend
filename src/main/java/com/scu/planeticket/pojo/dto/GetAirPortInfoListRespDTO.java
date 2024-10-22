package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-09 11:49
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAirPortInfoListRespDTO {

    private Integer airportId;

    /*
    机场名称
     */
    private String title;

    private String airportCode;

    private String cityName;

    private Float latitude;

    private Float longitude;

}
