package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-12 10:19
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightRecommendTimeReqDTO {

    private String departureCity;

    private String destCity;

}
