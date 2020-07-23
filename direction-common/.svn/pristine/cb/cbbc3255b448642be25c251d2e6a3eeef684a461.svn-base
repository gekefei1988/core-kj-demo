package com.direction.common.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

@JsonIgnoreProperties({
  "hibernateLazyInitializer",
  "handler"
})
public class TreeNode implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -6814570742389710106L;

	// ~ Fields
	// ==================================================================================================================

	private String nodeId;

	private String nodeCode;

	private String parentNodeId;

	private String nodeType;

	private Integer nodeLevel;

	private String title;

	private String fullTitle;

	private String icon;

	private String status;

	private boolean expand;

	private boolean disabled;

	private boolean selected;

	private boolean checked;

	private List<TreeNode> children = new ArrayList<TreeNode>();

	// ~ Constructors
	// ==================================================================================================================

	public TreeNode() {

	}

	public TreeNode(String nodeId, String title) {

		this.nodeId = nodeId;
		this.title = title;
	}

	public TreeNode(String nodeId, String title, String parentNodeId) {

		this.nodeId = nodeId;
		this.title = title;
		this.parentNodeId = parentNodeId;
	}

	public TreeNode(String nodeId, String title, String parentNodeId, String nodeType) {

		this.nodeId = nodeId;
		this.title = title;
		this.parentNodeId = parentNodeId;
		this.nodeType = nodeType;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getNodeId() {

		return nodeId;
	}

	public String getId() {

		return nodeId;
	}

	public String getNodeCode() {

		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {

		this.nodeCode = nodeCode;
	}

	public void setNodeId(String nodeId) {

		this.nodeId = nodeId;
	}

	public String getNodeType() {

		return nodeType;
	}

	public void setNodeType(String nodeType) {

		this.nodeType = nodeType;
	}

	public Integer getNodeLevel() {

		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {

		this.nodeLevel = nodeLevel;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getIcon() {

		return icon;
	}

	public void setIcon(String icon) {

		this.icon = icon;
	}

	public boolean isExpand() {

		return expand;
	}

	public void setExpand(boolean expand) {

		this.expand = expand;
	}

	public boolean isDisabled() {

		return disabled;
	}

	public void setDisabled(boolean disabled) {

		this.disabled = disabled;
	}

	public boolean isSelected() {

		return selected;
	}

	public void setSelected(boolean selected) {

		this.selected = selected;
	}

	public boolean isChecked() {

		return checked;
	}

	public void setChecked(boolean checked) {

		this.checked = checked;
	}

	public List<TreeNode> getChildren() {

		return children;
	}

	public void setChildren(List<TreeNode> children) {

		this.children = children;
	}

	public String getParentNodeId() {

		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {

		this.parentNodeId = parentNodeId;
	}

	public String getFullTitle() {

		return fullTitle;
	}

	public void setFullTitle(String fullTitle) {

		this.fullTitle = fullTitle;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
