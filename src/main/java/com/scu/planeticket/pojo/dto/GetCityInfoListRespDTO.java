package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-10 09:41
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCityInfoListRespDTO {

    private Integer cityId;

    /*
    城市名称
     */
    private String title;

    private String cityCode;

    private Float latitude;

    private Float longitude;

}
