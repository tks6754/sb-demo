package com.miao.spring.demo.common;





public enum ErrorEnum {

    /*数据库记录异常*/
    DB_NOT_EXIST("DB5101", "记录不存在"),


    /*Quartz异常*/
    JOB_RUN_ERR("QTZ5101","运行job任务错误"),
    JOB_PAUSE_ERR("QTZ5103", "暂停job任务错误"),
    JOB_RUN_ONCE_ERR("QTZ5105", "运行一次job任务错误"),
    JOB_UPDATE_ERR("QTZ5106", "更新job任务错误"),
    JOB_DELETE_ERR("QTZ5107", "删除job任务错误"),

    /*设计逻辑异常*/
    LOGICAL_ERR("LE5999", "设计逻辑冲突"),

    /*请求参数异常*/
    REQUEST_PARAM_ERR("RPE4001", "请求参数异常"),

    /*内部服务异常*/
    INNER_SERVER_ERR("ISE5001", "系统服务异常"),
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
