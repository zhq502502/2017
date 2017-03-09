package com.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
public @interface Anno {
	String value() default "vvvvv";
	String null_rep() default "";
}
