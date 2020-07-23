package com.direction.spring.mvc.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@NoRepositoryBean
public interface BaseDirRepository<T, ID extends Serializable> extends JpaRepositoryImplementation<T, ID> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取实体管理器.
	 * 
	 * @return
	 */
	EntityManager getEntityManager();
	
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
	 * 执行修改HQL.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	int executeUpdate(String hql, Map<String, Object> params);
	
	/**
	 * 执行修改HQL.
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	int executeUpdate(String hql, Object... objects);
	
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
	 * 执行修改SQL.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	int executeSQLUpdate(String sql, Map<String, Object> params);
	
	/**
	 * 执行修改SQL.
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	int executeSQLUpdate(String sql, Object... objects);
}
