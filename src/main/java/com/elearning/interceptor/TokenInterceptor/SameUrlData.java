package com.elearning.interceptor.TokenInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈<br>
 * 〈〉
 *页面form    防止表单重复提交
 * @author lxx
 * @create 2019/10/24 15:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SameUrlData {
}
