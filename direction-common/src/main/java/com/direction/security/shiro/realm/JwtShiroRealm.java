package com.direction.security.shiro.realm;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.direction.security.shiro.token.JwtToken;
import com.direction.security.shiro.token.JwtUtil;


@Service
public class JwtShiroRealm extends AuthorizingRealm {

	//@Autowired
	//private UserService userService;

/*	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthorityService authorityService;*/

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
	        throws AuthenticationException {
/*		String token = (String)auth.getPrincipal();
		String account  = JwtUtil.getClaim(token, SecurityConsts.ACCOUNT);

		if (account == null) {
			throw new AuthenticationException("token invalid");
		}

		if (JwtUtil.verify(token)) {
			return new SimpleAuthenticationInfo(token, token, "shiroRealm");
		}
		throw new AuthenticationException("Token expired or incorrect.");*/
		
		
		
        JwtToken jwtToken = (JwtToken) auth;
        String token = jwtToken.getToken();
        
        try{
        if(!JwtUtil.verify(token)){
        	throw new AuthenticationException("token过期，请重新登录");
        }
        }catch (Exception e) {
            throw new TokenExpiredException("token过期，请重新登录");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        //SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getSalt(), "jwtRealm");

        return authenticationInfo;

       
	}
	
	

	
	
	

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		/*String account = JwtUtil.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
		User UserInfo = userService.findUserByAccount(account);

		//获取role
		List<Role> RoleList = roleService.findRoleByUserId(UserInfo.getId());
		//获取权限
		List<Object> AuthorityList = authorityService.findByUserId(UserInfo.getId());
		for(Role Role : RoleList){
			authorizationInfo.addRole(Role.getName());
			for(Object auth: AuthorityList){
				authorizationInfo.addStringPermission(auth.toString());
			}
		}*/
		return authorizationInfo;
	}

}
