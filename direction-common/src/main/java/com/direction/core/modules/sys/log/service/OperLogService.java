package com.direction.core.modules.sys.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.utils.lang.DateUtils;
import com.direction.common.utils.mapper.JsonMapper;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.log.entity.OperLog;
import com.direction.core.modules.sys.log.repository.OperLogRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class OperLogService {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(OperLogService.class);

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private OperLogRepository operLogRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取操作信息.
	 * 
	 * @param id
	 * @return
	 */
	public OperLog get(String id) {
		
		if (StringUtils.isBlank(id)) {
			return null;
		}
		
		Optional<OperLog> log = this.operLogRepository.findById(id);
		
		if (log.isPresent()) {
			return log.get();
		}
		
		return null;
	}
	
	/**
	 * 分页查询.
	 * 
	 * @param oper
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return
	 */
	public VuePage<OperLog> findPage(OperLog oper, Date startDate, Date endDate, VuePage<OperLog> page) {

		// 封装查询条件
		Specification<OperLog> params = new Specification<OperLog>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OperLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				// title
				if (StringUtils.isNotBlank(oper.getTitle())) {
					predicateList.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + oper.getTitle() + "%"));
				}

				// 操作结果
				if (StringUtils.isNotBlank(oper.getOperResult())) {
					predicateList.add(criteriaBuilder.equal(root.get("operResult").as(String.class), oper.getOperResult()));
				}
				
				// 类型
				if (StringUtils.isNotBlank(oper.getOperType())) {
					predicateList.add(criteriaBuilder.equal(root.get("operType").as(String.class), oper.getOperType()));
				}
				
				// 日期区间
				if (startDate != null) {
					predicateList.add(criteriaBuilder.greaterThan(root.get("createDate").as(Date.class), startDate));
				}
				
				// 结束时间
				if (endDate != null) {
					Date end = DateUtils.addDays(endDate, 1);
					predicateList.add(criteriaBuilder.lessThan(root.get("createDate").as(Date.class), end));
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};
		
		Page<OperLog> areaPage = this.operLogRepository.findAll(params, page.getPageable());
		page.setPage(areaPage);

		return page;
	}
	
	/**
	 * 根据ID删除日志.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		this.operLogRepository.deleteById(id);
	}

	/**
	 * 保存操作日志.
	 * 
	 * @param obj
	 */
	@Transactional(readOnly = false)
	public void saveOperLog(Object obj) {

		try {
			OperLog operLog = new OperLog();
			if (obj != null) {
				operLog.setParams(JsonMapper.toJson(obj));
			}

			UserProfile user = UserProfileUtils.getUserProfile();
			if (user == null) {
				operLog.setCreateBy("SYS_BACKSTAGE");
			} else {
				operLog.setCreateBy(user.getUserId());
			}

			operLogRepository.save(operLog);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 保存操作日志.
	 * 
	 * @param title
	 * @param operType
	 * @param params
	 */
	@Transactional(readOnly = false)
	public void saveOperLog(String title, String operType, Object params) {

		try {
			OperLog operLog = new OperLog(title, operType, (params != null ? JsonMapper.toJson(params) : null));

			UserProfile user = UserProfileUtils.getUserProfile();
			if (user == null) {
				operLog.setCreateBy("SYS_BACKSTAGE");
			} else {
				operLog.setCreateBy(user.getUserId());
			}

			operLogRepository.save(operLog);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 保存操作日志.
	 * 
	 * @param title
	 * @param operType
	 * @param method
	 * @param operResult
	 * @param params
	 */
	@Transactional(readOnly = false)
	public void saveOperLog(String title, String operType, String method, String operResult, Object params) {

		try {
			OperLog operLog = new OperLog(title, operType, method, operResult,
			    params != null ? JsonMapper.toJson(params) : null);

			UserProfile user = UserProfileUtils.getUserProfile();
			if (user == null) {
				operLog.setCreateBy("SYS_BACKSTAGE");
			} else {
				operLog.setCreateBy(user.getUserId());
			}

			operLogRepository.save(operLog);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
