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
 * @Create: 2024-03-12 10:22
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightRecommendTimeRespDTO {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PriceList {
        private String date;
        private String price;
    }

    private String departureCity;

    private String destCity;

    private List<PriceList> priceList;

}
