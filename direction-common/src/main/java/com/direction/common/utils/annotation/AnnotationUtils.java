package com.direction.common.utils.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.direction.common.annotation.SearchColumn;
import com.direction.common.mappers.MapperColumn;

// ~ File Information
// ====================================================================================================================

public class AnnotationUtils {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取查询条件.
	 * 
	 * @param clazz
	 * @return
	 */
	public static Map<String, MapperColumn> loadColumnMap(Class<?> clazz) {
		
		List<MapperColumn> columns = new ArrayList<MapperColumn>();
		
		// 获取注解
		AnnotationUtils.loadClassColumns(clazz, columns);
		
		Map<String, MapperColumn> results = new HashMap<String, MapperColumn>();
		
		for (MapperColumn col : columns) {
			results.put(col.getName(), col);
		}
		
		return results;
	}

	/**
	 * 获取Class栏位注解.
	 * 
	 * @param clazz
	 * @param columns
	 */
	public static void loadClassColumns(Class<?> clazz, List<MapperColumn> columns) {
		
		if (clazz != null && !clazz.getName().equals(Object.class.getName())) {
			
			Field[] fields = clazz.getDeclaredFields();
			SearchColumn col = null;
			
			for (Field filed : fields) {
				
				col = filed.getAnnotation(SearchColumn.class);
				
				if (col == null) {
					continue;
				}
				
				columns.add(new MapperColumn(filed.getName(), col.queryType(), col.isTenant(), col.relationType(), col.combRelationType(), col.combColumns()));
			}
			
			// 如果存在上级
			AnnotationUtils.loadClassColumns(clazz.getSuperclass(), columns);
		}
		
		return;
	}
}
