package com.miao.spring.demo.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miao.spring.demo.common.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse<T> {

    protected boolean success = false;

    private T data;

    private String errorCode;
    private String errorMsg;

    public static GlobalResponse success(){
        return new GlobalResponse(true, null, null, null);
    }

    public static <T> GlobalResponse<T> success(T data){
        return new GlobalResponse<>(true, data, null, null);
    }

    public static GlobalResponse fail(String errorCode, String errorMsg){
        return new GlobalResponse(false, null, errorCode, errorMsg);
    }

    public static GlobalResponse fail(ErrorEnum errEnum){
        return new GlobalResponse(false, null, errEnum.errCode(), errEnum.errorMsg());
    }

    public static <T> GlobalResponse<T> fail(ErrorEnum errEnum, T data){
        return new GlobalResponse<>(false, data, errEnum.errCode(), errEnum.errorMsg());
    }
}
