package com.miao.spring.demo.common.exception;

import com.miao.spring.demo.common.ErrorEnum;

/**
 * 系统逻辑异常
 */
public class LogicalException extends BaseException {
    private static final long serialVersionUID = 5574231462241079807L;

    public LogicalException(String errorMsg){
        super(ErrorEnum.LOGICAL_ERR.errCode(), errorMsg);
    }
}
