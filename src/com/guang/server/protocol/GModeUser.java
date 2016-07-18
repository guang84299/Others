package com.guang.server.protocol;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.handler.GSessionHandler;
import com.guang.server.session.GSession;
import com.guang.web.mode.GAd;
import com.guang.web.mode.GApp;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GNetworkOperator;
import com.guang.web.mode.GPhoneModel;
import com.guang.web.mode.GPush;
import com.guang.web.mode.GSysVal;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GUserPush;
import com.guang.web.mode.GUserStt;
import com.guang.web.service.GAdService;
import com.guang.web.service.GAppService;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GNetworkOperatorService;
import com.guang.web.service.GPhoneModelService;
import com.guang.web.service.GPushService;
import com.guang.web.service.GSysValService;
import com.guang.web.service.GUserPushService;
import com.guang.web.service.GUserService;
import com.guang.web.service.GUserSttService;
import com.guang.web.tools.BeanUtils;
import com.guang.web.tools.GTools;

public class GModeUser {
	private final static Logger logger = LoggerFactory.getLogger(GModeUser.class);
	private static GModeUser instance;
	private static GUserService userService;
	private static GAreaService areaService;
	private static GNetworkOperatorService gNetworkOperatorService;
	private static GPhoneModelService gPhoneModelService;
	private static GSysValService sysValService;
	private static GAdService adService;
	private static GPushService pushService;
	private static GUserPushService userPushService;
	private static GAppService appService;
	private static GUserSttService userSttService;
		
	public static GModeUser getInstance()
	{
		if(instance == null)
		{
			instance = new GModeUser();
			userService = BeanUtils.getBean("GUserServiceImpl");
			areaService = BeanUtils.getBean("GAreaServiceImpl");
			gNetworkOperatorService = BeanUtils.getBean("GNetworkOperatorServiceImpl");
			gPhoneModelService = BeanUtils.getBean("GPhoneModelServiceImpl");
			sysValService = BeanUtils.getBean("GSysValServiceImpl");
			adService = BeanUtils.getBean("GAdServiceImpl");
			pushService = BeanUtils.getBean("GPushServiceImpl");
			userPushService = BeanUtils.getBean("GUserPushServiceImpl");
			appService = BeanUtils.getBean("GAppServiceImpl");
			userSttService = BeanUtils.getBean("GUserSttServiceImpl");
		}					
		return instance;
	}
	//验证是否已经注册
	public void validate(IoSession session, String data)
	{
		JSONObject obj = JSONObject.fromObject(data);
		String name = obj.getString("name");
		String password = obj.getString("password");
		GUser user = userService.find(name);
		JSONObject result = new JSONObject();
		GData gdata = null;
		if(user != null && user.getName().equals(name) && user.getPassword().equals(password))
		{
			result.put("result", true);
			gdata = new GData(GProtocol.MODE_USER_LOGIN_VALIDATERESULT, result.toString());
			
			//如果已经在线 关闭
			if(GSessionHandler.getInstance().judeOnline(name))
			{
				GSessionHandler.getInstance().closeSession(name);
				user = userService.find(name);
			}
			
			user.setNetworkType(obj.getString("networkType"));
			user.setUpdatedDate(new Date());
			userService.update(user);
			loginSuccess(session,user.getName());
		}
		else
		{
			result.put("result", false);
			gdata = new GData(GProtocol.MODE_USER_LOGIN_VALIDATERESULT, result.toString());
		}
		session.write(gdata.pack());
	}
	//登录
	public void login(IoSession session, String data)
	{
		JSONObject obj = JSONObject.fromObject(data);
		String name = obj.getString("name");
		String password = obj.getString("password");
		String networkType = obj.getString("networkType");
		GUser user = userService.find(name);
		obj = new JSONObject();
		GData gdata = null;
		if(user != null && user.getName().equals(name) && user.getPassword().equals(password))
		{			
			obj.put("result", true);
			gdata = new GData(GProtocol.MODE_USER_LOGIN_RESULT, obj.toString());			
			
			//如果已经在线 关闭
			if(GSessionHandler.getInstance().judeOnline(name))
			{
				GSessionHandler.getInstance().closeSession(name);
				user = userService.find(name);
			}
			user.setNetworkType(networkType);
			user.setUpdatedDate(new Date());
			userService.update(user);
			loginSuccess(session,user.getName());
		}
		else
		{
			obj.put("result", false);
			gdata = new GData(GProtocol.MODE_USER_LOGIN_RESULT, obj.toString());
		}
		session.write(gdata.pack());
	}

