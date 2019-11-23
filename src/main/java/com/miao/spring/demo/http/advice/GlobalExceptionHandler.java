package com.miao.spring.demo.http.advice;

import com.miao.spring.demo.common.exception.BaseException;
import com.miao.spring.demo.http.GlobalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@Slf4j
@ResponseBody
@RestControllerAdvice(basePackages = "com.miao.spring.demo.controller")
public class GlobalExceptionHandler {

    /**
     * 自定义异常 捕获
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(BaseException.class)
    public GlobalResponse baseExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        BaseException exception = (BaseException) e;
        log.error("服务异常, 错误码:[{}], 错误信息:[{}]", exception.getErrCode(), exception.getErrCode(), e);
        return GlobalResponse.failed(exception.getErrCode(), exception.getErrorMsg());
    }


    //MethodArgumentNotValidException
      //      MethodArgumentTypeMismatchException
    // ConstraintViolationException

    /**
     * 参数校验异常 捕获
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public GlobalResponse validationExceptionHandler(HttpServletRequest request, HttpServletResponse response, final Exception e){





        return null;
    }



    /**
     * 捕获  RuntimeException 异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public GlobalResponse runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        log.error("服务内部运行错误", exception);
        return GlobalResponse.failed("500", exception.getMessage());
    }

}