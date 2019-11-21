package com.miao.spring.demo.common.exception;


/**
 * Quartz 异常
 */
public class QuartzException {

    /**
     * 运行任务异常
     */
    public class JobRunException extends BaseException{
        public JobRunException(String jobName, String jobGroup, String message, Throwable throwable){
            super("", "运行任务失败。 jobName: "+ jobName + "groupName：" + jobGroup, message, throwable);
        }
    }

    /**
     * 暂停任务异常
     */
    public class JobPauseException extends BaseException{
        public JobPauseException(String jobName, String jobGroup, String message, Throwable throwable){
            super("", "暂停任务失败。 jobName: "+ jobName + "groupName：" + jobGroup, message, throwable);
        }
    }

    /**
     * 立即运行一次任务异常
     */
    public class JobRunOnceException extends BaseException{
        public JobRunOnceException(String jobName, String jobGroup, String message, Throwable throwable){
            super("", "立即运行一次任务失败。 jobName: "+ jobName + "groupName：" + jobGroup, message, throwable);
        }
    }

    /**
     * 更新任务异常
     */
    public class JobUpdateException extends BaseException{
        public JobUpdateException(String jobName, String jobGroup, String message, Throwable throwable){
            super("", "更新任务失败。 jobName: "+ jobName + "groupName：" + jobGroup, message, throwable);
        }
    }

    /**
     * 删除任务异常
     */
    public class JobDeleteException extends BaseException{
        public JobDeleteException(String jobName, String jobGroup, String message, Throwable throwable){
            super("", "删除任务失败。 jobName: "+ jobName + "groupName：" + jobGroup, message, throwable);
        }
    }

}
