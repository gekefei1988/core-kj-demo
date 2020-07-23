package com.direction.core.inf.sys.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

/**
 * 码表.
 * 
 * <pre>
 * 	码表
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
public class DictTable implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 4264546007689738643L;

	// ~ Fields
	// ==================================================================================================================

	private String type;

	private String name;

	private List<DictTableItem> children = new ArrayList<DictTableItem>();

	// ~ Constructors
	// ==================================================================================================================
	
	public DictTable() {

	}
	
	public DictTable(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public DictTable(String type, String name, List<DictTableItem> children) {
		this.type = type;
		this.name = name;
		this.children = children;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public List<DictTableItem> getChildren() {

		return children;
	}

	public void setChildren(List<DictTableItem> children) {

		this.children = children;
	}
}
