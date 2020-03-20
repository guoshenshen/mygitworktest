package com.elearning.interceptor.loginInterceptor;

import java.lang.annotation.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/1 13:43
 */

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface IsCheckUserLogin {
    boolean check() default false;
}
