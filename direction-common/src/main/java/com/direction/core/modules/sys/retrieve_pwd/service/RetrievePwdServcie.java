package com.direction.core.modules.sys.retrieve_pwd.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.image.CaptchaUtils;
import com.direction.common.utils.phone.TelephoneUtils;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;

// ~ File Information
// ====================================================================================================================

/**
 * 找回密码业务逻辑层.
 * 
 * <pre>
 * 找回密码业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class RetrievePwdServcie {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private TenantRegService tenantRegService;
	
	@Autowired
	private UserService userService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 第二步, 验证号码是否存在, 输入是否正确.
	 * 
	 * @param telephone
	 * @param verifyId
	 * @param verifyCode
	 * @param telephoneCode
	 * @return
	 */
	public ResultJson stepTwo(String telephone, String verifyId, String verifyCode, String telephoneCode) {

		if (StringUtils.isBlank(telephone)) {
			return ResultJson.fail("手机号码为空");
		}
		else if (StringUtils.isBlank(verifyCode)) {
			return ResultJson.fail("图片验证码不能为空");
		}
		else if (StringUtils.isBlank(telephoneCode)) {
			return ResultJson.fail("手机验证码不能为空");
		}
		
		// 验证 图片验证码
		if (!CaptchaUtils.contrastVerifyCode(verifyId, verifyCode)) {
			return ResultJson.fail("图片验证码输入错误或已过期");
		}

		// 验证手机验证码
		if (!TelephoneUtils.contrastVerifyCode(TelephoneUtils.MODULES_RETRIEVE_PWD_TWO, telephone, telephoneCode)) {
			return ResultJson.fail("手机验证码输入错误或已过期");
		}
		
		// 验证手机号码是否存在
		ResultJson result = this.tenantRegService.validateOnlyOne("telephone", telephone);
		
		// false 为存在,
		if (result.isSuccess()) {
			return ResultJson.fail("该手机号码不存在");
		}
		
		// 缓存中生成一个临时待修改的手机号码
		TelephoneUtils.putCache(TelephoneUtils.MODULES_RETRIEVE_PWD_THREE, telephone, telephoneCode, 10 * 60);
		
		return ResultJson.success();
	}
	
	/**
	 * 第三步, 保存新密码.
	 * 
	 * @param telephone
	 * @param telephoneCode
	 * @param password
	 * @param surePassword
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson stepThree(String telephone, String telephoneCode, String password, String surePassword) {
		
		if (StringUtils.isBlank(telephone)) {
			return ResultJson.fail("租户对象为空");
		}
		else if (StringUtils.isBlank(telephoneCode)) {
			return ResultJson.fail("手机验证码不能为空");
		}
		else if (StringUtils.isAnyBlank(password, surePassword)) {
			return ResultJson.fail("新密码或者确认密码为空");
		}

		// 验证手机验证码
		if (!TelephoneUtils.contrastVerifyCode(TelephoneUtils.MODULES_RETRIEVE_PWD_THREE, telephone, telephoneCode)) {
			return ResultJson.fail("手机验证码输入错误或已过期");
		}
		
		// 比对密码
		if (!StringUtils.equals(password, surePassword)) {
			return ResultJson.fail("新密码和确认密码不相同");
		}
		
		//TODO 修改用户新密码
		User user = userService.getUserByUserName(telephone);
		if (user == null) {
			return ResultJson.fail("用户不存在，无法进行修改");
		}
		
		user.setPassword(password);
		this.userService.save(user);
		
		return ResultJson.success();
	}
}
