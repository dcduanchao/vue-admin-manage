package com.example.dc.ann;

import java.lang.annotation.*;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:39 2020/10/12
 * @ Description：
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
