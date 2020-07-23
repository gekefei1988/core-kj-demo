package com.direction.core.inf.sys.post;

import java.util.List;

import com.direction.core.modules.sys.post.entity.Post;

// ~ File Information
// ====================================================================================================================

public interface IPostService{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据状态获取岗位列表.
	 * 
	 * @param tenantId
	 * @param status
	 * @return
	 */
	List<Post> getPostList(String tenantId, String status);
}
