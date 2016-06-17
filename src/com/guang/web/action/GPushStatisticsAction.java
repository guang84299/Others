package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAd;
import com.guang.web.mode.GPush;
import com.guang.web.mode.GUserPush;
import com.guang.web.service.GAdService;
import com.guang.web.service.GPushService;
import com.guang.web.service.GUserPushService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GPushStatisticsAction extends ActionSupport{

	private static final long serialVersionUID = -1559603936397543079L;
	private static final Logger logger = LoggerFactory.getLogger(GPushStatisticsAction.class);
	@Resource private GPushService pushService;
	@Resource private GAdService adService;
	@Resource private GUserPushService userPushService;

	public String list() {

		QueryResult<GPush>  qr = pushService.findAll(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (!StringTools.isEmpty(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GPush> pushList = pushService.findAll(start).getList();
				
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", pushList);
		ActionContext.getContext().put("pages", "pushStatistics");
		return "index";
	}
	//É¾³ýpushStatistics
	public void deletePushStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			pushService.delete(Long.parseLong(id));
			List<GUserPush> ups = userPushService.findByPushId(Long.parseLong(id), 0).getList();			
			for(GUserPush up : ups)
			{
				userPushService.delete(up.getId());
			}
		}
	}
	
	public void updateShowNum()
	{
		synchronized (pushService) {
			String data = ServletActionContext.getRequest().getParameter("data");
			JSONObject obj = JSONObject.fromObject(data);			
			String adId = obj.getString("adId");
			String pushId = obj.getString("pushId");
			long userId = obj.getLong("userId");
			if(!StringTools.isEmpty(adId) && !StringTools.isEmpty(pushId))
			{
				long push_id = Long.parseLong(pushId);
				long ad_id = Long.parseLong(adId);
				
				GPush push = pushService.find(push_id);
				push.setShowNum(push.getShowNum() + 1);
				pushService.update(push);
				
				GAd ad = adService.find(ad_id);
				ad.setShowNum(ad.getShowNum()+1);
				adService.update(ad);
				
				GUserPush up = userPushService.findByPushIdAndUserId(push_id, userId);
				up.setShows(true);
				userPushService.update(up);
			}
			
		}
	}
	
	public void updateClickNum()
	{
		synchronized (pushService) {
			String data = ServletActionContext.getRequest().getParameter("data");
			JSONObject obj = JSONObject.fromObject(data);			
			String adId = obj.getString("adId");
			String pushId = obj.getString("pushId");
			long userId = obj.getLong("userId");
			if(!StringTools.isEmpty(adId) && !StringTools.isEmpty(pushId))
			{
				long push_id = Long.parseLong(pushId);
				long ad_id = Long.parseLong(adId);
				
				GPush push = pushService.find(push_id);
				push.setClickNum(push.getClickNum() + 1);
				pushService.update(push);
				
				GAd ad = adService.find(ad_id);
				ad.setClickNum(ad.getClickNum()+1);
				adService.update(ad);
				
				GUserPush up = userPushService.findByPushIdAndUserId(push_id, userId);
				up.setClick(true);
				userPushService.update(up);
			}
			
		}
	}
	
	public void updateDownloadNum()
	{
		synchronized (pushService) {
			String data = ServletActionContext.getRequest().getParameter("data");
			JSONObject obj = JSONObject.fromObject(data);			
			String adId = obj.getString("adId");
			String pushId = obj.getString("pushId");
			long userId = obj.getLong("userId");
			if(!StringTools.isEmpty(adId) && !StringTools.isEmpty(pushId))
			{
				long push_id = Long.parseLong(pushId);
				long ad_id = Long.parseLong(adId);
				
				GPush push = pushService.find(push_id);
				push.setDownloadNum(push.getDownloadNum() + 1);
				pushService.update(push);
				
				GAd ad = adService.find(ad_id);
				ad.setDownloadNum(ad.getDownloadNum()+1);
				adService.update(ad);
				
				GUserPush up = userPushService.findByPushIdAndUserId(push_id, userId);
				up.setDownload(true);
				userPushService.update(up);
			}
			
		}
	}
	
	public void updateInstallNum()
	{
		synchronized (pushService) {
			String data = ServletActionContext.getRequest().getParameter("data");
			JSONObject obj = JSONObject.fromObject(data);			
			String adId = obj.getString("adId");
			String pushId = obj.getString("pushId");
			long userId = obj.getLong("userId");
			if(!StringTools.isEmpty(adId) && !StringTools.isEmpty(pushId))
			{
				long push_id = Long.parseLong(pushId);
				long ad_id = Long.parseLong(adId);
				
				GPush push = pushService.find(push_id);
				push.setInstallNum(push.getInstallNum() + 1);
				pushService.update(push);
				
				GAd ad = adService.find(ad_id);
				ad.setInstallNum(ad.getInstallNum()+1);
				adService.update(ad);
				
				GUserPush up = userPushService.findByPushIdAndUserId(push_id, userId);
				up.setInstall(true);
				userPushService.update(up);
			}
			
		}
	}
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getUserPushByClick()
	{
		String pushId = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(pushId))
		{
			List<GUserPush> list = userPushService.findByPushIdAndType(Long.parseLong(pushId), "click", 5).getList();
		
			String s = JSONArray.fromObject(list).toString();
			print(s);
		}
	}
	public void getUserPushByDownload()
	{
		String pushId = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(pushId))
		{
			List<GUserPush> list = userPushService.findByPushIdAndType(Long.parseLong(pushId), "download", 5).getList();
		
			String s = JSONArray.fromObject(list).toString();
			print(s);
		}
	}
	
	public void getUserPushByInstall()
	{
		String pushId = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(pushId))
		{
			List<GUserPush> list = userPushService.findByPushIdAndType(Long.parseLong(pushId), "install", 5).getList();
		
			String s = JSONArray.fromObject(list).toString();
			print(s);
		}
	}
}
