package com.direction.core.modules.sys.post.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.post.entity.Post;
import com.direction.core.modules.sys.post.repository.PostRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class PostService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private PostRepository postRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID获取岗位信息.
	 * 
	 * @param postId
	 * @return
	 */
	public Post get(String postId) {
		
		if (StringUtils.isBlank(postId)) {
			return null;
		}

		return this.postRepository.get(postId);
	}
	
	/**
	 * 保存岗位信息.
	 * 
	 * @param post
	 */
	@Transactional(readOnly = false)
	public void save(Post post) {
		try {
			
			this.postRepository.save(post);
		} catch (Exception e) {
			throw new ServiceException("岗位保存失败, 错误信息: " + e.getMessage());
		}
	}
	
	/**
	 * 删除岗位信息.
	 * 
	 * @param postId
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String postId) {
		
		try {
			this.postRepository.deleteById(postId);
		} catch (Exception e) {
			throw new ServiceException("岗位删除失败, 错误信息: " + e.getMessage());
		}
	}
	
	/**
	 * 验证属性是否唯一.
	 * 
	 * @param tenantId
	 * @param propName
	 * @param propValue
	 * @param postId
	 * @return
	 */
	public ResultJson validateOnlyOne(String tenantId, String propName, String propValue, String postId) {
		
		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}
		
		Specification<Post> postParams = new Specification<Post>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(postId)) {
					
					return criteriaBuilder.and(
						criteriaBuilder.notEqual(root.get("postId").as(String.class), postId), 
						criteriaBuilder.equal(root.get("tenantId"), tenantId),
						criteriaBuilder.equal(root.get(propName).as(String.class), propValue)
					);
				}
				else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};
		
		if (this.postRepository.count(postParams) == 0) {
			return ResultJson.success();
		}
		
		return ResultJson.fail("该名称已存在, 请重新输入...");
	}
	
	/**
	 * 根据状态查询岗位信息.
	 * 
	 * @param tenantId
	 * @param status
	 * @return
	 */
	public List<Post> getPostList(String tenantId, String status) {
		return this.postRepository.getPostList(tenantId, status);
	}
	
	/**
	 * 岗位分页.
	 * 
	 * @param post
	 * @param page
	 * @return
	 */
	public VuePage<Post> findPage(Post post, VuePage<Post> page) {

		// 封装查询条件
		Specification<Post> postParams = new Specification<Post>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				if (post != null) {
					predicateList.add(criteriaBuilder.equal(root.get("tenantId"), post.getTenantId()));
				}
				
				if (post != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(post.getPostName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("postName").as(String.class), "%" + post.getPostName() + "%"),
						    criteriaBuilder.like(root.get("postNo").as(String.class), "%" + post.getPostName() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(post.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), post.getStatus()));
					}
					
					// 岗位类型
					if (StringUtils.isNotBlank(post.getPostType())) {
						predicateList.add(criteriaBuilder.equal(root.get("postType").as(String.class), post.getPostType()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<Post> postPage = postRepository.findAll(postParams, page.getPageable());
		page.setPage(postPage);

		return page;
	}
}
