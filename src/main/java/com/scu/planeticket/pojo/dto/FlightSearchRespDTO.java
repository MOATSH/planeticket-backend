package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-15 13:45
 **/

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FlightSearchRespDTO {

    private List<List<Flight>> straight;
    private List<List<Flight>> unStraight;
    private List<List<Flight>> recommend;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Flight {
        private int flightId;
        private String departureCity;
        private String departureAirport;
        private String destCity;
        private String destAirport;
        private String departureDate;
        private String totalFare;
        private String totalDistance;
        private String travelDuration;
        private String arrivalDate;
    }

}
