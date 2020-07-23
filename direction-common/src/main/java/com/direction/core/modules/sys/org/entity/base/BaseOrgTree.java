package com.direction.core.modules.sys.org.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 组织树.
 * 
 * <pre>
 * 组织树
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseOrgTree extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 6805447389876889188L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_org_tree_pk")
	@GenericGenerator(name = "sys_org_tree_pk", strategy = "uuid")
	@Column(name = "tree_id", unique = true, nullable = false)
	private String treeId;

	@Column(name = "tree_name")
	private String treeName;

	@Column(name = "tree_code")
	private String treeCode;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "is_default", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean defaultTree;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getTreeId() {

		return treeId;
	}

	public void setTreeId(String treeId) {

		this.treeId = treeId;
	}

	public String getTreeName() {

		return treeName;
	}

	public void setTreeName(String treeName) {

		this.treeName = treeName;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public boolean isDefaultTree() {

		return defaultTree == null ? false : defaultTree;
	}

	public Boolean getDefaultTree() {

		return defaultTree;
	}

	public void setDefaultTree(Boolean defaultTree) {

		this.defaultTree = defaultTree;
	}

	public String getTreeCode() {

		return treeCode;
	}

	public void setTreeCode(String treeCode) {

		this.treeCode = treeCode;
	}
}
