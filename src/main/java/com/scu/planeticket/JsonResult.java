package com.scu.planeticket;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    /** 状态描述信息 */
    private String state;
    /** 数据 */
    private T data;

    public JsonResult(String  ok, T data) {
        this.state = ok;
        this.data = data;
    }
}
