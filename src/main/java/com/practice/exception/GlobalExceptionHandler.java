package com.practice.exception;

import com.practice.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author kexin.ding
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获全局异常,处理所有不可知的异常
     */
    @ExceptionHandler(value = Exception.class)
    Result handle(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("url:{}, msg:{}", request.getRequestURL(), e.getMessage());
        return Result.fail(e.getMessage());
    }

}
