package com.guang.server.protocol;

import java.lang.reflect.Method;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.handler.GSessionHandler;

import net.sf.json.JSONObject;

/**
 * 自定义协议 基于json
 * @author guang
 *
 */

public class GProtocol {
	private static final Logger logger = LoggerFactory.getLogger(GProtocol.class);
	public static final int MODE_TEST = -1;
	public static final String PROTOCOL_CLASS_HEAD = "com.guang.server.protocol.";
	public static final String PROTOCOL_CLASS_SESSION = "org.apache.mina.core.session.IoSession";
	public static final String PROTOCOL_CLASS_STRING = "java.lang.String";
	public static final String PROTOCOL_CLASS_INSTANCE = "getInstance";
	
	public static final String MODE_USER_LOGIN_VALIDATERESULT = "GModeUser_validateResult";
	public static final String MODE_USER_REGIST_RESULT = "GModeUser_registResult";
	public static final String MODE_USER_LOGIN_RESULT = "GModeUser_loginResult";
	public static final String MODE_USER_SENDMESSAGE_RESULT = "GModeUser_sendMessageResult";
	public static final String MODE_USER_SENDSPOT_RESULT = "GModeUser_sendSpotResult";
	public static final String MODE_USER_SEND_CHANGEAD = "GModeUser_sendChangeAdResult";
	
	
	public static void parse(IoSession session, Object message)
	{		
		try {
			JSONObject data = JSONObject.fromObject(message.toString());
			String[] mode = data.getString("mode").split("_");
			String className = mode[0];
			String methodName = mode[1];		
			Class<?> c = Class.forName(PROTOCOL_CLASS_HEAD + className);
			Method m = c.getMethod(PROTOCOL_CLASS_INSTANCE, new Class[]{});	
			Object obj = m.invoke(c);
			Class<?> args[] = new Class[]{Class.forName(PROTOCOL_CLASS_SESSION),Class.forName(PROTOCOL_CLASS_STRING)};
			m = c.getMethod(methodName, args);	
			m.invoke(obj,session,data.getString("body"));
		} catch (Exception e) {
			logger.error("数据解析失败！"+e.getMessage() + "  data="+message.toString());
		}
	}
}