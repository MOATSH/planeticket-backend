package com.scu.planeticket.pojo.dto;

import com.scu.planeticket.pojo.entity.FlightInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-16 10:09
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendReqDTO {

    private Integer user_id;

    private List<FlightInfo> candidate_tickets;

}
