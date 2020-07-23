package com.direction.spring.auditor;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.direction.core.inf.UserProfileUtils;

// ~ File Information
// ====================================================================================================================

/**
 * 用来处理基础信息注解等信息.
 * 
 * <pre>
 * 	用来处理基础信息注解等信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取用户ID.
	 * 
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		if (UserProfileUtils.getUserProfile() != null) {
			return Optional.ofNullable(UserProfileUtils.getUserProfile().getUserId());
		}
		return Optional.empty();
	}
}
