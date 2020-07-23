package com.direction.core.modules.sys.reg.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.reg.service.RegService;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantType;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 租户注册页面.
 * 
 * <pre>
 * 	租户注册页面
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/reg")
public class RegController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private RegService regService;

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
	@RequestMapping(value = "/tenantReg", method = RequestMethod.POST)
	public ResultJson tenantReg(TenantReg tenantReg, String verifyId, String verifyCode, String telephoneCode) {
		
		// 赋值类型
		tenantReg.setTenantType(TenantType.PERSONAL);
		
		// 赋值状态
		tenantReg.setStatus(TenantRegStauts.DEFAULT);
		tenantReg.setRegDate(new Date());
		tenantReg.setTenantName(tenantReg.getTelephone());
		
		return regService.tenantReg(tenantReg, verifyId, verifyCode, telephoneCode);
	}
	
	/**
	 * 密码找回.
	 * 
	 * @param imgCode
	 * @param telephoneCode
	 * @return
	 */
	@RequestMapping(value = "/getBackPwd", method = RequestMethod.POST)
	public ResultJson getBackPwd(String imgCode, String telephoneCode) {
		
		String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
		return regService.getBackPwd(sessionId, imgCode, telephoneCode);
	}
	
	/**
	 * 修改租户密码.
	 * 
	 * @param password
	 * @param surePassword
	 * @return
	 */
	@RequestMapping(value = "/modifyUserPwd", method = RequestMethod.POST)
	public ResultJson modifyUserPwd(String password, String surePassword) {
		String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
		return regService.modifyUserPwd(sessionId, password, surePassword);
	}
}
