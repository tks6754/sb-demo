package com.miao.spring.demo.common.exception;

import com.miao.spring.demo.common.ErrorEnum;

public class DbException {

    public static class DbNotExistException extends BaseException{
        private static final long serialVersionUID = -762937455654147384L;

        public DbNotExistException(){
            super(ErrorEnum.DB_NOT_EXIST, null);
        }
    }

}
