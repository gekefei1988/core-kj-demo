package com.direction.common.utils.phone;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;

// ~ File Information
// ====================================================================================================================

public class TelephoneUtils {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static Random random = new Random();

	// ~ Fields
	// ==================================================================================================================
	
	// 注册
	public static final String MODULES_REG = "TENANT_REG";
	
	// 密码找回步骤2
	public static final String MODULES_RETRIEVE_PWD_TWO = "RETRIEVE-PWD-2";
	
	// 密码找回步骤3
	public static final String MODULES_RETRIEVE_PWD_THREE = "RETRIEVE-PWD-3";

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 生成手机验证码.
	 * 
	 * @return
	 */
	public static String genPhoneCode() {
		
		String phoneCode = "";
		
		for (int i = 0; i < 6; i++) {
			phoneCode += random.nextInt(10);
		}
		
		return phoneCode;
	}

	/**
	 * 验证码放入缓存, 缓存时间默认300秒.
	 * 
	 * @param telephone
	 * @param code
	 */
	public static void putCache(String telephone, String code) {
		TelephoneUtils.putCache(telephone, code, 300);
	}
	
	/**
	 * 验证码放入缓存.
	 * 
	 * @param telephone
	 * @param code
	 * @param cacheSeconds
	 */
	public static void putCache(String telephone, String code, int cacheSeconds) {
		CacheSysManageUtils.put(CacheSysModulesCategory.VERIFY_TELEPHONE, "TELEPHONE_" + telephone, code, cacheSeconds);
	}
	
	/**
	 * 验证码放入缓存.
	 * 
	 * @param modules
	 * @param telephone
	 * @param code
	 * @param cacheSeconds
	 */
	public static void putCache(String modules, String telephone, String code, int cacheSeconds) {
		CacheSysManageUtils.put(CacheSysModulesCategory.VERIFY_TELEPHONE, "TELEPHONE_" + modules + telephone, code, cacheSeconds);
	}
	
	/**
	 * 验证码比对.
	 * 
	 * @param telephone
	 * @param code
	 * @return
	 */
	public static boolean contrastVerifyCode(String telephone, String code) {
		
		boolean isSuccess = false;
		
		if (StringUtils.isAnyBlank(telephone, code)) {
			return isSuccess;
		}
		
		// 缓存中获取验证码
		String verifyCode = (String) CacheSysManageUtils.get(CacheSysModulesCategory.VERIFY_TELEPHONE, "TELEPHONE_" + telephone);
		
		if (StringUtils.isNotBlank(verifyCode)) {
			isSuccess = StringUtils.equalsIgnoreCase(verifyCode, code);
		}
		
		return isSuccess;
	}
	
	/**
	 * 验证码比对.
	 * 
	 * @param modules
	 * @param telephone
	 * @param code
	 * @return
	 */
	public static boolean contrastVerifyCode(String modules, String telephone, String code) {
		
		boolean isSuccess = false;
		
		if (StringUtils.isAnyBlank(modules, telephone, code)) {
			return isSuccess;
		}
		
		// 缓存中获取验证码
		String verifyCode = (String) CacheSysManageUtils.get(CacheSysModulesCategory.VERIFY_TELEPHONE, "TELEPHONE_" + modules + telephone);
		
		if (StringUtils.isNotBlank(verifyCode)) {
			isSuccess = StringUtils.equalsIgnoreCase(verifyCode, code);
		}
		
		return isSuccess;
	}
}
