package com.scu.planeticket;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

    private Integer state;
    private String message;
    private T data;

}
