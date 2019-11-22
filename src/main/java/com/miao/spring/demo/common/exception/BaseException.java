package com.miao.spring.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -3550672046776764077L;

    private String errCode;
    private String errorMsg;

    /**
     * constructor
     * @param errCode
     * @param errorMsg
     * @param cause
     */
    public BaseException(String errCode, String errorMsg, Throwable cause){
        super(cause);
        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }

}
