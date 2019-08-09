package com.practice.aspect;

import java.lang.annotation.*;

/**
 * 排序 切面注解
 *
 * @author dkx
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface SortAop {

    Class Class();

}
