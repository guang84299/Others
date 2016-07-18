package com.guang.web.action;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;



import com.guang.server.handler.GSessionHandler;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GApp;
import com.guang.web.mode.GSysVal;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GUserStt;
import com.guang.web.service.GAppService;
import com.guang.web.service.GSysValService;
import com.guang.web.service.GUserService;
import com.guang.web.service.GUserSttService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class GUserAction extends ActionSupport{

	private static final long serialVersionUID = -6570772391551890119L;
	@Resource private  GUserService userService;
	@Resource private GAppService appService;
	@Resource private GSysValService sysValService;
	@Resource private GUserSttService userSttService;
	
	public String list()
	{
		QueryResult<GUser>  qr = userService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GUser> userList = userService.findAlls(start).getList();
		
		for(GUser u : userList)
		{
			if(GSessionHandler.getInstance().judeOnline(u.getName()))
			{
				u.setOnline(true);
			}
			else
			{
				u.setOnline(false);
			}
			if(u.getPhoneNumber() == null || "".equals(u.getPhoneNumber()))
				u.setPhoneNumber("未知");
		}
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("userList", userList);
		ActionContext.getContext().put("pages", "user");
		
		return "index";
	}
	
	//上传app信息
	public void uploadAppInfos()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data);
		GUser  user = userService.find(obj.getString("id"));
		String name = obj.getString("name");
		String packageName = obj.getString("packageName");
		String versionName = null;
		String sdkVersion = null;	
		
		//兼容之前版本，必须多一层判断
		if(obj.containsKey("versionName"))
			versionName = obj.getString("versionName");
		if(obj.containsKey("sdkVersion"))
			sdkVersion = obj.getString("sdkVersion");
		
		if(StringTools.isEmpty(versionName))
			versionName = "1.0";
		if(StringTools.isEmpty(sdkVersion))
			sdkVersion = "1.0";
		
		boolean isExist = false;
		List<GApp> list = appService.findAppsByUserId(user.getId()).getList();
		for(GApp a : list)
		{
			if(a.getPackageName().equals(packageName))
			{
				isExist = true;
				a.setUpdateSdkVersion(sdkVersion);
				a.setUpdateVersionName(versionName);
				appService.update(a);
				break;
			}
		}
		if(!isExist)
		{
			GApp app = new GApp(user.getId(), name, packageName,versionName,sdkVersion);
			appService.add(app);
		}
		
		try {
			ServletActionContext.getResponse().getWriter().print(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除user
	public void deleteUser()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			long ids = Long.parseLong(id);
			userService.delete(ids);
			
			List<GApp> list = appService.findAppsByUserId(ids).getList();
			for(GApp app : list)
			{
				appService.delete(app.getId());
			}
		}
	}
	
	//初始化基本数据
	public void init()
	{
//		GSysVal sysVal = new GSysVal(0, false, 2, "", "", 0, 1.0f);
//		sysValService.save(sysVal);
		
		userSttService.add(new GUserStt(0l,0l,0l, 0l, 0l, 0l));
	}

	//修改app model
	public void updateAppModel()
	{
		List<GApp> list = appService.findApps(0, 100000000).getList();
		for(GApp app : list)
		{
			if(StringTools.isEmpty(app.getVersionName()) || StringTools.isEmpty(app.getSdkVersion()))
			{
				app.setVersionName("1.0");
				app.setSdkVersion("1.0");
				appService.update(app);
			}
		}
	}
}
