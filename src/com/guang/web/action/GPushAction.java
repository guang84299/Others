package com.guang.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.guang.web.service.GAdService;
import com.guang.web.service.GAppService;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GNetworkOperatorService;
import com.guang.web.service.GPhoneModelService;
import com.guang.web.service.GPushService;
import com.guang.web.service.GSysValService;
import com.guang.web.service.GUserPushService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GPushAction extends ActionSupport {
	private static final long serialVersionUID = 1262752139846582908L;
	private static final Logger logger = LoggerFactory.getLogger(GPushAction.class);
	@Resource private GAreaService areaService;
	@Resource private GPhoneModelService phoneModelService;
	@Resource private GNetworkOperatorService networkOperatorService;
	@Resource private GSysValService sysValService;
	@Resource private GUserService userService;
	@Resource private GAppService appService;
	@Resource private GPushService pushService;
	@Resource private GUserPushService userPushService;
	@Resource private GAdService adService;

	public String list() {
		
		ActionContext.getContext().put("pages", "push");
		return "index";
	}
	
	//推送消息
	public String pushMessage()
	{
		push(0);
		String pushPic = ServletActionContext.getRequest().getParameter("pushPic");
		if("1".equals(pushPic))
			push(2);//推送带大图消息
		ActionContext.getContext().put("pages", "push");
		return "index";
	}
	
	//推送插屏
	public String pushSpot()
	{
		push(1);
					
		ActionContext.getContext().put("pages", "push");
		return "index";
	}
	
	//推送 pushType : 0=消息 1=插屏 2=图片消息
	public synchronized void push(int pushType)
	{
		String broadcast = ServletActionContext.getRequest().getParameter("broadcast");
		String username = ServletActionContext.getRequest().getParameter("username");
		String appname = ServletActionContext.getRequest().getParameter("appname");
		String area_province = ServletActionContext.getRequest().getParameter("area_province");
		String area_city = ServletActionContext.getRequest().getParameter("area_city");
		String phone_model = ServletActionContext.getRequest().getParameter("phone_model");
		String network_operator = ServletActionContext.getRequest().getParameter("network_operator");
		String session_from = ServletActionContext.getRequest().getParameter("session_from");
		String session_to = ServletActionContext.getRequest().getParameter("session_to");
		String createDate_from = ServletActionContext.getRequest().getParameter("createDate_from");
		String createDate_to = ServletActionContext.getRequest().getParameter("createDate_to");
		String title = ServletActionContext.getRequest().getParameter("title");
		String message = ServletActionContext.getRequest().getParameter("message");
		String adId = ServletActionContext.getRequest().getParameter("adId");
		long ad_id = Long.parseLong(adId);		
		if("all".equals(broadcast))
		{
			HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
			Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
			int num = 0;
			GPush push = new GPush(ad_id, pushType, 0, 0, 0, 0, 0, 0);
			pushService.add(push);
			while (iter.hasNext()) {
				Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
				GSession val = entry.getValue();
				GUser user = userService.find(val.getName());
				if(user != null)
				{
					if(isArea(user, area_province, area_city) 
							&& isPhoneModel(user, phone_model)
							&& isOperator(user, network_operator)
							&& isSession(val.getSession().getId(), session_from, session_to)
							&& isDate(user, createDate_from, createDate_to))
					{
						GAd ad = adService.find(ad_id);
						if(pushType == 0)
						{						
							val.sendMessage(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}							
						else if(pushType == 1)
						{
							val.sendSpot(user.getId(),push.getId()+"",adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}
						else
						{
							val.sendMessagePic(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}
						num++;
						
						userPushService.add(new GUserPush(user.getId(), push.getId()));
					}
				}
				
			}	
			
			synchronized (pushService) {
				push = pushService.find(push.getId());
				push.setSendNum(num);
				pushService.update(push);
			}					
		}
		else if("app".equals(broadcast))
		{
			HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
			Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
			int num = 0;
			GPush push = new GPush(ad_id, pushType, 1, 0, 0, 0, 0, 0);
			pushService.add(push);
			while (iter.hasNext()) {
				Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
				GSession val = entry.getValue();
				GUser user = userService.find(val.getName());
				if(user != null)
				{
					if(		isAppName(user, appname)
							&& isArea(user, area_province, area_city) 
							&& isPhoneModel(user, phone_model)
							&& isOperator(user, network_operator)
							&& isSession(val.getSession().getId(), session_from, session_to)
							&& isDate(user, createDate_from, createDate_to))
					{
						GAd ad = adService.find(ad_id);
						if(pushType == 0)
						{						
							val.sendMessage(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}
						else if(pushType == 1)
						{
							val.sendSpot(user.getId(),push.getId()+"",adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}
						else
						{
							val.sendMessagePic(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
						}
						num++;
						
						userPushService.add(new GUserPush(user.getId(), push.getId()));
					}
				}			
			}
			synchronized (pushService) {
				push = pushService.find(push.getId());
				push.setSendNum(num);
				pushService.update(push);
			}		
		}
		else
		{
			GSession session = GSessionHandler.getInstance().getSessionByName(username);
			GUser user = userService.find(username);
			if(session != null)
			{
				GPush push = new GPush(ad_id, pushType, 2, 1, 0, 0, 0, 0);
				pushService.add(push);
				GAd ad = adService.find(ad_id);
				if(pushType == 0)
				{
					session.sendMessage(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
				}
				else if(pushType == 1)
				{
					session.sendSpot(user.getId(),push.getId()+"",adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
				}
				else
				{
					session.sendMessagePic(user.getId(),title, message,push.getId()+"", adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
				}
				userPushService.add(new GUserPush(user.getId(), push.getId()));
			}
		}
	}
	//推送插屏
	public synchronized void pushSpotByClient()
	{
		GSysVal sysval = sysValService.find();
		if(!sysval.getRequestPushState())
			return;
		float r = (float) Math.random();
		if(r > sysval.getRequestPushRand())
		{
			return;
		}
		
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null)
		{
			JSONObject obj = JSONObject.fromObject(data);
			String username = obj.getString("username");
			//广告算法	
			List<GAd> list = adService.findAdsByShowLevel().getList();
			List<GAd> listad = new ArrayList<GAd>();
			for(GAd ad : list)
			{
				if(ad.getShowLevel() > 0)
				{
					listad.add(ad);
				}
			}
			if(listad.size() == 0)
				return;
			GAd ad = listad.get(GTools.getRand(0, listad.size()));
			long ad_id = ad.getId();
			String adId = ad_id+"";
			
			GSession session = GSessionHandler.getInstance().getSessionByName(username);
			GUser user = userService.find(username);
			if(session != null)
			{
				GPush push = new GPush(ad_id, 1, 2, 1, 0, 0, 0, 0);
				pushService.add(push);				
				session.sendSpot(user.getId(),push.getId()+"",adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());
				
				userPushService.add(new GUserPush(user.getId(), push.getId()));
			}
		}
					
	}
	//推送点击下载安装过的用户
	public void sendClickDownloadInstallAd()
	{
		String pushId = ServletActionContext.getRequest().getParameter("pushId");
		String adId = ServletActionContext.getRequest().getParameter("adId");
		String type = ServletActionContext.getRequest().getParameter("type");
		
		if(!StringTools.isEmpty(pushId) && !StringTools.isEmpty(adId) && !StringTools.isEmpty(type))
		{
			long push_id = Long.parseLong(pushId);
			long ad_id = Long.parseLong(adId);
			String ty = "click";
			if("2".equals(type))
				ty = "download";
			else if("2".equals(type))
				ty = "install";
			List<GUserPush> list = userPushService.findByPushIdAndType(push_id, ty, 100000).getList();
			int num = 0;
			GPush push = new GPush(ad_id, 1, 3, 0, 0, 0, 0, 0);
			pushService.add(push);
			for(GUserPush up : list)
			{
				GUser user = userService.find(up.getUserId());
				GSession session = GSessionHandler.getInstance().getSessionByName(user.getName());
				if(session != null)
				{					
					GAd ad = adService.find(ad_id);
					session.sendSpot(user.getId(),push.getId()+"",adId,ad.getPackageName(),ad.getPicPath(),ad.getDownloadPath());					
					userPushService.add(new GUserPush(user.getId(), push.getId()));
					num++;
				}
			}
			synchronized (pushService) {
				push = pushService.find(push.getId());
				push.setSendNum(num);
				pushService.update(push);
			}	
		}
	}

	// 返回数据
	public void print(Object data) {
		try {
			ServletActionContext.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获得地域
	public void getAreas() {
		List<GArea> list = areaService.findAll().getList();
		String data = JSONArray.fromObject(list).toString();
		print(data);
	}

	// 获得手机型号
	public void getPhoneModels() {
		List<GPhoneModel> list = phoneModelService.findAll().getList();
		String data = JSONArray.fromObject(list).toString();
		print(data);
	}

	// 获得运营商
	public void getNetworkOperators() {
		List<GNetworkOperator> list = networkOperatorService.findAll()
				.getList();
		String data = JSONArray.fromObject(list).toString();
		print(data);
	}

	// 获取自动推送配置信息
	public void getAutoPushSetting() {
		GSysVal sysval = sysValService.find();
		String data = JSONObject.fromObject(sysval).toString();
		print(data);
	}
	
	//设置自动推送配置
	public String autoPushSetting()
	{
		String autoPush_type = ServletActionContext.getRequest().getParameter("autoPush_type");
		String title = ServletActionContext.getRequest().getParameter("title");
		String message = ServletActionContext.getRequest().getParameter("message");
		String adId = ServletActionContext.getRequest().getParameter("adId");
		String waitTime = ServletActionContext.getRequest().getParameter("waitTime");
		String auto_state = ServletActionContext.getRequest().getParameter("auto_state");

		if(!StringTools.isEmpty(waitTime) && !StringTools.isEmpty(adId))
		{
			GSysVal sysval = sysValService.find();
			
			sysval.setAutoPushType(Integer.parseInt(autoPush_type));
			sysval.setTitle(title);
			sysval.setMessage(message);
			sysval.setAdId(Integer.parseInt(adId));
			sysval.setWaitTime(Float.parseFloat(waitTime));
			if("1".equals(auto_state))
				sysval.setAutoState(true);
			else
				sysval.setAutoState(false);
			
			sysValService.update(sysval);
		}
		
		ActionContext.getContext().put("pages", "push");
		return "index";
	}
	
	//主动请求push配置
	public String requestPushSetting()
	{
		String request_state = ServletActionContext.getRequest().getParameter("request_state");
		String requestPushRand = ServletActionContext.getRequest().getParameter("requestPushRand");
		
		GSysVal sysval = sysValService.find();
		if("1".equals(request_state))
			sysval.setRequestPushState(true);
		else
			sysval.setRequestPushState(false);
		sysval.setRequestPushRand(Float.parseFloat(requestPushRand));
		sysValService.update(sysval);
		
		ActionContext.getContext().put("pages", "push");
		return "index";
	}
	
	public boolean isAppName(GUser user,String appname)
	{
		List<GApp> list = appService.findAppsByUserId(user.getId()).getList();
		if(list.size() > 0)
		{
			for(GApp app : list)
			{
				if(app.getPackageName().equals(appname))
					return true;
			}
		}
		return false;
	}
	
	public boolean isArea(GUser user,String area_province,String area_city)
	{		
		if("all".equals(area_province))
		{
			return true;
		}
		else
		{
			if("all".equals(area_city))
			{
				if(user.getProvince().equals(area_province))
					return true;
			}
			else
			{
				if(user.getProvince().equals(area_province) && user.getCity().equals(area_city))
					return true;
			}
		}
		return false;
	}

	public boolean isPhoneModel(GUser user,String phone_model)
	{
		if("all".equals(phone_model))
			return true;
		else
		{
			if(phone_model.equals(user.getModel()))
				return true;
		}
		return false;
	}

	public boolean isOperator(GUser user,String network_operator)
	{
		if("all".equals(network_operator))
		{
			return true;
		}
		else
		{
			if(network_operator.equals(user.getNetworkOperatorName()))
				return true;
		}
		return false;
	}
	
	public boolean isSession(long id,String session_from,String session_to)
	{
		if(StringTools.isEmpty(session_from) || StringTools.isEmpty(session_to))
			return true;
		int from = Integer.parseInt(session_from);
		int to = Integer.parseInt(session_to);	
		if(to == 0)
			return true;
		if(id >= from && id <= to)
			return true;
		return false;
	}

	public boolean isDate(GUser user,String createDate_from,String createDate_to)
	{
		if(StringTools.isEmpty(createDate_from) || StringTools.isEmpty(createDate_to))
			return true;
		Date createDate = user.getCreatedDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			Date from = sdf.parse(createDate_from);
			Date to = sdf.parse(createDate_to);
			
			if(createDate.getTime() >= from.getTime() && createDate.getTime() <= to.getTime())
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return false;
	}


}
