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
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.log.entity.ErrorLog;
import com.direction.core.modules.sys.log.repository.ErrorLogRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 错误日志业务逻辑层.
 * 
 * <pre>
 * 错误日志业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class ErrorLogService {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(ErrorLogService.class);

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private ErrorLogRepository errorLogRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取异常信息.
	 * 
	 * @param id
	 * @return
	 */
	public ErrorLog get(String id) {
		
		if (StringUtils.isBlank(id)) {
			return null;
		}
		
		Optional<ErrorLog> log = this.errorLogRepository.findById(id);
		
		if (log.isPresent()) {
			return log.get();
		}
		
		return null;
	}
	
	/**
	 * 分页查询.
	 * 
	 * @param expType
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return
	 */
	public VuePage<ErrorLog> findPage(String expType, Date startDate, Date endDate, VuePage<ErrorLog> page) {

		// 封装查询条件
		Specification<ErrorLog> params = new Specification<ErrorLog>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ErrorLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
					
				// 类型
				if (StringUtils.isNotBlank(expType)) {
					predicateList.add(criteriaBuilder.equal(root.get("expType").as(String.class), expType));
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
		
		Page<ErrorLog> areaPage = this.errorLogRepository.findAll(params, page.getPageable());
		page.setPage(areaPage);

		return page;
	}
	
	/**
	 * 根据ID查询系统日志.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		this.errorLogRepository.deleteById(id);
	}
	
	/**
	 * 保存错误信息.
	 * 
	 * @param log
	 */
	@Transactional(readOnly = false)
	public void saveError(ErrorLog log) {
		try {
			this.errorLogRepository.save(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 保存日志信息.
	 * 
	 * @param e
	 * @param expType
	 */
	@Transactional(readOnly = false)
	public void saveError(Exception e, String expType) {

		try {
			String className = e.getStackTrace()[0].getClassName();
			String methodName = e.getStackTrace()[0].getMethodName();

			// 创建一个日志对象(准备记录日志)
			ErrorLog log = new ErrorLog();

			log.setClassName(className);
			log.setMethodName(methodName);
			log.setMessage(e.getMessage());
			log.setMethodParam("");
			log.setExpType(expType);
			
			// 获取用户
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user == null) {
				log.setCreateBy("SYS_BACKSTAGE");
			}
			else {
				log.setCreateBy(user.getUserId());
			}
			log.setCreateDate(new Date());
			errorLogRepository.save(log);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	
	/**
	 * 保存错误日志.
	 * 
	 * @param e
	 */
	@Transactional(readOnly = false)
	public void saveError(Exception e) {

		try {
			String className = e.getStackTrace()[0].getClassName();
			String methodName = e.getStackTrace()[0].getMethodName();

			// 创建一个日志对象(准备记录日志)
			ErrorLog log = new ErrorLog();

			log.setClassName(className);
			log.setMethodName(methodName);
			log.setMessage(e.getMessage());
			log.setMethodParam("");
			log.setExpType("system");
			
			// 获取用户
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user == null) {
				log.setCreateBy("SYS_BACKSTAGE");
			}
			else {
				log.setCreateBy(user.getUserId());
			}
			log.setCreateDate(new Date());
			errorLogRepository.save(log);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
}
