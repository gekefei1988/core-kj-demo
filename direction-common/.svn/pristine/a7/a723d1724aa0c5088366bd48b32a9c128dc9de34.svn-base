package com.direction.core.modules.sys.post.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.MapUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.modules.sys.post.entity.base.BasePost;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_post")
public class Post extends BasePost {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -2685522228368891816L;
	private static final String DICT_TYPE = "sys_post_type";

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {
		return this.getPostId();
	}
	
	public String getPostTypeDesc() {
		return MapUtils.getString(EIPService.getDictService().getTopValueMap(DICT_TYPE), this.getPostType());
	}
}
