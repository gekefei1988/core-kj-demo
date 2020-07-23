package com.direction.core.modules.sys.org.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.modules.sys.org.entity.base.BaseOrgTreeNode;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_org_tree_node")
public class OrgTreeNode extends BaseOrgTreeNode implements IOrgTreeNode {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -2404569172022519528L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 节点类型名称.
	 * 
	 * @return
	 */
	public String getNodeTypeName() {
		return EIPService.getDictService().getTopValueMap("sys-org-category").get(this.getNodeType());
	}
	
	/**
	 * 节点图标.
	 * 
	 * @return
	 */
	public String getNodeIcon() {
		return EIPService.getDictService().getTopItemMap("sys-org-category").get(this.getNodeType()).getCssClass();
	}
	
	/**
	 * 机构名称.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeNode#getOrgName()
	 */
	@Override
	@Transient
	public String getOrgName() {
		return this.getNodeName();
	}

	/**
	 * 机构ID.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeNode#getOrgId()
	 */
	@Override
	@Transient
	public String getOrgId() {
		return this.getId();
	}

	/**
	 * 机构组织树编号.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeNode#getOrgTreeCode()
	 */
	@Override
	@Transient
	public String getOrgTreeCode() {
		return this.getTreeCode();
	}

	/**
	 * 机构编号.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeNode#getOrgCode()
	 */
	@Override
	@Transient
	public String getOrgCode() {
		return getNodeCode();
	}
}
