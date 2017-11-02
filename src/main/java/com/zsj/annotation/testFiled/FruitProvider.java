package com.zsj.annotation.testFiled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {
	/**
	 * 供应商编号
	 */
	public int id() default -1;

	/**
	 * 供应商名称
	 */
	public String name() default "";

	/**
	 * 供应商地址
	 */
	public String address() default "";
}