	//注册
	public void register(IoSession session, String data)
	{
		GUser user = (GUser) JSONObject.toBean(JSONObject.fromObject(data),GUser.class);
		userService.add(user);
		
		areaService.add(new GArea(user.getProvince(), user.getCity()));
		gNetworkOperatorService.add(new GNetworkOperator(user.getNetworkOperatorName()));
		gPhoneModelService.add(new GPhoneModel(user.getModel()));
		
		GData gdata = new GData(GProtocol.MODE_USER_REGIST_RESULT,"1");
		session.write(gdata.pack());
		loginSuccess(session,user.getName());		
	}
	
	//登录成功
	public void loginSuccess(IoSession session,final String name)
	{
		final GSession gsession = GSessionHandler.getSessions().get(session.getId());
		gsession.setName(name);	
		
		logger.info(name+" 登录成功！");
		
		updateActive();
		autoPush(name);
	}
	
	//退出登录
	public void loginOut(String name)
	{
		GUser user = userService.find(name);
		if(user != null)
		{
			Date updated = user.getUpdatedDate();
       		if(updated == null)
       			updated =  new Date();
        	long t = new Date().getTime() - updated.getTime();
        	String lastOnlineTime = t/1000/60+"";
        	
        	String onlineTime = user.getOnlineTime();
        	if(onlineTime == null)
        		onlineTime = "0";
        	long ot = Long.parseLong(onlineTime) + t/1000/60;
        	user.setLastOnlineTime(lastOnlineTime);
        	user.setOnlineTime(ot+"");
        	
        	userService.update(user);        	
		}
	}
	
	//心跳检测
	public void heartBeat(IoSession session, String data)
	{
//		GSession gsession = GSessionHandler.getSessions().get(session.getId());
//		gsession.setHeartBeatTime(System.currentTimeMillis());
	}
	
	//更新日活
	public synchronized void updateActive()
	{
		GUserStt userStt = userSttService.find();
		Date date = new Date();		
		if(date.getDate() != userStt.getCurrDate().getDate())
		{
			userStt.setCurrDate(date);
			userStt.setYesterdayAdd(userStt.getTodayAdd());
			userStt.setYesterdayActive(userStt.getTodayActive());
			userStt.setYesterdayStartTimes(userStt.getTodayStartTimes());
			userStt.setTodayActive(1l);
			userStt.setTodayStartTimes(1l);
		}
		else
		{
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();			
			colvals.put("updatedDate >=", "'"+date.toLocaleString()+"'");
			date.setDate(date.getDate()+1);
			colvals.put("updatedDate <", "'"+date.toLocaleString()+"'");
			long num = userService.find(colvals).getNum();
			userStt.setTodayActive(num);
			
			userStt.setTodayStartTimes(userStt.getTodayStartTimes() + 1l);
		}
		date = new Date();	
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();			
		colvals.put("createdDate >=", "'"+date.toLocaleString()+"'");
		date.setDate(date.getDate()+1);
		colvals.put("createdDate <", "'"+date.toLocaleString()+"'");
		long num = userService.find(colvals).getNum();
		userStt.setTodayAdd(num);
		
		userSttService.update(userStt);
	}
	
	//自动推送
	public void autoPush(final String name)
	{
		final GSysVal val = sysValService.find();
		if(val.isAutoState())
		{
			new Thread(){
				public void run() {
					try {
						Thread.sleep((long)(val.getWaitTime()*60*1000));
						
						GSession session = GSessionHandler.getInstance().getSessionByName(name);
						if(session == null)
							return;
						GUser user = userService.find(name);
						//获得该用户包名
						List<GApp> list = appService.findAppsByUserId(user.getId()).getList();
						//获得广告包名
						long adId = val.getAdId();
						GAd ad = adService.find(adId);
						//如果该用户已经安装该应用，则不再推荐
						for(GApp app : list)
						{
							if(app.getPackageName().equals(ad.getPackageName()))
								return;
						}
						
						if(val.getAutoPushType() == 1)
						{					
							GPush push = new GPush(adId, 0, 2, 1, 0, 0, 0, 0);
							pushService.add(push);
							session.sendMessage(0,user.getId(),val.getTitle(), val.getMessage(),
									push.getId()+"", adId+"", ad.getPackageName(),ad.getPicPath(), ad.getDownloadPath(),GTools.getRandomUUID());
							userPushService.add(new GUserPush(user.getId(), push.getId()));
						}
						else
						{
							GPush push = new GPush(adId, 1, 2, 1, 0, 0, 0, 0);
							pushService.add(push);
							session.sendSpot(0,user.getId(),push.getId()+"", adId+"", ad.getPackageName(),
									ad.getPicPath(), ad.getDownloadPath(),GTools.getRandomUUID());
							userPushService.add(new GUserPush(user.getId(), push.getId()));
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
				};
			}.start();
		}
	}
}
