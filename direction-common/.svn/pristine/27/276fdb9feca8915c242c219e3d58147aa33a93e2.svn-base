package com.direction.core.modules.sys.tenant.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.modules.sys.tenant.entity.TenantCompCert;
import com.direction.core.modules.sys.tenant.entity.TenantPer;
import com.direction.core.modules.sys.tenant.repository.TenantCompCertRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 租户/主体认证-个人认证.
 * 
 * <pre>
 * 租户 / 主体认证 - 个人认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class TenantCompCertService extends BaseService<TenantCompCertRepository, TenantCompCert, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param tenant
	 * @param page
	 * @return
	 */
	public VuePage<TenantCompCert> findPage(TenantPer entity, VuePage<TenantCompCert> page) {

		// 封装查询条件
		Specification<TenantCompCert> params = new Specification<TenantCompCert>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TenantCompCert> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (entity != null) {

					// // 判断关键字
					// if (StringUtils.isNotBlank(entity.getTelephone())) {
					// predicateList
					// .add(criteriaBuilder.like(root.get("telephone").as(String.class), "%" + tenantReg.getTelephone() + "%"));
					// }
					//
					// // 判断类型
					// if (StringUtils.isNotBlank(tenantReg.getTenantType())) {
					// predicateList
					// .add(criteriaBuilder.equal(root.get("tenantType").as(String.class), tenantReg.getTenantType()));
					// }
					//
					// // 判断状态
					// if (StringUtils.isNotBlank(tenantReg.getStatus())) {
					// predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), tenantReg.getStatus()));
					// }
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		return findPage(params, page);
	}
}
