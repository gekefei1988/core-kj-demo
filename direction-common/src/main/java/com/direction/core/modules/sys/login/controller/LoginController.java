package com.direction.core.modules.sys.login.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.image.CaptchaUtils;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.login.util.WeChatUtils;
import com.direction.core.modules.sys.user.entity.WeChatSession;
import com.direction.security.shiro.token.ShiroUsernamePasswordToken;
import com.direction.spring.mvc.controller.BaseController;

/**
 * 登陆控制层.
 * 
 * <pre>
 * 登陆控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	// ~ Constructors
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	/**
	 * 用户登陆.
	 * 
	 * @param userName
	 * @param password
	 * @param verifyId
	 * @param verifyCode
	 * @param client
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultJson login(String userName, String password, String verifyId, String verifyCode, String client) {

		ResultJson result = ResultJson.success();
		
		// 验证码比对
//		if (!CaptchaUtils.contrastVerifyCode(verifyId, verifyCode)) {
//			result.setSuccess(false);
//			result.setMsg("验证码输入错误或者已过期");
//			return result;
//		}

		try {

			// 在认证提交前准备 token（shiro验证令牌）
			ShiroUsernamePasswordToken token = new ShiroUsernamePasswordToken(userName, password, client);

			// 从SecurityUtils里边创建一个 subject
			Subject subject = SecurityUtils.getSubject();

			UserProfile userProfile = (UserProfile) subject.getPrincipal();

			// 重新登陆, 先登出
			if (userProfile != null) {
				subject.logout();
			}

			// 执行认证登陆
			subject.login(token);
			
			result.setBody(UserProfileUtils.getUserProfile().getToken());
			
			logger.info("用户[" + userName + "]登录成功");
		} catch (Exception e) {
			logger.info("用户[" + userName + "]登录失败");
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}
	
	/**
	 * 用户登陆.
	 * 
	 * @param userName
	 * @param password
	 * @param verifyId
	 * @param verifyCode
	 * @param client
	 * @return
	 */
	@RequestMapping(value = "/login-admin", method = RequestMethod.POST)
	public ResultJson loginAdmin(String userName, String password, String verifyId, String verifyCode, String client) {

		ResultJson result = ResultJson.success();
		
		// 验证码比对
	/*	if (!CaptchaUtils.contrastVerifyCode(verifyId, verifyCode)) {
			result.setSuccess(false);
			result.setMsg("验证码输入错误或者已过期");
			return result;
		}
*/
		try {

			// 在认证提交前准备 token（shiro验证令牌）
			ShiroUsernamePasswordToken token = new ShiroUsernamePasswordToken(userName, password, client, true);

			// 从SecurityUtils里边创建一个 subject
			Subject subject = SecurityUtils.getSubject();

			UserProfile userProfile = (UserProfile) subject.getPrincipal();

			// 重新登陆, 先登出
			if (userProfile != null) {
				subject.logout();
			}

			// 执行认证登陆
			subject.login(token);
			result.setBody(UserProfileUtils.getUserProfile().getToken());
			
			logger.info("用户[" + userName + "]登录成功");
		} catch (Exception e) {
			logger.info("用户[" + userName + "]登录失败");
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}
	
	/**
	 * 用户登陆.
	 * 
	 * @param userName
	 * @param password
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/wclogin")
	public ResultJson login(String code) {
		
		ResultJson result = ResultJson.success();
		
		try {
			
			WeChatSession weChatSession = WeChatUtils.getWeChatSession(code);
			String openid = null;
			
			if (weChatSession != null) {
				openid = weChatSession.getOpenid();
			}
			
			if (StringUtils.isBlank(openid)) {
				throw new java.lang.Exception("获取微信权限失败.");
			}
			
			// 在认证提交前准备 token（shiro验证令牌）
			ShiroUsernamePasswordToken token = new ShiroUsernamePasswordToken(weChatSession);
			
			// 从SecurityUtils里边创建一个 subject
			Subject subject = SecurityUtils.getSubject();
			
			UserProfile userProfile = (UserProfile) subject.getPrincipal();
			
			// 重新登陆, 先登出
			if (userProfile != null) {
				subject.logout();
			}
			
			// 执行认证登陆
			subject.login(token);
			result.setBody(UserProfileUtils.getUserProfile().getToken());
			
			logger.info("用户[" + userProfile.getUserName() + "]登录成功");
		} catch (Exception e) {
			logger.info("用户[" + code + "]登录失败");
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取登陆的用户信息.
	 * 
	 * @return
	 */
	@GetMapping(value = "/login/getUserInfo")
	public UserProfile getUserInfo() {
		return UserProfileUtils.getUserProfile();
	}

	/**
	 * 用户登出.
	 * 
	 * @return
	 */
	@GetMapping(value = "/logout")
	public ResultJson logout() {
		SecurityUtils.getSubject().logout();
		return ResultJson.success();
	}
}
