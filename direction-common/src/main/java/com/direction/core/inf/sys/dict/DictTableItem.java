package com.direction.core.inf.sys.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

/**
 * 码表元素.
 * 
 * <pre>
 * 码表元素
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@JsonIgnoreProperties({
  "hibernateLazyInitializer",
  "handler"
})
public class DictTableItem implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -840552923568637874L;

	// ~ Fields
	// ==================================================================================================================

	private String id;

	private String parentId;

	private String label;

	private String value;

	private int level;

	private String cssClass;

	private int displayOrder;

	private String ext1;

	private String ext2;

	private String ext3;

	private String ext4;

	private String ext5;

	private String ext6;

	private boolean defaultValue;

	private String status;

	List<DictTableItem> children = new ArrayList<DictTableItem>();

	// ~ Constructors
	// ==================================================================================================================

	public DictTableItem(IDictData dictData) {

		this.id = dictData.getId();
		this.parentId = dictData.getParentId();
		this.label = dictData.getDictLabel();
		this.value = dictData.getDictValue();
		this.level = dictData.getLevel();
		this.cssClass = dictData.getCssClass();
		this.displayOrder = dictData.getDisplayOrder();
		this.ext1 = dictData.getExt1();
		this.ext2 = dictData.getExt2();
		this.ext3 = dictData.getExt3();
		this.ext4 = dictData.getExt4();
		this.ext5 = dictData.getExt5();
		this.ext6 = dictData.getExt6();
		this.defaultValue = dictData.isDefaultValue();
		this.status = dictData.getStatus();
	}

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
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

	public String getLabel() {

		return label;
	}

	public void setLabel(String label) {

		this.label = label;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public String getCssClass() {

		return cssClass;
	}

	public void setCssClass(String cssClass) {

		this.cssClass = cssClass;
	}

	public int getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {

		this.displayOrder = displayOrder;
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

	public boolean isDefaultValue() {

		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {

		this.defaultValue = defaultValue;
	}

	public List<DictTableItem> getChildren() {

		return children;
	}

	public void setChildren(List<DictTableItem> children) {

		this.children = children;
	}
}
