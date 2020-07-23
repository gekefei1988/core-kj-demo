package com.direction.core.modules.sys.org.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.modules.sys.org.entity.OrgTree;
import com.direction.core.modules.sys.org.service.OrgTreeService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 组织树控制层.
 * 
 * <pre>
 * 组织树控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/org-tree")
public class OrgTreeController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private OrgTreeService orgTreeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取所有顶级组织.
	 * 
	 * @return
	 */
	@RequestMapping("/getAllOrgTrees")
	public List<OrgTree> getAllOrgTrees() {
		return this.orgTreeService.getTreesByTenant(UserProfileUtils.getTenantId());
	}
	
	/**
	 * 分页查询组织机构树.
	 * 
	 * @param orgTree
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<OrgTree> list(OrgTree orgTree, VuePage<OrgTree> page) {
		
		if (orgTree != null && StringUtils.isBlank(orgTree.getTenantId())) {
			orgTree.setTenantId(UserProfileUtils.getSearchTenantId());
		}
		
		return this.orgTreeService.findPage(orgTree, page);
	}
	
	/**
	 * 根据ID获取对象.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public OrgTree get(String id) {
		
		OrgTree orgTree = this.orgTreeService.get(id);
		
		if (orgTree == null) {
			orgTree = new OrgTree();
			orgTree.setDefaultTree(false);
			orgTree.setStatus(StatusConst.ENABLE);
			orgTree.setTenantId(UserProfileUtils.getTenantId());
		}
		
		return orgTree;
	}

	/**
	 * 保存组织树.
	 * 
	 * @param orgTree
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(OrgTree orgTree) {
		
		if (orgTree != null && StringUtils.isBlank(orgTree.getTenantId())) {
			orgTree.setTenantId(UserProfileUtils.getTenantId());
		}

		this.orgTreeService.save(orgTree);
		return success();
	}

	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param treeId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String treeId) {

		return this.orgTreeService.validateOnlyOne(UserProfileUtils.getTenantId(), propName, propValue, treeId);
	}
	
	/**
	 * 删除组织树.
	 * 
	 * @param treeId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String treeId) {
		
		this.orgTreeService.delete(treeId);
		return success();
	}
}
