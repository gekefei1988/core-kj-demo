package com.direction.core.inf.sys.user;

import java.io.Serializable;
import java.util.List;

import com.direction.core.inf.WeightsConst;
import com.direction.core.inf.sys.role.IRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

/**
 * 用户资料.
 * 
 * <pre>
 * 用户资料
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@JsonIgnoreProperties({
  "hibernateLazyInitializer",
  "handler"
})
public class UserProfile implements Serializable {

	private static final long serialVersionUID = -3993301875638806309L;

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@JsonIgnore
	private IUserInfo userInfo;

	// 角色列表
	private List<String> access;

	// token
	private String token;

	// 用户头像
	private String avatar;

	// 用户ID
	private String userId;

	// 用户昵称, 显示名称
	private String nickName;

	// 登陆账号
	private String userName;
	
	// 姓名
	private String empName;
	
//	//是否监管
//	private boolean gov;

	// ~ Constructors
	// ==================================================================================================================

	public UserProfile(IUserInfo userInfo, List<String> access, String token) {

		this.userInfo = userInfo;
		this.access = access;
		this.token = token;
	}

	// ~ Methods
	// ==================================================================================================================

	public List<String> getAccess() {
		return access;
	}

	public String getToken() {
		return token;
	}

	public String getAvatar() {

		if (userInfo != null) {
			return userInfo.getAvatar();
		}

		return avatar;
	}

	public String getUserId() {

		if (userInfo != null) {
			return userInfo.getUserId();
		}

		return userId;
	}

	public String getNickName() {

		if (userInfo != null) {
			return userInfo.getNickName();
		}

		return nickName;
	}

	public String getUserName() {

		if (userInfo != null) {
			return userInfo.getUserName();
		}

		return userName;
	}
	
	public String getEmpId() {
		
		if (userInfo != null) {
			return userInfo.getEmpId();
		}
	
		return null;
	}
	
	public String getEmpName() {
		
		if (userInfo != null) {
			return userInfo.getEmpName();
		}
	
		return empName;
	}

	/**
	 * 权重.
	 * 
	 * @return
	 */
	public int getWeights() {

		if (userInfo != null) {
			return userInfo.getWeights();
		}

		return 100;
	}
	
	/**
	 * 机构.
	 * 
	 * @return
	 */
	public String getOrgId() {
		return userInfo.getOrgId();
	}

	/**
	 * 是否超级管理员.
	 * 
	 * @return
	 */
	public boolean isSuperAdmin() {

		if (this.getWeights() == WeightsConst.WEIGHTS_SUPER_MANAGER) {
			return true;
		}

		return false;
	}
	
	public boolean isGov() {

		for(String s:this.getAccess()) {
			
			System.out.println("role:" + s);
		}
		if(this.getAccess().contains(IRole.DEFAULT_GOV_ROLE)){//政府管理员角色
			return true;
		}

		return false;
	}
	
	/**
	 * 个性签名.
	 * 
	 * @return
	 */
	public String getSign() {
		if (userInfo != null) {
			return userInfo.getSign();
		}
		return null;
	}
	
	/**
	 * 用户类型.
	 * 
	 * @return
	 */
	public String getUserType() {
		if (userInfo != null) {
			return userInfo.getUserType();
		}
		return null;
	}
	
	/**
	 * 用户状态.
	 * 
	 * @return
	 */
	public String getStatus() {
		if (userInfo != null) {
			return userInfo.getStatus();
		}
		return null;
	}
	
	public String getTenantId() {
		if (userInfo != null) {
			return userInfo.getTenantId();
		}
		return null;
	}
}
