package com.direction.core.modules.sys.user.entity;

/**
 * 微信用户session.
 * 
 * @author Evan
 *
 */
public class WeChatSession {

	private String openid = "";
	private String sessionKey = "";
	private String unionid = "";
	private String errcode = "";
	private String errmsg = "";
	private String token = "";
	private String userid = "";
	
	public WeChatSession() {
		
	}
	
	public void setSession_key(String session_key) {
		this.sessionKey = session_key;
	}
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}