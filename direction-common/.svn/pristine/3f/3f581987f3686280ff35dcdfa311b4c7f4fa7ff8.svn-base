package com.direction.core.modules.sys.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.post.entity.Post;

// ~ File Information
// ====================================================================================================================

@Repository
public interface PostRepository extends JpaRepository<Post, String>, JpaSpecificationExecutor<Post> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID查询岗位.
	 * 
	 * @param postId
	 * @return
	 */
	@Query("from Post where postId = ?1")
	Post get(String postId);
	
	/**
	 * 根据状态查询岗位信息.
	 * 
	 * @param tenantId
	 * @param status
	 * @return
	 */
	@Query("from Post where tenantId=?1 and status= ?2 order by displayOrder asc")
	List<Post> getPostList(String tenantId, String status);
}
