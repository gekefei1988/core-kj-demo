package com.direction.core.modules.sys.data_perm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.modules.sys.data_perm.entity.DataPerm;
import com.direction.core.modules.sys.data_perm.entity.DataPermKey;
import com.direction.core.modules.sys.data_perm.entity.DataPermVO;
import com.direction.core.modules.sys.data_perm.repository.DataPermRepository;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.service.OrgTreeNodeService;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 数据权限逻辑层.
 * 
 * <pre>
 * 	数据权限逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class DataPermService extends BaseService<DataPermRepository, DataPerm, DataPermKey> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private OrgTreeNodeService orgTreeNodeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 查询用户信息.
	 * 
	 * @param orgId
	 * @param tenantId
	 * @param status
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DataPermVO> getList(String orgId, String tenantId, String status, String userName) {
		
		if (StringUtils.isAnyBlank(orgId, tenantId)) {
			return new ArrayList<DataPermVO>();
		}
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder hql = new StringBuilder("select new com.direction.core.modules.sys.data_perm.entity.DataPermVO(")
				.append("dp.userId, u.orgId, u.nickName, u.userName, u.status) ")
				.append("from User u ")
				.append("inner join DataPerm dp on dp.userId = u.userId ")
				.append("and dp.orgId = :orgId and dp.tenantId = :tenantId ");
		
		params.put("orgId", orgId);
		params.put("tenantId", tenantId);
		
		if (StringUtils.isNotBlank(status)) {
			hql.append(" and u.status = :status ");
			params.put("status", status);
		}
		
		if (StringUtils.isNotBlank(userName)) {
			hql.append(" and (u.userName like :userName or u.nickName like :userName )");
			userName = "%" + userName + "%";
			params.put("userName", userName);
		}
		
		return (List<DataPermVO>) this.findListByParams(hql.toString(), params);
	}
	
	/**
	 * 保存数据权限.
	 * 
	 * @param tenantId
	 * @param orgId
	 * @param userIds
	 */
	@Transactional(readOnly = false)
	public void saveDataPerms(String tenantId, String orgId, String userIds) {
		
		if (StringUtils.isNoneBlank(tenantId, orgId, userIds)) {
			
			// 获取机构
			OrgTreeNode treeNode = this.orgTreeNodeService.get(orgId);
			
			String orgIds = "";
			
			if (treeNode == null) {
				return;
			}
			
			// 机构全路径id
			orgIds = treeNode.getParentIds() + "," + treeNode.getId();
			
			List<DataPerm> perms = new ArrayList<>();
			
			// 拆分用户ID
			String[] userArray = StringUtils.split(userIds, ",");
			
			for (String userId : userArray) {
				perms.add(new DataPerm(tenantId, userId, orgId, orgIds));
			}
			
			if (perms.size() > 0) {
				this.saveAll(perms);
			}
		}
	}
	
	/**
	 * 获取权限数据.
	 * 
	 * @param userId
	 * @return
	 */
	public List<DataPerm> getListByUser(String userId) {
		
		if (StringUtils.isBlank(userId)) {
			return new ArrayList<>();
		}
		
		return this.getBaseRepository().getListByUser(userId);
	}
	
	/**
	 * 查询负责机构.
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getListOrgByUser(String userId) {
		
		if (StringUtils.isBlank(userId)) {
			return new ArrayList<>();
		}
		
		return this.getBaseRepository().getListOrgByUser(userId);
	}
	
	/**
	 * 获取权限数据.
	 * 
	 * @param userId
	 * @return
	 */
	public Set<DataPerm> getSubList(String userId) {
		
		Set<DataPerm> results = new HashSet<DataPerm>();
		
		if (StringUtils.isBlank(userId)) {
			return results;
		}
		
		// 获取数据权限
		List<DataPerm> list = this.getBaseRepository().getListByUser(userId);
		
		if (list != null && list.size() > 0) {
			List<DataPerm> subList = null;
			for (DataPerm perm : list) {
				
				// 查询下级
				subList = this.getBaseRepository().getSubList(perm.getOrgIds());
				if (subList != null && subList.size() > 0) {
					results.addAll(subList);
				}
			}
		}
		
		return results;
	}
	
	/**
	 * 查询负责机构.
	 * 
	 * @param userId
	 * @return
	 */
	public Set<String> getSubListOrg(String userId) {
		
		Set<String> results = new HashSet<String>();
		
		if (StringUtils.isBlank(userId)) {
			return results;
		}
		
		// 获取数据权限
		List<DataPerm> list = this.getBaseRepository().getListByUser(userId);
		
		if (list != null && list.size() > 0) {
			List<String> subList = null;
			for (DataPerm perm : list) {
				
				// 查询下级
				subList = this.getBaseRepository().getSubListOrg(perm.getOrgIds());
				if (subList != null && subList.size() > 0) {
					results.addAll(subList);
				}
			}
		}
		
		return results;
	}
}
