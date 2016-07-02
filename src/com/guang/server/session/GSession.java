package com.guang.server.session;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.guang.server.protocol.GData;
import com.guang.server.protocol.GProtocol;

public class GSession {
	private IoSession session;
	private long heartBeatTime;//�ϴ��������ʱ��
	private String name;
	
	public GSession(IoSession session) {
		super();
		this.session = session;
		this.heartBeatTime = System.currentTimeMillis();
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public long getHeartBeatTime() {
		return heartBeatTime;
	}

	public void setHeartBeatTime(long heartBeatTime) {
		this.heartBeatTime = heartBeatTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sendMessage(int order,long userId,String title,String message,String pushId,String adId,String packageName,String picPath,String downloadPath)
	{
		JSONObject obj = new JSONObject();
		obj.put("order", order);
		obj.put("userId", userId);
		obj.put("title", title);
		obj.put("message", message);
		obj.put("pushId", pushId);
		obj.put("adId", adId);
		obj.put("packageName", packageName);
		obj.put("picPath", "images/sdk/qew_icon.png");
		obj.put("downloadPath", downloadPath);
		
		GData gdata = new GData(GProtocol.MODE_USER_SENDMESSAGE_RESULT, obj.toString());
		session.write(gdata.pack());
	}
	public void sendMessagePic(int order,long userId,String title,String message,String pushId,String adId,String packageName,String picPath,String downloadPath)
	{
		JSONObject obj = new JSONObject();
		obj.put("order", order);
		obj.put("userId", userId);
		obj.put("title", title);
		obj.put("message", message);
		obj.put("pushId", pushId);
		obj.put("adId", adId);
		obj.put("packageName", packageName);
		obj.put("picPath", picPath);
		obj.put("downloadPath", downloadPath);
		
		GData gdata = new GData(GProtocol.MODE_USER_SENDMESSAGE_PIC_RESULT, obj.toString());
		session.write(gdata.pack());
	}
	
	public void sendSpot(long userId,String pushId,String adId,String packageName,String picPath,String downloadPath)
	{
		JSONObject obj = new JSONObject();	
		obj.put("userId", userId);
		obj.put("pushId", pushId);
		obj.put("adId", adId);
		obj.put("packageName", packageName);
		obj.put("picPath", picPath);
		obj.put("downloadPath", downloadPath);
		
		GData gdata = new GData(GProtocol.MODE_USER_SENDSPOT_RESULT, obj.toString());
		session.write(gdata.pack());
	}
	
	public void changeAd(int platfrom)
	{
		JSONObject obj = new JSONObject();	
		obj.put("platfrom", platfrom);
		
		GData gdata = new GData(GProtocol.MODE_USER_SEND_CHANGEAD, obj.toString());
		session.write(gdata.pack());
	}
}
