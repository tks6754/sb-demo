package com.miao.spring.demo.http.advice;

import com.miao.spring.demo.common.ErrorEnum;
import com.miao.spring.demo.common.exception.BaseException;
import com.miao.spring.demo.http.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        return GlobalResponse.fail(exception.getErrCode(), exception.getErrorMsg());
    }


    /**
     * 参数校验异常 捕获
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    public GlobalResponse validationExceptionHandler(HttpServletRequest request, HttpServletResponse response, final Exception e){
        Map<Object, String> errMsgMap = new HashMap<>();

        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exp = (MethodArgumentNotValidException) e;
            errMsgMap = exp.getBindingResult().getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        }else if (e instanceof ConstraintViolationException){
            ConstraintViolationException exp = (ConstraintViolationException) e;
            errMsgMap = exp.getConstraintViolations().stream()
                    .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));

        }else {
            MethodArgumentTypeMismatchException exp = (MethodArgumentTypeMismatchException) e;
            errMsgMap.put(exp.getName() + " 参数类型错误", "正确类型: " + exp.getRequiredType().getSimpleName());
        }

        return GlobalResponse.fail(ErrorEnum.REQUEST_PARAM_ERR, errMsgMap);
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
        return GlobalResponse.fail(ErrorEnum.INNER_SERVER_ERR);
    }

}