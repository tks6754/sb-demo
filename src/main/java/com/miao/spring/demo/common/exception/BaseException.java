package com.miao.spring.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    private String errCode;
    private String errorMsg;

    public BaseException(String errCode, String errorMsg, String message, Throwable cause){
        super(message, cause);
        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }

}
