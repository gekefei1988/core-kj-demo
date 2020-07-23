package com.direction.core.modules.sys.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.entity.WeChatSession;
import com.direction.core.modules.sys.user.entity.WeChatUser;
import com.direction.core.modules.sys.user.repository.WeChatUserRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 微信账号Service.
 * 
 * <pre>
 * 	微信账号
 * </pre>
 * 
 * @author Evan
 * @since V1.0
 */
@Service
@Transactional(readOnly=true)
public class WeChatUserService {

	// ~ Static Fields
	// ==================================================================================================================
		
	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private WeChatUserRepository weChatUserRepository;
	
	@Autowired
	private UserService userService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据id查询微信账号信息.
	 * 
	 * @param id
	 * @return WxUser
	 */
	public WeChatUser get(String id) {
		
		WeChatUser weChatUser = null;
		
		if (StringUtils.isBlank(id)) {
			return weChatUser;
		}
		
		Optional<WeChatUser> optional = weChatUserRepository.findById(id);
		
		if (optional != null && optional.isPresent()) {
			weChatUser = optional.get();
		}
		
		return weChatUser;
	}
	
	/**
	 * 根据openid获取用户.
	 * 
	 * @param openId
	 */
	public WeChatUser getByOpenId(String openId) {
		
		WeChatUser user = null;
		List<WeChatUser> users = weChatUserRepository.getListByOpenId(openId);
		
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		
		return user;
	}
	
	/**
	 * 保存微信用户.
	 * 
	 * @param session
	 * @return
	 */
	public WeChatUser save(WeChatSession session) {
		
		String openid = session.getOpenid();
		String unionid = session.getUnionid();
		String userId = "";
		
		User user = userService.saveUserByOpenid(openid);
		userId = user == null ? "" : user.getUserId();
		
		return this.save(userId, openid, unionid, "");
	}
	
	/**
	 * 保存用户信息.
	 * 
	 * @param userId
	 * @param openId
	 * @param unionId
	 * @param nickname
	 * @return
	 */
	@Transactional(readOnly = false)
	public WeChatUser save(String userId, String openId, String unionId, String nickname) {
		
		WeChatUser user = new WeChatUser();
		
		user.setOpenId(openId);
		user.setUnionId(unionId);
		user.setUserId(userId);
		user.setNickname(nickname);
		
		this.save(user);
		
		return user;
	}
	
	/**
	 * 保存.
	 * 
	 * @param WeChatUser
	 */
	@Transactional(readOnly = false)
	public void save(WeChatUser weChatUser) {
		weChatUserRepository.save(weChatUser);
	}
	
	/**
	 * 
	 * 删除.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		weChatUserRepository.deleteById(id);
	}
	
	/**
	 * 列表查询(带分页).
	 * 
	 * @param weChatUser
	 * @param page
	 * @return 
	 */
	public VuePage<WeChatUser> findPage(WeChatUser weChatUser, VuePage<WeChatUser> page) {
		
		Specification<WeChatUser> params = new Specification<WeChatUser>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<WeChatUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				// 查询条件
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (weChatUser != null) {
					
					// TODO: 查询条件
					
				}
				
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		
		Page<WeChatUser> resultPage = weChatUserRepository.findAll(params, page.getPageable());
		page.setPage(resultPage);
		
		return page;
	}
}