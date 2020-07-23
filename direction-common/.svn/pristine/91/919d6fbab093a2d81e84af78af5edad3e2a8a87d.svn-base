package com.direction.core.modules.sys.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.user.entity.WeChatUser;
import com.direction.spring.mvc.repository.BaseRepository;

/**
 * 微信账号Repository.
 * 
 * <pre>
 * 	微信账号Repository
 * </pre>
 * 
 * @author Evan
 * @since V1.0
 */
@Repository
public interface WeChatUserRepository extends BaseRepository<WeChatUser, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public List<WeChatUser> getListByOpenId(String openId);
}