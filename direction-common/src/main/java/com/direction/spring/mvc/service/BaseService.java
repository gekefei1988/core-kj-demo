package com.direction.spring.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.jpa.SpecificationUtils;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

public class BaseService<R extends BaseRepository<T, ID>, T, ID> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private R baseRepository;

	@PersistenceContext(unitName = "entityManagerFactoryPrimary")
	private EntityManager entityManager;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 清楚缓存.
	 * 
	 */
	public void clearCache() {

	}

	/**
	 * 根据ID查询.
	 * 
	 * @param id
	 * @return
	 */
	public T get(ID id) {

		if (id == null) {
			return null;
		}

		Optional<T> opt = baseRepository.findById(id);

		if (opt.isPresent()) {
			return opt.get();
		}

		return null;
	}

	/**
	 * 查询所有.
	 * 
	 * @return
	 */
	public List<T> findAll() {

		return baseRepository.findAll();
	}

	/**
	 * 查询所有,加入排序条件.
	 * 
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Sort sort) {

		return baseRepository.findAll(sort);
	}

	/**
	 * 根据范例查询.
	 * 
	 * @param example
	 * @return
	 */
	public List<T> findAll(Example<T> example) {

		return this.baseRepository.findAll(example);
	}

	/**
	 * 根据范例查询, 并加入排序.
	 * 
	 * @param example
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Example<T> example, Sort sort) {

		return this.baseRepository.findAll(example, sort);
	}

	/**
	 * 保存.
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public T save(T entity) {

		T result = this.baseRepository.save(entity);
		this.clearCache();
		return result;
	}

	/**
	 * 保存所有.
	 * 
	 * @param entities
	 * @return
	 */
	@Transactional(readOnly = false)
	public <S extends T> List<S> saveAll(List<S> entities) {

		List<S> results = this.baseRepository.saveAll(entities);

		this.clearCache();

		return results;
	}

	/**
	 * 删除实体.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {

		this.baseRepository.delete(entity);
		this.clearCache();
	}

	/**
	 * 根据ID进行删除.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteById(ID id) {

		Assert.notNull(id, "The given id must not be null!");

		T entity = this.get(id);
		if (entity != null) {
			this.delete(entity);
		}
		this.clearCache();
	}

	/**
	 * 分页查询.
	 * 
	 * @param spec
	 * @param page
	 * @return
	 */
	public VuePage<T> findPage(@Nullable Specification<T> spec, VuePage<T> page) {

		Page<T> tPage = baseRepository.findAll(spec, page.getPageable());
		page.setPage(tPage);
		return page;
	}

	/**
	 * 分页查询.
	 * 
	 * @param spec
	 * @param page
	 * @return
	 */
	public VuePage<T> findPage(@Nullable T param, VuePage<T> page) {

		Specification<T> specification = SpecificationUtils.getSpecification(param);
		Page<T> tPage = baseRepository.findAll(specification, page.getPageable());
		page.setPage(tPage);

		return page;
	}

	/**
	 * 返回repository.
	 * 
	 * @return
	 */
	public R getBaseRepository() {

		return this.baseRepository;
	}

	/**
	 * 根据条件获取列表.
	 * 
	 * @param param
	 * @return
	 */
	public List<T> findList(T param) {

		List<T> list = new ArrayList<>();
		Specification<T> specification = SpecificationUtils.getSpecification(param);
		list = baseRepository.findAll(specification);

		return list;
	}

	/**
	 * 根据条件获取列表.
	 * 
	 * @param param
	 * @param sort
	 * @return
	 */
	public List<T> findList(T param, Sort sort) {

		List<T> list = new ArrayList<>();
		Specification<T> specification = SpecificationUtils.getSpecification(param);
		list = baseRepository.findAll(specification, sort);

		return list;
	}

	/**
	 * 根据属性查询.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public List<T> findByProp(String key, Object value) {

		if (StringUtils.isBlank(key) || value == null) {
			return new ArrayList<T>();
		}

		Specification<T> spec = new Specification<T>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.equal(root.get(key), value);
			}
		};

		return this.getBaseRepository().findAll(spec);
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param idValue
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String idValue) {

		return this.validateOnlyOne(null, propName, propValue, "id", idValue);
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param tenantId
	 * @param propName
	 * @param propValue
	 * @param idValue
	 * @return
	 */
	public ResultJson validateOnlyOne(String tenantId, String propName, String propValue, String idValue) {

		return this.validateOnlyOne(tenantId, propName, propValue, "id", idValue);
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param tenantId
	 * @param propName
	 * @param propValue
	 * @param propIdName
	 * @param idValue
	 * @return
	 */
	public ResultJson validateOnlyOne(String tenantId,
	    String propName,
	    String propValue,
	    String propIdName,
	    String idValue) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<T> params = new Specification<T>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				// 加入租户条件
				if (StringUtils.isNotBlank(tenantId)) {
					predicateList.add(criteriaBuilder.equal(root.get("tenantId"), tenantId));
				}

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(idValue)) {
					predicateList.add(criteriaBuilder.notEqual(root.get(propIdName).as(String.class), idValue));
				}

				predicateList.add(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		if (this.getBaseRepository().count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您填写的内容已存在, 请重新输入");
	}
	
	/**
	 * 执行hql.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<?> findListByParams(String hql, Map<String, Object> params) {
		
		if (StringUtils.isBlank(hql)) {
			return new ArrayList<>();
		}
		
		Query query = this.entityManager.createQuery(hql);
		
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.getResultList();
	}
	
	/**
	 * 执行hql.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<?> findListByParams(String hql, Object[] ...props) {
		
		if (StringUtils.isBlank(hql)) {
			return new ArrayList<>();
		}
		
		Query query = this.entityManager.createQuery(hql);
		
		if (props != null && props.length > 0) {
			for (int i = 0; i < props.length; i++) {
				query.setParameter(i, props[i]);
			}
		}
		return query.getResultList();
	}

	public EntityManager getEntityManager() {

		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}
}