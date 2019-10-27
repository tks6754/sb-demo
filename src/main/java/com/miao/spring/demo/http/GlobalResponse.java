package com.miao.spring.demo.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class GlobalResponse<T> {

    protected boolean success = false;

    private T data;

    private String errorCode;
    private String errorMsg;

    public static <T> GlobalResponse<T> success(T data){
        return new GlobalResponse<>(true, data, null, null);
    }

    public static <T> GlobalResponse<T> failed(String errorCode, String errorMsg){
        return new GlobalResponse<>(false, null, errorCode, errorMsg);
    }
}
