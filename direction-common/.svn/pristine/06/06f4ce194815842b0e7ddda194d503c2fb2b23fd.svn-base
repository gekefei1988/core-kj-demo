package com.direction.core.modules.sys.retrieve_pwd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.retrieve_pwd.service.RetrievePwdServcie;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 密码找回功能.
 * 
 * <pre>
 * 	密码找回功能
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/retrieve-pwd")
public class RetrievePwdController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private RetrievePwdServcie retrievePwdServcie;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 密码找回.
	 * 
	 * @param telephone
	 * @param verifyId
	 * @param verifyCode
	 * @param telephoneCode
	 * @return
	 */
	@RequestMapping(value = "/stepTwo", method = RequestMethod.POST)
	public ResultJson stepTwo(String telephone, String verifyId, String verifyCode, String telephoneCode) {
		return this.retrievePwdServcie.stepTwo(telephone, verifyId, verifyCode, telephoneCode);
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
	@RequestMapping(value = "/stepThree", method = RequestMethod.POST)
	public ResultJson stepThree(String telephone, String telephoneCode, String password, String surePassword) {
		return this.retrievePwdServcie.stepThree(telephone, telephoneCode, password, surePassword);
	}
}
