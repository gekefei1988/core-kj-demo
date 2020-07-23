package com.direction.spring.mvc.repository.support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.repository.BaseDirRepository;

// ~ File Information
// ====================================================================================================================

public class BaseDirRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements BaseDirRepository<T, ID> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// 实体信息
	private final JpaEntityInformation<T, ?> entityInformation;

	// 实体管理器
	private final EntityManager entityManager;

	// 持久化
	private final PersistenceProvider provider;

	// 方法元素
	private @Nullable CrudMethodMetadata metadata;

	// ~ Constructors
	// ==================================================================================================================

	/**
	 * 构造方法, 初始化实体信息.
	 *
	 * @param entityInformation
	 * @param entityManager
	 */
	public BaseDirRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {

		super(entityInformation, entityManager);

		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
		this.provider = PersistenceProvider.fromEntityManager(entityManager);
	}

	/**
	 * 构造方法.
	 *
	 * @param domainClass
	 * @param em
	 */
	public BaseDirRepositoryImpl(Class<T> domainClass, EntityManager em) {

		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID进行查询.
	 * 
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#getOne(java.lang.Object)
	 */
	@Override
	public T getOne(ID id) {

		Optional<T> entity = this.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}

		return null;
	}

	/**
	 * @see com.direction.spring.mvc.repository.BaseDirRepository#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {

		return this.entityManager;
	}

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

		Query query = entityManager.createQuery(hql, getDomainClass());

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

		Query query = entityManager.createQuery(hql, getDomainClass());

		if (objects != null && objects.length > 0) {

			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}

		return query.getResultList();
	}

	/**
	 * 分页查询.
	 * 
	 * @see com.direction.spring.mvc.repository.JpaDirRepository#findHQLPage(java.lang.String, java.util.Map,
	 *      org.springframework.data.domain.Pageable)
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
			query = entityManager.createQuery(hql + page.getOrderby(), getDomainClass());
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
	 * 执行modifying语句.
	 * 
	 * @see com.direction.spring.mvc.repository.BaseDirRepository#executeUpdate(java.lang.String, java.util.Map)
	 */
	@Override
	@Transactional
	public int executeUpdate(String hql, Map<String, Object> params) {

		if (StringUtils.isBlank(hql)) {
			return 0;
		}

		Query query = entityManager.createQuery(hql);

		if (params != null && params.size() > 0) {

			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.executeUpdate();
	}

	/**
	 * 执行modifying语句.
	 * 
	 * @see com.direction.spring.mvc.repository.BaseDirRepository#executeUpdate(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	@Transactional
	public int executeUpdate(String hql, Object... objects) {

		if (StringUtils.isBlank(hql)) {
			return 0;
		}

		Query query = entityManager.createQuery(hql);

		if (objects != null && objects.length > 0) {

			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}

		return query.executeUpdate();
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
	 * @see com.direction.spring.mvc.repository.BaseDirRepository#executeSQLUpdate(java.lang.String, java.util.Map)
	 */
	@Override
	@Transactional
	public int executeSQLUpdate(String sql, Map<String, Object> params) {

		if (StringUtils.isBlank(sql)) {
			return 0;
		}

		Query query = entityManager.createNativeQuery(sql);

		if (params != null && params.size() > 0) {

			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.executeUpdate();
	}

	/**
	 * @see com.direction.spring.mvc.repository.BaseDirRepository#executeSQLUpdate(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	@Transactional
	public int executeSQLUpdate(String sql, Object... objects) {

		if (StringUtils.isBlank(sql)) {
			return 0;
		}

		Query query = entityManager.createNativeQuery(sql);

		if (objects != null && objects.length > 0) {

			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}

		return query.executeUpdate();
	}
}
