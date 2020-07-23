package com.direction.security.shiro.token;

import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.direction.security.shiro.constants.ShiroConstants;

/**
 * JWT工具类.
 * 
 * <pre>
 * 	JWT工具类
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
public class JwtUtil {

	// 默认设置30分钟
	public static final long EXPIRE_TIME = 30 * 60 * 1000;

	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	/**
	 * 获得token中的信息无需secret解密也能获得
	 * 
	 * @return token中包含的签发时间
	 */
	public static Date getIssuedAt(String token) {

		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getIssuedAt();
		} catch (JWTDecodeException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 * 
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {

		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim(ShiroConstants.CURRENT_USERNAME).asString();
		} catch (JWTDecodeException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 生成签名,expireTime后过期
	 * 
	 * @param username 用户名
	 * @param time 过期时间(分钟)
	 * @return 加密的token
	 */
	public static String sign(String username, String salt) {

		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(salt);
			
			// 附带username信息
			return JWT.create()
			    .withClaim(ShiroConstants.CURRENT_USERNAME, username)
			    .withExpiresAt(date)
			    .withIssuedAt(new Date())
			    .sign(algorithm);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 生成签名, expireTime后过期.
	 * 
	 * @param username 用户名
	 * @param time 过期时间(分钟)
	 * 
	 * @return 加密的token
	 */
	public static String sign(String username, String salt, long time) {

		try {
			Date date = new Date(System.currentTimeMillis() + time);
			Algorithm algorithm = Algorithm.HMAC256(username);
			
			// 附带username信息
			return JWT.create().withClaim(ShiroConstants.CURRENT_USERNAME, username).withExpiresAt(date).withIssuedAt(new Date()).sign(algorithm);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * token是否过期.
	 * 
	 * @return true：过期
	 */
	public static boolean isTokenExpired(String token) {

		Date now = Calendar.getInstance().getTime();
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getExpiresAt().before(now);
	}

	/**
	 * 生成随机盐,长度32位.
	 * 
	 * @return
	 */
	public static String generateSalt() {

		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		String hex = secureRandom.nextBytes(16).toHex();
		return hex;
	}

	/**
	 * 校验token是否正确
	 * 
	 * @param token
	 * @return
	 */
	public static boolean verify(String token) {

		try {
			String secret = getClaim(token, ShiroConstants.CURRENT_USERNAME);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			
			logger.info("用户[" + secret + "]Token 验证成功");
			return true;
		} catch (Exception e) {
			logger.error("用户Token 验证失败, 错误信息: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 校验token是否正确
	 *
	 * @param token 密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret) {

		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim(ShiroConstants.CURRENT_USERNAME, username).build();
			
			// 效验TOKEN
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得Token中的信息无需secret解密也能获得.
	 * 
	 * @param token
	 * @param claim
	 * @return
	 */
	public static String getClaim(String token, String claim) {

		try {
			DecodedJWT jwt = JWT.decode(token);
			String ss = jwt.getClaim(claim).asString();
			return ss;
		} catch (JWTDecodeException e) {
			return null;
		}
	}
}
