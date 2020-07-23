package com.direction.core.modules.sys.data_perm.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.data_perm.entity.DataPermKey;
import com.direction.core.modules.sys.data_perm.service.DataPermService;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 数据权限控制层.
 * 
 * <pre>
 * 数据权限控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/data-perm")
public class DataPermController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private DataPermService dataPermService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 查询集合.
	 * 
	 * @param orgId
	 * @param tenantId
	 * @return
	 */
	@RequestMapping("/list")
	public ResultJson list(String orgId, String tenantId, String status, String userName) {
		
		ResultJson result = ResultJson.success();
		result.setBody(this.dataPermService.getList(orgId, tenantId, status, userName));
		
		return result;
	}
	
	/**
	 * 保存数据权限.
	 * 
	 * @param tenantId
	 * @param orgId
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(String tenantId, String orgId, String userIds) {
		
		this.dataPermService.saveDataPerms(tenantId, orgId, userIds);
		
		return success();
	}
	
	/**
	 * 删除数据.
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/deleteDataPerm")
	public ResultJson deleteDataPerm(String userId, String orgId) {
		
		if (StringUtils.isAnyBlank(orgId, userId)) {
			return error();
		}
		else {
			this.dataPermService.deleteById(new DataPermKey(userId, orgId));
		}
		
		return success();
	}
}
