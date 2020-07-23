package com.direction.core.inf;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.direction.common.exception.ServiceException;
import com.direction.core.inf.sys.user.UserProfile;

// ~ File Information
// ====================================================================================================================

public class UserDataHQLPermsUtils {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取用户权限.
	 * 
	 * @param userPropName
	 * @param params
	 */
	public static String getUserDataPermPredicates(String userPropName, Map<String, Object> params) {

		if (StringUtils.isAnyBlank(userPropName)) {
			throw new ServiceException("用户属性为空, 不能进行查询");
		}
		else if (params == null) {
			throw new ServiceException("params cannot be null");
		}

		UserProfile user = UserProfileUtils.getUserProfile();
		
		StringBuilder userHql = new StringBuilder();
		
		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {
			
			// 判断用户
			if (WeightsConst.WEIGHTS_USER == user.getWeights()) {
				userHql.append(" and ").append(userPropName).append(" = :prop_user_id ");
				params.put("prop_user_id", user.getUserId());
			}
		}
		else {
			userHql.append(" and ").append(userPropName).append(" = 'IS_DATA_PREM_NULL' ");
		}
		
		return userHql.toString();
	}
	
	/**
	 * 获取机构权限.
	 * 
	 * @param orgPropName
	 * @param params
	 */
	public static String getOrgDataPermPredicates(String orgPropName, Map<String, Object> params) {

		if (StringUtils.isAnyBlank(orgPropName)) {
			throw new ServiceException("企业属性为空, 不能进行查询");
		}
		else if (params == null) {
			throw new ServiceException("params cannot be null");
		}
		
		StringBuilder orgHql = new StringBuilder();

		UserProfile user = UserProfileUtils.getUserProfile();
		
		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {
			
			// 如果是超级管理员, 则不加入该权限认证, 非超级管理员, 则判断用户
			if (!user.isSuperAdmin()) {
				
				// 判断用户是否机构管理员用户, 如果是机构管理员, 则加载机构下所有ID
				if (WeightsConst.WEIGHTS_MANAGER == user.getWeights()) {
					
					List<String> orgIds = EIPService.getOrgTreeService().getOrgTreeNodeIds(user.getOrgId());
					orgIds.add(user.getOrgId());

					orgHql.append(" and ").append(orgPropName).append(" in (:prop_org_ids ) ");
					params.put("prop_org_ids", orgIds);
				}
				// 如果是用户级别, 则获取该用户所负责机构的ID, 如果没有负责机构的ID, 则获取自己的USER_ID作为查询条件
				else if (WeightsConst.WEIGHTS_USER == user.getWeights()) {
					
					// 获取该用户负责的机构IDS
					List<String> orgIds = EIPService.getDataPermService().getListOrgByUser(user.getUserId());
					
					if (orgIds != null && orgIds.size() > 0) {
						orgHql.append(" and ").append(orgPropName).append(" in (:prop_org_ids_user ) ");
						params.put("prop_org_ids_user", orgIds);
					}
					else {
						orgHql.append(" and ").append(orgPropName).append(" = 'IS_DATA_PREM_NULL' ");
					}
				}
				else {
					orgHql.append(" and ").append(orgPropName).append(" = 'IS_DATA_PREM_NULL' ");
				}
			}
		}
		else {
			orgHql.append(" and ").append(orgPropName).append(" = 'IS_DATA_PREM_NULL' ");
		}
		
		return orgHql.toString();
	}
	
	/**
	 * 获取机构和用户数据权限.
	 * 
	 * @param orgPropName
	 * @param userPropName
	 * @param params
	 * 
	 * @return string
	 */
	public static String getDataPermPredicates(String orgPropName, String userPropName, Map<String, Object> params) {
		return UserDataHQLPermsUtils.getDataPermPredicates(orgPropName, userPropName, null, params);
	}
	
	/**
	 * 获取机构和用户数据权限.
	 * 
	 * @param orgPropName
	 * @param userPropName
	 * @param type 空值或者其他都代表user, EMPLOYEE代表员工
	 * @param params
	 * 
	 * @return string
	 */
	public static String getDataPermPredicates(String orgPropName, String userPropName, String type, Map<String, Object> params) {
		
		if (StringUtils.isAnyBlank(orgPropName, userPropName)) {
			
			throw new ServiceException("企业属性或者用户属性为空, 不能进行查询");
		}
		else if (params == null) {
			throw new ServiceException("params cannot be null");
		}
		
		StringBuilder orgHql = new StringBuilder();

		UserProfile user = UserProfileUtils.getUserProfile();
		
		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {
			
			// 如果是超级管理员, 则不加入该权限认证, 非超级管理员, 则判断用户
			if (!user.isSuperAdmin()) {
				
				// 判断用户是否机构管理员用户, 如果是机构管理员, 则加载机构下所有ID
				if (WeightsConst.WEIGHTS_MANAGER == user.getWeights()) {
					
					List<String> orgIds = EIPService.getOrgTreeService().getOrgTreeNodeIds(user.getOrgId());
					orgIds.add(user.getOrgId());

					orgHql.append(" and ").append(orgPropName).append(" in (:prop_org_ids ) ");
					params.put("prop_org_ids", orgIds);
				}
				// 如果是用户级别, 则获取该用户所负责机构的ID, 如果没有负责机构的ID, 则获取自己的USER_ID作为查询条件
				else if (WeightsConst.WEIGHTS_USER == user.getWeights()) {
					
					// 获取该用户负责的机构IDS
					List<String> orgIds = EIPService.getDataPermService().getListOrgByUser(user.getUserId());
					
					if (orgIds != null && orgIds.size() > 0) {
						orgHql.append(" and ( ").append(orgPropName).append(" in (:prop_org_ids_user ) ");
						params.put("prop_org_ids_user", orgIds);
						
						if (StringUtils.isNotBlank(type) && StringUtils.equals(type, "EMPLOYEE")) {
							// 增加用户权限
							orgHql.append(" or ").append(userPropName).append(" = :user_prop_emp_id) ");
							params.put("user_prop_emp_id", user.getEmpId());
						}
						else {
							orgHql.append(" or ").append(userPropName).append(" = :user_prop_user_id) ");
							params.put("user_prop_user_id", user.getUserId());
						}
					}
					else {
						orgHql.append(" and ").append(orgPropName).append(" in (:prop_org_ids_user ) ");
						params.put("prop_org_ids_user", orgIds);
					}
				}
				else {
					orgHql.append(" and ").append(orgPropName).append(" = 'IS_DATA_PREM_NULL' ");
				}
			}
		}
		else {
			orgHql.append(" and ").append(orgPropName).append(" = 'IS_DATA_PREM_NULL' ");
		}
		
		return orgHql.toString();
	}
}
