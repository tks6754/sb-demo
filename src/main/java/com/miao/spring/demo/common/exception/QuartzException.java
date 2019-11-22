package com.miao.spring.demo.common.exception;


/**
 * Quartz 异常
 */
public class QuartzException {

    /**
     * 运行任务异常
     */
    public static class JobRunException extends BaseException{
        private static final long serialVersionUID = 4340100533005278089L;

        public JobRunException(String errCode, String errMsg, Throwable throwable){
            super(errCode, errMsg, throwable);
        }
    }

    /**
     * 暂停任务异常
     */
    public static class JobPauseException extends BaseException{
        private static final long serialVersionUID = -1997714951151683637L;

        public JobPauseException(String errCode, String errMsg, Throwable throwable){
            super(errCode, errMsg, throwable);
        }
    }

    /**
     * 立即运行一次任务异常
     */
    public static class JobRunOnceException extends BaseException{
        private static final long serialVersionUID = 4774832393871649123L;

        public JobRunOnceException(String errCode, String errMsg, Throwable throwable){
            super(errCode, errMsg, throwable);
        }
    }

    /**
     * 更新任务异常
     */
    public static class JobUpdateException extends BaseException{
        private static final long serialVersionUID = -1328805415793767096L;

        public JobUpdateException(String errCode, String errMsg, Throwable throwable){
            super(errCode, errMsg, throwable);
        }
    }

    /**
     * 删除任务异常
     */
    public static class JobDeleteException extends BaseException{
        private static final long serialVersionUID = 3045625927243195717L;

        public JobDeleteException(String errCode, String errMsg, Throwable throwable){
            super(errCode, errMsg, throwable);
        }
    }

}
