package com.direction.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// ~ File Information
// ====================================================================================================================

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SearchColumn {

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
	 * 是否是租户栏位
	 * 
	 * @return
	 */
	boolean isTenant() default false;
	
	/**
	 * 默认值.
	 * 
	 * @return
	 */
	String defaultValue() default "";
	
	/**
	 * 关联关系.
	 * 
	 * @return
	 */
	RelationType relationType() default RelationType.AND;
	
	/**
	 * 组合关联关系.
	 * 
	 * @return
	 */
	RelationType combRelationType() default RelationType.AND;
	
	/**
	 * 组合列.
	 * 
	 * @return
	 */
	CombColumn[] combColumns() default {};
}
