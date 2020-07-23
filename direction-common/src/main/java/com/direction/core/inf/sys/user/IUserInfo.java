package com.direction.core.inf.sys.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.direction.core.inf.WeightsConst;

// ~ File Information
// ====================================================================================================================

/**
 * 用户接口.
 * 
 * <pre>
 * 	用户接口
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public interface IUserInfo extends Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	// 获取用户ID
	String getUserId();
	
	// 显示名称
	String getNickName();
	
	// 姓名
	String getEmpName();
	
	// 名称
	String getName();
	
	// 账号
	String getUserName();
	
	// 密码
	String getPassword();
	
	// 用户头像
	default String getAvatar() {
		return "/upload/images/default_user.png";
	}
	
	// 状态
	String getStatus();
	
	// 最后登陆错误
	Date getLastPwdUpdate();
	
	// 错误次数
	int getLoginErrorCount();
	
	// 允许PC登陆
	boolean isAllowPC();
	
	// 允许移动端登陆
	boolean isAllowMobile();
	
	// 允许微信登陆
	boolean isAllowWechat();
	
	default String getEmpId() {
		return null;
	}
	
	// 用户类型
	default String getUserType() {
		return UserType.EMPLOYEE;
	}
	
	// 个性签名
	String getSign();
	
	// 机构ID
	String getOrgId();
	
	// 权重
	default int getWeights() {
		return WeightsConst.WEIGHTS_USER;
	}
	
	// 最后登录时间
	Date getLastLoginDate();
	
	// 租户ID
	String getTenantId();
	
	// ~ classs
	// ===================================================================================================================

	
	/**
	 * 用户状态.
	 * 
	 * <pre>
	 * 	用户状态
	 * </pre>
	 * 
	 * @author liutao
	 * @since $Rev$
	 *
	 */
	public static class UserStatus {
		
		// 启用
		public static final String ENABLE = "0";
		
		// 停用
		public static final String DISABLED = "1";
		
		// 过期
//		public static final String EXPIRED = "3";
		
		public static Map<String, String> getUserStatusMap() {

			Map<String, String> maps = new HashMap<String, String>();
			maps.put(ENABLE, "启用");
			maps.put(DISABLED, "停用");
//			maps.put(EXPIRED, "过期");
			return maps;
		}
	}
	
	/**
	 * 用户类型.
	 * 
	 * <pre>
	 * 	用户类型
	 * </pre>
	 * 
	 * @author liutao
	 * @since $Rev$
	 *
	 */
	public static class UserType {
		
		// 员工用户
		public static final String EMPLOYEE = "0";
		
		// 管理员
		public static final String MANAGE = "1";
		
		// 租户临时用户
		public static final String TENANT = "2";
		
		// 普通用户
		public static final String USER = "3";
		
		// guest, 匿名用户, 访客, 用来演示用等
		public static final String GUEST = "4";
		
		// 超级管理员
		public static final String SUPER_ADMIN = "9";
		
		public static Map<String, String> getUserTypeMap() {

			Map<String, String> maps = new HashMap<String, String>();
			maps.put(EMPLOYEE, "员工用户");
			maps.put(MANAGE, "管理员");
			maps.put(TENANT, "临时租户");
			maps.put(USER, "普通用户");
			maps.put(GUEST, "访客");
			maps.put(SUPER_ADMIN, "超级管理员");
			return maps;
		}
	}
}
