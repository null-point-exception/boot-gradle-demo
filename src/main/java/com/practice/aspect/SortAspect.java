package com.practice.aspect;

import cn.hutool.core.util.ArrayUtil;
import com.practice.bean.query.Sort;
import com.practice.util.QueryUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 排序Aspect
 *
 * @author kexin.ding
 */
@Aspect
@Component
public class SortAspect {

    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger(SortAspect.class);

    /**
     * 方法执行前
     */
    @Before("@annotation(sortAop)")
    public void before(JoinPoint joinPoint, SortAop sortAop) throws Exception {
        // 接收到请求，记录请求内容
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (ArrayUtil.isNotEmpty(args)) {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Parameter[] parameters = method.getParameters();
            int idx = -1;
            for (int i = 0; i < parameters.length; i++) {
                if (sortAop.name().equals(parameters[i].getName())) {
                    idx = i;
                    break;
                }
            }
            assert idx > -1;
            // 通过属性获取对象的属性
            Field field = args[idx].getClass().getDeclaredField("sort");
            // 对象的属性的访问权限设置为可访问
            field.setAccessible(true);
            // 获取属性的对应的值
            Sort sort = (Sort) field.get(args[idx]);
            QueryUtils.setField(sort, sortAop.typeClass());
        }
    }

}
