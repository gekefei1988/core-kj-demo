package com.direction.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// ~ File Information
// ====================================================================================================================

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CombColumn {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 查询类型.
	 * 
	 * @return
	 */
	QueryType queryType() default QueryType.EQ;

	/**
	 * 指定列.
	 * 
	 * @return
	 */
	String column() default "";
}
