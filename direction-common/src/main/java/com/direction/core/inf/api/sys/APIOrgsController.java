package com.direction.core.inf.api.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.tree.TreeNode;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.org.IOrgTreeService;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 组织机构相关API.
 * 
 * <pre>
 * 	组织机构相关API
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/api/sys/orgs")
public class APIOrgsController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private IOrgTreeService iOrgTreeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取行政组织树.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getXZOrgTree")
	public List<TreeNode> getXZOrgTree() {
		return this.iOrgTreeService.getXZOrgTree(UserProfileUtils.getTenantId());
	}
}
