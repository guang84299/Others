package com.guang.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.handler.GSessionHandler;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GUserPush;
import com.guang.web.service.GUserPushService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GPushCompareAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(GPushCompareAction.class);
	
	private static final long serialVersionUID = 1L;
	
	@Resource private GUserPushService userPushService;
	@Resource private GUserService userService;

	public String list()
	{
		String pushId = ServletActionContext.getRequest().getParameter("pushId");
		if(StringTools.isEmpty(pushId))
			pushId = "0";
		long pid = Long.parseLong(pushId);
		
		QueryResult<GUserPush>  qr = userPushService.findByPushId(pid, 0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (!StringTools.isEmpty(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GUserPush> list = userPushService.findByPushId(pid,start).getList();
		List<GUser> userList = new ArrayList<GUser>();
		
		for(GUserPush up : list)
		{
			GUser user = userService.find(up.getUserId());
			if(StringTools.isEmpty(user.getPhoneNumber()))
				user.setPhoneNumber("δ֪");
			user.setOnline(GSessionHandler.getInstance().judeOnline(user.getName(),user.getPassword()));
			userList.add(user);
		}
				
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", userList);
		ActionContext.getContext().put("pushId", pushId);
		ActionContext.getContext().put("pages", "pushCompare");
		
		return "index";
	}
	
	public void deleteUserPush()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String pushId = ServletActionContext.getRequest().getParameter("pushId");
		
		if(!StringTools.isEmpty(pushId) && !StringTools.isEmpty(pushId))
		{
			long userId = Long.parseLong(id);
			long push_id = Long.parseLong(pushId);
			
			GUserPush up = userPushService.findByPushIdAndUserId(push_id, userId);
			userPushService.delete(up.getId());
		}
		
	}
}
