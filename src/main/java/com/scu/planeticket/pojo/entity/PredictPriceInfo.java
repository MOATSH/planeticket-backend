package com.scu.planeticket.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 航班价格预测信息表
 * </p>
 *
 * @author MOATSH
 * @since 2024-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PredictPriceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增唯一标识
     */
    @TableId(value = "predict_id", type = IdType.AUTO)
    private Long predictId;

    /**
     * 起始机场三字码
     */
    private String startAirport;

    /**
     * 目的机场三字码
     */
    private String destAirport;

    /**
     * 价格
     */
    private String price;

    /**
     * 日期
     */
    private LocalDateTime date;


}
