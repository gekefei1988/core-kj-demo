package com.direction.common.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// ~ File Information
// ====================================================================================================================

/**
 * 级联控件.
 * 
 * <pre>
 * 级联控件
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class Cascader implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 2053286179704477144L;

	// ~ Fields
	// ==================================================================================================================

	private String label;

	private String value;

	private String code;

	private String parentCode;

	private List<Cascader> children = new ArrayList<Cascader>();

	// ~ Constructors
	// ==================================================================================================================

	public Cascader() {

	}

	public Cascader(String label, String value) {

		this.label = label;
		this.value = value;
	}

	public Cascader(String label, String value, String code, String parentCode) {

		this.label = label;
		this.value = value;
		this.code = code;
		this.parentCode = parentCode;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getLabel() {

		return label;
	}

	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getParentCode() {

		return parentCode;
	}

	public void setParentCode(String parentCode) {

		this.parentCode = parentCode;
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

	public List<Cascader> getChildren() {

		return children;
	}

	public void setChildren(List<Cascader> children) {

		this.children = children;
	}
}
