package com.direction.core.modules.sys.verify_code.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.image.CaptchaUtils;
import com.direction.common.utils.phone.TelephoneUtils;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 生成验证码控制层.
 * 
 * <pre>
 * 	生成验证码控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/verify-code")
public class VerifyController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantRegService tenantRegService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 生成验证码.
	 * 
	 */
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public void verifyCodeGenerate(String verifyId, HttpServletResponse response) {
		
		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		
		// 设置响应头信息，告诉浏览器不要缓存此内容
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expire", 0);
    
		try {
			String code = CaptchaUtils.generateCaptcha(response.getOutputStream());
	    CaptchaUtils.putCache(verifyId, code);
		} catch (IOException e) {
			System.err.println("验证码生成异常...");
		}
	}
	
	/**
	 * 发送手机验证码.
	 * 
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "/sendPhoneCode", method = RequestMethod.POST)
	public ResultJson sendPhoneCode(String telephone, String modules) {
		
		if (StringUtils.isBlank(telephone)) {
			return error("未获取到手机号码，不能发送手机验证码");
		}
		
		// 保存手机验证码
		String code = TelephoneUtils.genPhoneCode();
		
		if (StringUtils.isNotBlank(modules)) {
			TelephoneUtils.putCache(modules, telephone, code, 300);
		}
		else {
			TelephoneUtils.putCache("TENANT_REG", telephone, code, 300);
		}
		
		System.out.println(code);
		
		// TODO 发送手机验证码
		
		return success();
	}
	
	/**
	 * 验证码验证.
	 * 
	 * @param verifyId
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultJson verifyCheck(String verifyId, String verifyCode) {
		
//		if (!CaptchaUtils.contrastVerifyCode(verifyId, verifyCode)) {
//			return error("验证码输入错误或者已过期");
//		}
		
		return success();
	}
	
	/**
	 * 验证手机号码的唯一性, 是否已经注册过.
	 * 
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "/phoneOnlyOne", method = RequestMethod.POST)
	public ResultJson phoneOnlyOne(String telephone) {
		
		// 电话号码是否已经被注册
		ResultJson result = tenantRegService.validateOnlyOne("telephone", telephone);
		
		return result;
	}
	
	/**
	 * 手机验证码验证.
	 * 
	 * @param telephone
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value = "/phoneCheck", method = RequestMethod.POST)
	public ResultJson phoneCheck(String telephone, String phoneCode) {
		
		if (!TelephoneUtils.contrastVerifyCode("TENANT_REG", telephone, phoneCode)) {
			return error("验证码输入错误或者已过期");
		}
		
		return success();
	}
}
