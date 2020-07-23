package com.direction.core.modules.sys.company.service;

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
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.service.AreaService;
import com.direction.core.modules.sys.company.entity.Company;
import com.direction.core.modules.sys.company.repository.CompanyRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 企业信息业务逻辑层.
 * 
 * <pre>
 * 	企业信息业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class CompanyService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据ID获取企业.
	 * 
	 * @param companyId
	 * @return
	 */
	public Company get(String companyId) {
		
		if (StringUtils.isBlank(companyId)) {
			return null;
		}
		
		// 缓存中获取
		Company company = (Company) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_COMPANY, "get_company_" + companyId);
		
		if (company != null) {
			return company;
		}
		
		company = companyRepository.getOne(companyId);
		
		// 放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_COMPANY, "get_company_" + companyId, company);
		
		return company;
	}
	
	/**
	 * 新增或修改企业信息.
	 * 
	 * @param company
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(Company company) {
		
		// 企业简称
		if (StringUtils.isBlank(company.getCompanyName())) {
			company.setCompanyName(company.getCompanyFullName());
		}
		
		if (StringUtils.isNotBlank(company.getCompanyId())) {
			this.update(company);
		} 
		else {
			this.save(company);
		}
	}
	
	/**
	 * 修改企业信息.
	 * 
	 * @param company
	 */
	@Transactional(readOnly = false)
	private void update(Company company) {
		
		try {
			// 主键为空不能进行保存
			if (StringUtils.isBlank(company.getCompanyId())) {
				throw new ServiceException("主键为空, 不能进行修改...");
			}
			
			Company temCompany = this.get(company.getCompanyId());
			
			if (temCompany == null) {
				throw new ServiceException("该企业已被删除, 不能再次修改...");
			}
			
			// 判断归属地变动
			if (StringUtils.isNotBlank(company.getAreaCodes())) {
				String[] areaCodes = StringUtils.split(company.getAreaCodes(), ",");
				Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
				
				if (area != null) {
					company.setAreaNames(area.getAllAreaName());
				}
			}
			
			this.companyRepository.save(company);
			
			// 清空使用企业的缓存
			CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_COMPANY, "get_company_" + company.getCompanyId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 保存企业信息.
	 * 
	 * @param company
	 */
	@Transactional(readOnly = false)
	private void save(Company company) {
		try {
			
			// 保存区域
			if (StringUtils.isNotBlank(company.getAreaCodes())) {
				String[] areaCodes = StringUtils.split(company.getAreaCodes(), ",");
				Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
				
				if (area != null) {
					company.setAreaNames(area.getAllAreaName());
				}
			}
			
			this.companyRepository.save(company);
			
			// 清空使用企业的缓存
			CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_COMPANY, "get_company_" + company.getCompanyId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 根据ID删除企业信息.
	 * 
	 * @param companyId
	 */
	@Transactional(readOnly = false, value = "transactionManagerPrimary")
	public void delete(String companyId) {
			
		Company company = this.get(companyId);
		
		if (company != null) {
			
			try {
				
				this.companyRepository.delete(company);
				
				// 清空使用企业的缓存
				CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_COMPANY, "get_company_" + company.getCompanyId());
			} catch (Exception e) {
				throw new ServiceException("企业信息删除失败, 错误信息: " + e.getMessage());
			}
		}
		else {
			throw new ServiceException("企业信息删除失败, 错误信息: 该企业已不存在");
		}
	}
	
	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param companyId
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String companyId) {
		
		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}
		
		Specification<Company> companyParams = new Specification<Company>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(companyId)) {
					
					return criteriaBuilder.and(
						criteriaBuilder.notEqual(root.get("companyId").as(String.class), companyId), 
						criteriaBuilder.equal(root.get(propName).as(String.class), propValue)
					);
				}
				else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};
		
		if (this.companyRepository.count(companyParams) == 0) {
			return ResultJson.success();
		}
		
		return ResultJson.fail("已存在, 请重新输入...");
	}
	
	/**
	 * 企业分页.
	 * 
	 * @param company
	 * @param page
	 * @return
	 */
	public VuePage<Company> findPage(Company company, VuePage<Company> page) {

		// 封装查询条件
		Specification<Company> companyParams = new Specification<Company>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				if (company != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(company.getCompanyName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("companyName").as(String.class), "%" + company.getCompanyName() + "%"),
						    criteriaBuilder.like(root.get("companyFullName").as(String.class), "%" + company.getCompanyName() + "%")));
					}
					
					// 企业法人
					if (StringUtils.isNotBlank(company.getChargePerson())) {
						predicateList.add(criteriaBuilder.equal(root.get("chargePerson").as(String.class), company.getChargePerson()));
					}

					// 判断状态
					if (StringUtils.isNotBlank(company.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), company.getStatus()));
					}
					
					// 企业编号
					if (StringUtils.isNotBlank(company.getCompanyCode())) {
						predicateList.add(criteriaBuilder.equal(root.get("companyCode").as(String.class), company.getCompanyCode()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};
		
		Page<Company> companyPage = companyRepository.findAll(companyParams, page.getPageable());
		
		page.setPage(companyPage);

		return page;
	}
}
