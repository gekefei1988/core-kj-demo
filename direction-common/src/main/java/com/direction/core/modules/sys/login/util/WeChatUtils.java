package com.direction.core.modules.sys.login.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.direction.common.utils.http.HttpClientUtils;
import com.direction.core.modules.sys.user.entity.WeChatSession;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WeChatUtils {
	
	@Value("${wechat.auth.appid:}")
	private static String APPID;

	@Value("${wechat.auth.secret:}")
	private static String SECRET;
	
	public static WeChatSession getWeChatSession(String jscode) {
		
		System.out.println("jscode===" + jscode);
		
		System.out.println("APPID:" + APPID);
		System.out.println("SECRET:" + SECRET);
		
		String appid = APPID == null ? "wx62723a8a93a03b71" : APPID;
		String secret = SECRET == null ? "cd6af02ac1eca49cba21e6415a14c8aa" : APPID;
		String grant_type = "authorization_code";
		
		String js_code = jscode;
		WeChatSession weChatSession = null;
		
		System.out.println("appid>>>" + appid);
		System.out.println("secret>>>" + secret);
		System.out.println("js_code>>>" + js_code);
		
		try {
		
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s";
			url = String.format(url, appid, secret, js_code, grant_type);
			
			String result = HttpClientUtils.get(url);
			
			ObjectMapper mapper = new ObjectMapper();
		
			weChatSession = mapper.readValue(result, WeChatSession.class);
			
			System.out.println(weChatSession.getErrcode());
			System.out.println(weChatSession.getErrmsg());
			System.out.println(weChatSession.getOpenid());
			System.out.println(weChatSession.getUnionid());
			System.out.println(weChatSession.getSessionKey());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return weChatSession;
	}
	
	public static void main(String[] args) {
		
		try {
			
			String appid = APPID == null ? "wx62723a8a93a03b71" : APPID;
			String secret = SECRET == null ? "cd6af02ac1eca49cba21e6415a14c8aa" : APPID;
			String grant_type = "authorization_code";
			String js_code = "123";
			
			System.out.println("appid>>>" + appid);
			System.out.println("secret>>>" + secret);
			System.out.println("js_code>>>" + js_code);
			
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s";
			url = String.format(url, appid, secret, js_code, grant_type);
			
			String result = HttpClientUtils.get(url);
			ObjectMapper mapper = new ObjectMapper();
			
			WeChatSession weChatSession = mapper.readValue(result, WeChatSession.class);
			
			System.out.println(weChatSession.getErrcode());
			System.out.println(weChatSession.getErrmsg());
			System.out.println(weChatSession.getOpenid());
			System.out.println(weChatSession.getUnionid());
			System.out.println(weChatSession.getSessionKey());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}