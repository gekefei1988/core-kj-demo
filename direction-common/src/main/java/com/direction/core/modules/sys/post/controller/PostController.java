package com.direction.core.modules.sys.post.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.modules.sys.post.entity.Post;
import com.direction.core.modules.sys.post.service.PostService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 岗位控制层.
 * 
 * <pre>
 * 	岗位控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/post")
public class PostController extends BaseController {

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
	 * 分页查询.
	 * 
	 * @param post
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Post> list(Post post, VuePage<Post> page) {
		
		if (post != null && StringUtils.isBlank(post.getTenantId())) {
			post.setTenantId(UserProfileUtils.getTenantId());
		}
		
		return this.postService.findPage(post, page);
	}
	
	/**
	 * 保存岗位信息.
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(Post post) {
		
		if (post != null && StringUtils.isBlank(post.getTenantId())) {
			post.setTenantId(UserProfileUtils.getTenantId());
		}
		
		this.postService.save(post);
		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Post get(String id) {
		
		Post post = this.postService.get(id);
		
		if (post == null) {
			post = new Post();
			post.setDisplayOrder(0);
			post.setStatus(StatusConst.ENABLE);
			post.setTenantId(UserProfileUtils.getTenantId());
		}
		
		return post;
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String id) {
		return this.postService.validateOnlyOne(UserProfileUtils.getTenantId(), propName, propValue, id);
	}
	
	/**
	 * 删除.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		this.postService.delete(id);
		
		return success();
	}
}
