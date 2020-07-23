package com.direction.core.modules.sys.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.modules.sys.user.entity.base.BaseWeChatUser;

// ~ File Information
// ====================================================================================================================

/**
 * 微信账号.
 * 
 * <pre>
 * 	微信账号
 * </pre>
 * 
 * @author Evan
 * @since V1.0
 */
@Entity
@Table(name = "sys_wechat_user")
public class WeChatUser extends BaseWeChatUser {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final long serialVersionUID = 1L;
	
	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
		
}