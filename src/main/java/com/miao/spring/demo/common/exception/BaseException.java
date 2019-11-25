package com.miao.spring.demo.common.exception;

import com.miao.spring.demo.common.ErrorEnum;
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
     * @param errEnum
     * @param cause
     */
    public BaseException(ErrorEnum errEnum, Throwable cause){
        super(cause);
        this.errCode = errEnum.errCode();
        this.errorMsg = errEnum.errorMsg();
    }

}
