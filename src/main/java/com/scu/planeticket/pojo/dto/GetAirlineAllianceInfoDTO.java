package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAirlineAllianceInfoDTO {
    private static final long serialVersionUID = 1L;

    private String allianceName;

    private Integer airlineCount;
}
