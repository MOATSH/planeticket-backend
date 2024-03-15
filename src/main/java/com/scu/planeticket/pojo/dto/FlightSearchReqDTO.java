package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-15 13:45
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchReqDTO {

    private String departureCity;

    private String destCity;

    private String date;

}
