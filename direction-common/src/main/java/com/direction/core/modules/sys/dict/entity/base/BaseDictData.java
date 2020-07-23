package com.direction.core.modules.sys.dict.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 字典数据.
 * 
 * <pre>
 * 字典数据
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseDictData extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 5198141128577420633L;

	// ~ Fields
	// ==================================================================================================================
	@Id
	@GeneratedValue(generator = "sys_dict_data_pk")
	@GenericGenerator(name = "sys_dict_data_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "dict_type_id")
	private String dictTypeId;

	@Column(name = "dict_type")
	@NotBlank(message = "字典类型不能为空")
	private String dictType;

	@Column(name = "parent_id")
	private String parentId;

	@Column(name = "parent_ids")
	private String parentIds;

	@Column(name = "dict_label")
	private String dictLabel;

	@Column(name = "dict_value")
	@NotBlank(message = "字典值不能为空")
	private String dictValue;

	@Column(name = "is_leaf", columnDefinition = "CHAR")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean leaf;

	@Column(name = "level")
	private int level;

	@Column(name = "display_order")
	@NotNull(message = "字典排序不能为空")
	private Integer displayOrder;

	@Column(name = "css_class")
	private String cssClass;

	@Column(name = "ext_1")
	private String ext1;

	@Column(name = "ext_2")
	private String ext2;

	@Column(name = "ext_3")
	private String ext3;

	@Column(name = "ext_4")
	private String ext4;

	@Column(name = "ext_5")
	private String ext5;

	@Column(name = "ext_6")
	private String ext6;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "is_default_value", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean defaultValue;

	@Column(name = "is_sys", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean sys;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getDictType() {

		return dictType;
	}

	public String getDictTypeId() {

		return dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {

		this.dictTypeId = dictTypeId;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
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

	public String getDictLabel() {

		return dictLabel;
	}

	public void setDictLabel(String dictLabel) {

		this.dictLabel = dictLabel;
	}

	public String getDictValue() {

		return dictValue;
	}

	public void setDictValue(String dictValue) {

		this.dictValue = dictValue;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public Boolean isLeaf() {

		if (this.leaf == null) {
			this.leaf = false;
		}

		return leaf;
	}

	public void setLeaf(Boolean leaf) {

		this.leaf = leaf;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}

	public void setDictType(String dictType) {

		this.dictType = dictType;
	}

	public String getCssClass() {

		return cssClass;
	}

	public void setCssClass(String cssClass) {

		this.cssClass = cssClass;
	}

	public String getExt1() {

		return ext1;
	}

	public void setExt1(String ext1) {

		this.ext1 = ext1;
	}

	public String getExt2() {

		return ext2;
	}

	public void setExt2(String ext2) {

		this.ext2 = ext2;
	}

	public String getExt3() {

		return ext3;
	}

	public void setExt3(String ext3) {

		this.ext3 = ext3;
	}

	public String getExt4() {

		return ext4;
	}

	public void setExt4(String ext4) {

		this.ext4 = ext4;
	}

	public String getExt5() {

		return ext5;
	}

	public void setExt5(String ext5) {

		this.ext5 = ext5;
	}

	public String getExt6() {

		return ext6;
	}

	public void setExt6(String ext6) {

		this.ext6 = ext6;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public boolean isDefaultValue() {

		return defaultValue == null ? false : defaultValue;
	}

	public Boolean getDefaultValue() {

		return defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {

		this.defaultValue = defaultValue;
	}

	public Boolean getSys() {

		return sys;
	}

	public void setSys(Boolean sys) {

		this.sys = sys;
	}
}
