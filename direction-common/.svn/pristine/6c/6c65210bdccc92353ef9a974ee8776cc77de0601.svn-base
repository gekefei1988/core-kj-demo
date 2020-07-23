package com.direction.core.modules.sys.user.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseSuperAdmin extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -2560856480859145250L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_super_admin_pk")
	@GenericGenerator(name = "sys_super_admin_pk", strategy = "uuid")
	@Column(name = "user_id", unique = true, nullable = false)
	private String userId;

	// 账号
	@NotBlank(message = "账号不能为空")
	@Column(name = "user_name")
	private String userName;

	// 昵称
	@Column(name = "nick_name")
	private String nickName;

	// 登陆密码
	@Column(name = "password")
	@NotBlank(message = "密码不能为空")
	private String password;

	@Column(name = "reg_password")
	@NotBlank(message = "注册密码不能为空")
	private String regPassword;

	// 头像
	@Column(name = "avatar")
	private String avatar;

	// 允许PC登陆
	@Column(name = "is_allow_pc", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private boolean allowPC;

	// 允许移动端登陆
	@Column(name = "is_allow_mobile", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private boolean allowMobile;

	// 允许微信端登陆
	@Column(name = "is_allow_wechat", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private boolean allowWechat;

	// 状态
	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// 最后一次登陆IP
	@Column(name = "last_login_ip")
	private String lastLoginIP;

	// 最后一次登陆日期
	@Column(name = "last_login_date")
	private Date lastLoginDate;

	// 最后一次修改密码
	@Column(name = "last_pwd_update")
	private Date lastPwdUpdate;

	// 登陆错误次数
	@Column(name = "login_error_count")
	private int loginErrorCount;

	@Column(name = "login_error_date")
	private Date loginErrorDate;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getNickName() {

		return nickName;
	}

	public void setNickName(String nickName) {

		this.nickName = nickName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getRegPassword() {

		return regPassword;
	}

	public void setRegPassword(String regPassword) {

		this.regPassword = regPassword;
	}

	public String getAvatar() {

		return avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public boolean getAllowPC() {

		return allowPC;
	}

	public boolean isAllowPC() {

		return allowPC;
	}

	public void setAllowPC(boolean allowPC) {

		this.allowPC = allowPC;
	}

	public boolean getAllowMobile() {

		return allowMobile;
	}

	public boolean isAllowMobile() {

		return allowMobile;
	}

	public void setAllowMobile(boolean allowMobile) {

		this.allowMobile = allowMobile;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getLastLoginIP() {

		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {

		this.lastLoginIP = lastLoginIP;
	}

	public Date getLastLoginDate() {

		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {

		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastPwdUpdate() {

		return lastPwdUpdate;
	}

	public void setLastPwdUpdate(Date lastPwdUpdate) {

		this.lastPwdUpdate = lastPwdUpdate;
	}

	public int getLoginErrorCount() {

		return loginErrorCount;
	}

	public void setLoginErrorCount(int loginErrorCount) {

		this.loginErrorCount = loginErrorCount;
	}

	public Date getLoginErrorDate() {

		return loginErrorDate;
	}

	public void setLoginErrorDate(Date loginErrorDate) {

		this.loginErrorDate = loginErrorDate;
	}

	public boolean isAllowWechat() {

		return allowWechat;
	}

	public void setAllowWechat(boolean allowWechat) {

		this.allowWechat = allowWechat;
	}
}
