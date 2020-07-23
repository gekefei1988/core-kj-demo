package com.direction.core.modules.sys.dept.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.modules.sys.company.entity.Company;
import com.direction.core.modules.sys.company.service.CompanyService;
import com.direction.core.modules.sys.dept.entity.Dept;
import com.direction.core.modules.sys.dept.repository.DeptRespository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 部门Service层.
 * 
 * <pre>
 * 部门Service层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class DeptService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private DeptRespository deptRespository;
	
	@Autowired
	private CompanyService companyService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据部门ID获取部门信息.
	 * 
	 * @param deptId
	 * @return
	 */
	public Dept get(String deptId) {
		
		if (StringUtils.isBlank(deptId)) {
			return null;
		}
		
		// 缓存中获取
		Dept dept = (Dept) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DEPT, "get_dept_" + deptId);
		
		if (dept != null) {
			return dept;
		}
		
		dept = deptRespository.get(deptId);
		
		// 放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DEPT, "get_dept_" + deptId, dept);
		
		return dept;
	}

	/**
	 * 保存部门信息和组织树.
	 * 
	 * @param dept
	 */
	@Transactional(readOnly = false)
	public void save(Dept dept) {

		try {
			
			if (StringUtils.isBlank(dept.getCompanyId())) {
				throw new ServiceException("请选择归属企业...");
			}
			
			Company company = companyService.get(dept.getCompanyId());
			
			if (company == null) {
				throw new ServiceException("归属企业已被删除...");
			}
			
			if (StringUtils.isBlank(dept.getDeptName())) {
				dept.setDeptName(dept.getDeptFullName());
			}
			
			// 保存部门
			this.deptRespository.save(dept);
			
			// 清空使用企业的缓存
			CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_DEPT, "get_dept_" + dept.getDeptId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 删除部门.
	 * 
	 * @param deptId
	 */
	@Transactional(readOnly = false)
	public void delete(String deptId) {
		
		if (StringUtils.isBlank(deptId)) {
			throw new ServiceException("部门ID为空...");
		}
		
		Dept dept = this.get(deptId);
		
		if (dept == null) {
			throw new ServiceException("该部门已不存在...");
		}
		
		this.deptRespository.delete(dept);
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param deptId
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String deptId) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<Dept> params = new Specification<Dept>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(deptId)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("deptId").as(String.class), deptId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		if (this.deptRespository.count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("该名称已存在, 请重新输入...");
	}
	
	/**
	 * 部门列表分页.
	 * 
	 * @param dept
	 * @param page
	 * @return
	 */
	public VuePage<Dept> findPage(Dept dept, VuePage<Dept> page) {

		// 封装查询条件
		Specification<Dept> deptParams = new Specification<Dept>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				if (dept != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(dept.getDeptName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("deptName").as(String.class), "%" + dept.getDeptName() + "%"),
						    criteriaBuilder.equal(root.get("deptCode").as(String.class), dept.getDeptName())));
					}
					
					// 负责人
					if (StringUtils.isNotBlank(dept.getChargePerson())) {
						predicateList.add(criteriaBuilder.equal(root.get("chargePerson").as(String.class), dept.getChargePerson()));
					}

					// 判断状态
					if (StringUtils.isNotBlank(dept.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), dept.getStatus()));
					}
					
					// 归属企业
					if (StringUtils.isNotBlank(dept.getCompanyId())) {
						predicateList.add(criteriaBuilder.equal(root.get("companyId").as(String.class), dept.getCompanyId()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};
		
		Page<Dept> deptPage = deptRespository.findAll(deptParams, page.getPageable());
		
		page.setPage(deptPage);

		return page;
	}
}
