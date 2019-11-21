package com.miao.spring.demo.common;

public enum ErrorEnum {

    JOB_RUN_ERR("qtz5101","");

    ;






    private final String errCode;
    private final String errorMsg;

    ErrorEnum(final String errCode, final String errorMsg){
        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }
}
