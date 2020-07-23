package com.direction.core.modules.sys.user.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 微信账号Entity.
 * 
 * <pre>
 * 	微信账号Entity
 * </pre>
 * 
 * @author Evan
 * @since V1.0
 *
 */
@MappedSuperclass
public class BaseWeChatUser extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final long serialVersionUID = 1L;
	
	// ~ Fields
	// ==================================================================================================================

	/**
	 *id.
	 */
	@Id
	@GeneratedValue(generator = "uuid")
  	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	private java.lang.String id;
	
	/**
	 *微信id.
	 */
	@Column(name = "open_id", nullable = true, length = 32)
	private java.lang.String openId;
	
	/**
	 *微信unionId.
	 */
	@Column(name = "union_id", nullable = true, length = 32)
	private java.lang.String unionId;
	
	/**
	 *用户id.
	 */
	@Column(name = "user_id", nullable = true, length = 32)
	private java.lang.String userId;
	
	/**
	 *昵称.
	 */
	@Column(name = "nickname", nullable = true, length = 50)
	private java.lang.String nickname;
	
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 取得id.
	 * @return: java.lang.String  id
	 */
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 设置id.
	 * @param: java.lang.String  id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	/**
	 * 取得微信id.
	 * @return: java.lang.String  微信id
	 */
	public java.lang.String getOpenId() {
		return this.openId;
	}

	/**
	 * 设置微信id.
	 * @param: java.lang.String  微信id
	 */
	public void setOpenId(java.lang.String openId) {
		this.openId = openId;
	}
	
	/**
	 * 取得用户id.
	 * @return: java.lang.String  用户id
	 */
	public java.lang.String getUserId() {
		return this.userId;
	}

	/**
	 * 设置用户id.
	 * @param: java.lang.String  用户id
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	/**
	 * 取得昵称.
	 * @return: java.lang.String  昵称
	 */
	public java.lang.String getNickname() {
		return this.nickname;
	}

	/**
	 * 设置昵称.
	 * @param: java.lang.String  昵称
	 */
	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getUnionId() {
		return unionId;
	}

	public void setUnionId(java.lang.String unionId) {
		this.unionId = unionId;
	}
}