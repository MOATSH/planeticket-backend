package com.scu.planeticket.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoListRespDTO {
    private Integer userId;

    private String userName;

    private String password;
}
