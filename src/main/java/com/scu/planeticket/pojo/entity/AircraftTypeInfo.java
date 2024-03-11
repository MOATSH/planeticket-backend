package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AircraftTypeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aircraft_type_id", type = IdType.AUTO)
    private Integer aircraftTypeId;

    private String aircraftTypeName;

    private String manufacturer;


}
