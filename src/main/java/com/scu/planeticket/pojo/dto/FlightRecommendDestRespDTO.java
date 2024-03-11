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
 * @Create: 2024-03-11 14:41
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRecommendDestRespDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MapInfo {
        private Float longitude;
        private Float latitude;
        private String price;
    }

    private List<MapInfo> mapInfo;

}
