package com.practice.exception;

import com.practice.base.Result;
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
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获全局异常,处理所有不可知的异常
     */
    @ExceptionHandler(value = Exception.class)
    Result handle(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        LOG.error("url:{}, msg:{}", request.getRequestURL(), e.getMessage());
        return Result.fail(e.getMessage());
    }

}
