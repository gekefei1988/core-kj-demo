package com.direction.core.modules.sys.org.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

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
public class BaseOrgTreeNode extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 406605495686734845L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_org_tree_node_pk")
	@GenericGenerator(name = "sys_org_tree_node_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	// 所属组织树
	@Column(name = "tree_code")
	private String treeCode;

	// 引用节点
	@Column(name = "ref_id")
	private String refId;

	// 组织编号
	@Column(name = "node_code")
	@NotBlank(message = "机构编号不能为空")
	private String nodeCode;

	// 组织名称
	@Column(name = "node_name")
	@NotBlank(message = "机构名称不能为空")
	private String nodeName;

	// 简称
	@Column(name = "abbr_node_name")
	private String abbrNodeName;

	// 组织全名
	@Column(name = "full_node_name")
	private String fullNodeName;

	// 组织类型
	@Column(name = "node_type", columnDefinition = "CHAR")
	@NotBlank(message = "机构类型不能为空")
	private String nodeType;

	// 负责人
	@Column(name = "person")
	private String person;

	// 联系电话
	@Column(name = "telephone")
	private String telephone;

	// 电子邮箱
	@Column(name = "mail")
	private String mail;

	// 归属地
	@Column(name = "area_codes")
	private String areaCodes;

	// 归属地名称
	@Column(name = "area_names")
	private String areaNames;

	// 详细地址
	@Column(name = "address")
	private String address;

	// 邮政编码
	@Column(name = "zip_code")
	private String zipCode;

	// 节点级别
	@Column(name = "node_level")
	private Integer nodeLevel;

	// 父级ID
	@Column(name = "parent_id")
	private String parentId;

	// 所有父级ID
	@Column(name = "parent_ids")
	private String parentIds;

	// 显示顺序
	@Column(name = "display_order")
	private Integer displayOrder;

	// 状态
	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getTreeCode() {

		return treeCode;
	}

	public void setTreeCode(String treeCode) {

		this.treeCode = treeCode;
	}

	public String getRefId() {

		return refId;
	}

	public void setRefId(String refId) {

		this.refId = refId;
	}

	public String getNodeCode() {

		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {

		this.nodeCode = nodeCode;
	}

	public String getNodeName() {

		return nodeName;
	}

	public void setNodeName(String nodeName) {

		this.nodeName = nodeName;
	}

	public String getAbbrNodeName() {

		return abbrNodeName;
	}

	public void setAbbrNodeName(String abbrNodeName) {

		this.abbrNodeName = abbrNodeName;
	}

	public String getFullNodeName() {

		return fullNodeName;
	}

	public void setFullNodeName(String fullNodeName) {

		this.fullNodeName = fullNodeName;
	}

	public String getNodeType() {

		return nodeType;
	}

	public void setNodeType(String nodeType) {

		this.nodeType = nodeType;
	}

	public String getPerson() {

		return person;
	}

	public void setPerson(String person) {

		this.person = person;
	}

	public String getTelephone() {

		return telephone;
	}

	public void setTelephone(String telephone) {

		this.telephone = telephone;
	}

	public String getMail() {

		return mail;
	}

	public void setMail(String mail) {

		this.mail = mail;
	}

	public String getAreaCodes() {

		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {

		this.areaCodes = areaCodes;
	}

	public String getAreaNames() {

		return areaNames;
	}

	public void setAreaNames(String areaNames) {

		this.areaNames = areaNames;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

	public Integer getNodeLevel() {

		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {

		this.nodeLevel = nodeLevel;
	}

	public String getParentId() {

		return parentId;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public String getParentIds() {

		return parentIds;
	}

	public void setParentIds(String parentIds) {

		this.parentIds = parentIds;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
