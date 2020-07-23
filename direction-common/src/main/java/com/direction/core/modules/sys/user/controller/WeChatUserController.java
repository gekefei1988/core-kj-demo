package com.direction.core.modules.sys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.user.entity.WeChatUser;
import com.direction.core.modules.sys.user.service.WeChatUserService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 微信账号Controller.
 * 
 * <pre>
 * 	微信账号Controller
 * </pre>
 * 
 * @author Evan
 * @since V1.0
 *
 */
@RestController
@RequestMapping("brand/certificate/weChatUser")
public class WeChatUserController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private WeChatUserService weChatUserService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 列表查询(带分页).
	 * 
	 * @param weChatUser
	 * @param page
	 * @return VuePage
	 */
	@RequestMapping(value = "/list")
	public VuePage<WeChatUser> getList(WeChatUser weChatUser, VuePage<WeChatUser> page) {
		return weChatUserService.findPage(weChatUser, page);
	}
	
	/**
	 * 根据id查询.
	 * 
	 * @param weChatUserId
	 * @return WeChatUser
	 */
	@RequestMapping(value = "/get")
	public WeChatUser get(String id) {
		return weChatUserService.get(id);
	}
	
	/**
	 * 保存、修改.
	 * @param weChatUser
	 * @return ResultJson
	 */
	@RequestMapping("/save")
	public ResultJson save(WeChatUser weChatUser) {
		weChatUserService.save(weChatUser);
		return success();
	}
	
	/**
	 * 删除.
	 * 
	 * @param weChatUserId
	 * @return ResultJson
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		weChatUserService.delete(id);
		return success();
	}
}