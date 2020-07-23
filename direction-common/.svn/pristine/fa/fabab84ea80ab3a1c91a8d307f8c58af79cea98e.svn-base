package com.direction.core.modules.sys.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_user_online")
public class UserOnline extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_user_online_pk")
	@GenericGenerator(name = "sys_user_online_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "dept_id")
	private String deptId;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "ipaddr")
	private String ipaddr;

	@Column(name = "browser")
	private String browser;

	@Column(name = "status")
	private char status;

	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@Column(name = "last_access_time")
	private Date lastAccessTime;

	@Column(name = "login_count")
	private int loginCount;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getLoginName() {

		return loginName;
	}

	public void setLoginName(String loginName) {

		this.loginName = loginName;
	}

	public String getDeptName() {

		return deptName;
	}

	public void setDeptName(String deptName) {

		this.deptName = deptName;
	}

	public String getIpaddr() {

		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {

		this.ipaddr = ipaddr;
	}

	public String getBrowser() {

		return browser;
	}

	public void setBrowser(String browser) {

		this.browser = browser;
	}

	public char getStatus() {

		return status;
	}

	public void setStatus(char status) {

		this.status = status;
	}

	public Date getCreateTimestamp() {

		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {

		this.createTimestamp = createTimestamp;
	}

	public Date getLastAccessTime() {

		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {

		this.lastAccessTime = lastAccessTime;
	}

	public int getLoginCount() {

		return loginCount;
	}

	public void setLoginCount(int loginCount) {

		this.loginCount = loginCount;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getDeptId() {

		return deptId;
	}

	public void setDeptId(String deptId) {

		this.deptId = deptId;
	}

}
