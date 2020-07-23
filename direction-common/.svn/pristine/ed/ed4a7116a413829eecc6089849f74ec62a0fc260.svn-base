package com.direction.core.modules.sys.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.core.inf.sys.post.IPostService;
import com.direction.core.modules.sys.post.entity.Post;

// ~ File Information
// ====================================================================================================================

@Service
public class PostServiceImpl implements IPostService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private PostService postService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据状态获取岗位列表.
	 * 
	 * @see com.direction.core.inf.sys.post.IPostService#getPostList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Post> getPostList(String tenantId, String status) {
		return postService.getPostList(tenantId, status);
	}
}
