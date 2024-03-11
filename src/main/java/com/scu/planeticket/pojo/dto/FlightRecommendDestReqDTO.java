package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-11 13:55
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightRecommendDestReqDTO {

    private LocalDateTime date;

    private String departureCity;

}
