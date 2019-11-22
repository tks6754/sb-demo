package com.miao.spring.demo.common;





public enum ErrorEnum {

    /*Quartz异常*/
    JOB_RUN_ERR("qtz5101","运行job任务错误"),
    JOB_PAUSE_ERR("qtz5103", "暂停job任务错误"),
    JOB_RUN_ONCE_ERR("qtz5105", "运行一次job任务错误"),
    JOB_UPDATE_ERR("qtz5106", "更新job任务错误"),
    JOB_DELETE_ERR("qtz5107", "删除job任务错误"),
    ;








    private final String errCode;
    private final String errorMsg;

    ErrorEnum(final String errCode, final String errorMsg){
        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }

    public String errCode() {
        return errCode;
    }

    public String errorMsg() {
        return errorMsg;
    }
}
