package com.direction.spring.mvc.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 自定义JPA.
 * 
 * <pre>
 * 自定义JPA
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 * @param <T>
 * @param <ID>
 */
public interface JpaDirRepository<T> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	void setEntityManager(EntityManager entityManager);

	/**
	 * 分页查询.
	 * 
	 * @param hql
	 * @param params
	 * @param page
	 * @return
	 */
	VuePage<T> findHQLPage(String hql, Map<String, Object> params, VuePage<T> page);

	/**
	 * 根据hql和参数进行查询.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	List<T> query(String hql, Map<String, Object> params);

	/**
	 * 根据hql和参数进行查询.
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	List<T> query(String hql, Object... objects);
	
	/**
	 * 执行SQL.
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> querySQL(String sql, Map<String, Object> params);
	
	/**
	 * 执行SQL查询.
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<Map<String, Object>> querySQL(String sql, Object... objects);
	
	/**
	 * 分页查询.
	 * 
	 * @param sql
	 * @param params
	 * @param page
	 * @return
	 */
	VuePage<T> findSQLPage(String sql, Map<String, Object> params, VuePage<T> page);
}
