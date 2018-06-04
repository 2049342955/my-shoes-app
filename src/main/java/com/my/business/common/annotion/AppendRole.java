package com.my.business.common.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AppendRole {

    String value() default "";

    /**
     * 包含字段
     */
    String[] include() default {"role"};

}
