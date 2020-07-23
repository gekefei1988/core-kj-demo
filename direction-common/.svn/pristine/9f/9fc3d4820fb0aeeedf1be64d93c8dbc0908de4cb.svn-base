package com.direction.core.modules.sys.reg.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.image.CaptchaUtils;
import com.direction.common.utils.phone.TelephoneUtils;
import com.direction.common.utils.validation.HibernateValidationUtils;
import com.direction.core.modules.sys.role.service.UserRoleService;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;

// ~ File Information
// ====================================================================================================================

/**
 * 用户注册相关.
 * 
 * <pre>
 * 	用户注册相关, 找回密码等
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class RegService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private TenantRegService tenantRegService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 租户注册.
	 * 
	 * @param tenantReg
	 * @param verifyId
	 * @param verifyCode
	 * @param telephoneCode
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson tenantReg(TenantReg tenantReg, String verifyId, String verifyCode, String telephoneCode) {

		if (tenantReg == null) {
			return ResultJson.fail("租户对象为空");
		} else if (StringUtils.isBlank(verifyCode)) {
			return ResultJson.fail("图片验证码不能为空");
		} else if (StringUtils.isBlank(telephoneCode)) {
			return ResultJson.fail("手机验证码不能为空");
		} else if (!StringUtils.equals(tenantReg.getPassword(), tenantReg.getInitPwd())) {
			return ResultJson.fail("输入的密码和确认密码不相同");
		}

		ResultJson result = HibernateValidationUtils.validateEntity(tenantReg);

		// 如果实体验证不通过, 直接返回
		if (!result.isSuccess()) {
			return result;
		}

		// 验证 图片验证码
		if (!CaptchaUtils.contrastVerifyCode(verifyId, verifyCode)) {
			return ResultJson.fail("图片验证码输入错误或已过期");
		}

		// 验证手机验证码
		if (!TelephoneUtils.contrastVerifyCode("TENANT_REG", tenantReg.getTelephone(), telephoneCode)) {
			return ResultJson.fail("手机验证码输入错误或已过期");
		}

		// 保存注册的租户
		tenantRegService.saveOrUpdate(tenantReg);

		// 创建租户临时用户
		User user = userService.createUserByReg(tenantReg);

		// 给临时租户授权角色-临时租户
		this.userRoleService.assignTenantRoleUser(user.getUserId());

		return ResultJson.success();
	}

	/**
	 * 找回密码验证.
	 * 
	 * @param sessionId
	 * @param imgCode
	 * @param telephoneCode
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson getBackPwd(String sessionId, String imgCode, String telephoneCode) {

		if (StringUtils.isBlank(sessionId)) {
			return ResultJson.fail("未获取到请求的Session");
		} else if (StringUtils.isBlank(imgCode)) {
			return ResultJson.fail("图片验证码不能为空");
		} else if (StringUtils.isBlank(telephoneCode)) {
			return ResultJson.fail("手机验证码不能为空");
		}

		// TODO 验证 图片验证码

		// TODO 验证手机验证码

		return ResultJson.success();
	}

	/**
	 * 修改用户密码.
	 * 
	 * @param sessionId
	 * @param password
	 * @param surePassword
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson modifyUserPwd(String sessionId, String password, String surePassword) {

		if (StringUtils.isBlank(sessionId)) {
			return ResultJson.fail("未获取到请求的Session");
		} else if (StringUtils.isAnyBlank(password, surePassword)) {
			return ResultJson.fail("密码不能为空");
		}

		// TODO 验证密码是否被修改了-去session里获取是否已经修改过, 如果修改了不能再次修改

		return ResultJson.success();
	}
}
