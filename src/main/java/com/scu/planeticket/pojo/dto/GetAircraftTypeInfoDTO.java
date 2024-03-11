package com.scu.planeticket.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAircraftTypeInfoDTO {
    private static final long serialVersionUID = 1L;

    private String aircraftTypeName;

    private String manufacturer;
}
