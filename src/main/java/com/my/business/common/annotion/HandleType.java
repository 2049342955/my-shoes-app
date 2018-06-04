package com.my.business.common.annotion;

import com.my.business.common.ReturnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HandleType {

    String value() default "";

    ReturnType returnType() default ReturnType.OBJECT;
}
