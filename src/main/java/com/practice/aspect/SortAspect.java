package com.practice.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.practice.bean.query.Sort;
import com.practice.util.QueryUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Aspect 切面
 */
@Aspect
@Component
public class SortAspect {

    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger(SortAspect.class);

    /**
     * Pointcut 切入点
     */
    @Pointcut("@annotation(com.practice.aspect.SortAop)")
//    @Pointcut("execution(* com.practice.service.impl.*.*(..))")
    public void sortType() {
    }

    /**
     * 方法执行前
     */
    @Before(value = "sortType()")
    public void before(JoinPoint joinPoint) {
        /*// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));*/
        Object[] args = joinPoint.getArgs();
        if (ArrayUtil.isNotEmpty(args)) {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            SortAop sortAop = method.getAnnotation(SortAop.class);
            Class clazz = sortAop.Class();
            for (Object arg : args) {
                Object prop = null;
                try {
                    // 通过属性获取对象的属性
                    Field field = arg.getClass().getDeclaredField("sort");
                    // 对象的属性的访问权限设置为可访问
                    field.setAccessible(true);
                    // 获取属性的对应的值
                    prop = field.get(arg);
                } catch (Exception e) {
                    continue;
                }
                if (prop instanceof Sort) {
                    Sort sort = (Sort) prop;
                    if (StrUtil.isNotBlank(sort.getField())) {
                        QueryUtils.setField(sort, clazz);
                    }
                }
            }
        }
    }

}
