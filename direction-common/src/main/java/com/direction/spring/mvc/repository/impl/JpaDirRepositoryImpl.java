package com.direction.spring.mvc.repository.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.repository.JpaDirRepository;

// ~ File Information
// ====================================================================================================================

public class JpaDirRepositoryImpl<T> implements JpaDirRepository<T> {

	// ~ Static Fields
	// ==================================================================================================================

	// private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JpaDirRepositoryImpl.class);

	// ~ Fields
	// ==================================================================================================================

	private EntityManager entityManager;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 查询数据.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#query(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String hql, Map<String, Object> params) {

		if (StringUtils.isBlank(hql)) {
			return null;
		}

		Query query = entityManager.createQuery(hql);

		if (params != null && params.size() > 0) {

			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.getResultList();
	}

	/**
	 * 查询数据.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#query(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String hql, Object... objects) {

		if (StringUtils.isBlank(hql)) {
			return null;
		}

		Query query = entityManager.createQuery(hql);

		if (objects != null && objects.length > 0) {

			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}

		return query.getResultList();
	}

	public EntityManager getEntityManager() {

		return entityManager;
	}

	/**
	 * 注入实体管理器.
	 * 
	 * @param entityManager
	 */
	@PersistenceContext(unitName = "entityManagerFactoryPrimary")
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	/**
	 * 分页查询.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#findHQLPage(java.lang.String, java.util.Map, org.springframework.data.domain.Pageable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public VuePage<T> findHQLPage(String hql, Map<String, Object> params, VuePage<T> page) {
		
		if (StringUtils.isNotBlank(hql)) {
			
			// 查询总数
			String countHql = "select count(*) " + hql;
			
			// 获取总数
			Query query = entityManager.createQuery(countHql);
			
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			
			List<Long> totals = query.getResultList();
			long total = 0L;
			for (Long element : totals) {
				total += element == null ? 0 : element;
			}
			
			// 获取数据
			query = entityManager.createQuery(hql + page.getOrderby());
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			
			query.setFirstResult((page.getPageNumber() - 1) * page.getPageSize()).setMaxResults(page.getPageSize());
			
			List<T> results = query.getResultList();
			page.setPage(results, total);
		}
		
		return page;
	}

	/**
	 * 执行SQL查询.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#querySQL(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> querySQL(String sql, Map<String, Object> params) {

		if (StringUtils.isBlank(sql)) {
			return null;
		}

		Query query = entityManager.createNativeQuery(sql, Map.class);

		if (params != null && params.size() > 0) {

			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		
		return query.getResultList();
	}

	/**
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#querySQL(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> querySQL(String sql, Object... objects) {

		if (StringUtils.isBlank(sql)) {
			return null;
		}

		Query query = entityManager.createNativeQuery(sql, Map.class);

		if (objects != null && objects.length > 0) {

			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}

		return query.getResultList();
	}

	/**
	 * 使用SQL进行分页查询.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#findSQLPage(java.lang.String, java.util.Map, com.direction.spring.mvc.page.VuePage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public VuePage<T> findSQLPage(String sql, Map<String, Object> params, VuePage<T> page) {
		
		if (StringUtils.isNotBlank(sql)) {
			
			// 查询总数
			String countSql = "select count(*) " + sql;
			
			// 获取总数
			Query query = entityManager.createNativeQuery(countSql);
			
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			
			List<Long> totals = query.getResultList();
			long total = 0L;
			
			for (Long element : totals) {
				total += element == null ? 0 : element;
			}
			
			// 获取数据
			query = entityManager.createNativeQuery(sql + page.getOrderby());
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			
			query.setFirstResult((page.getPageNumber() - 1) * page.getPageSize()).setMaxResults(page.getPageSize());
			
			List<T> results = query.getResultList();
			page.setPage(results, total);
		}
		
		return page;
	}
}
