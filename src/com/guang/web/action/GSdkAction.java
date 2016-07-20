package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GSdk;
import com.guang.web.service.GSdkService;
import com.guang.web.tools.ApkTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GSdkAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(GSdkAction.class);
	private static final long serialVersionUID = 1L;
	@Resource private GSdkService sdkService;
	
	private File apk;
	private String apkFileName;
	private String online_state;
	
	public String list() {

		QueryResult<GSdk>  qr = sdkService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GSdk> sdkList = sdkService.findAlls(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", sdkList);
		ActionContext.getContext().put("pages", "sdk");
		
		return "index";
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String addSdk()
	{
		if(apk == null)
		{
			ActionContext.getContext().put("addSdk", "添加失败！");
			list();
			return "index";
		}
		GSdk sdk = sdkService.findNew();
		String code = "1";
		if(sdk != null)
		{
			code = sdk.getVersionCode();
			code = (Integer.parseInt(code)+1)+"";
		}
			
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"sdk/" + code);
		try {
			//上传apk		
			File file = new File(new File(apk_relpath), apkFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(apk, file);
			String downloadPath = apk_relpath + "/" + apkFileName;
			String []str = ApkTools.unZip(downloadPath, "");	
			String versionName = str[0];
			String packageName = str[1];
			String versionCode = str[2];
			boolean online = false;
			if(online_state != null && "1".equals(online_state))
			{
				online = true;
			}
			downloadPath = "sdk/" + code +  "/" + apkFileName;
			sdkService.add(new GSdk(packageName, versionName, versionCode, downloadPath, online));
			ActionContext.getContext().put("addSdk", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addSdk", "添加失败！");
		}
		list();
		return "index";
	}
	//删除sdk
	public void deleteSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			sdkService.delete(Long.parseLong(id));
		}
	}
	
	public void findSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			print(JSONObject.fromObject(sdk).toString());
		}
	}
	
	public String updateSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String online_state = ServletActionContext.getRequest().getParameter("online_state");
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			if("1".equals(online_state))
				sdk.setOnline(true);
			else
				sdk.setOnline(false);
			sdkService.update(sdk);
			
			ActionContext.getContext().put("updateSdk","更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateSdk","更改失败！");
		return "index";
	}
	
	
	public void findNewSdk()
	{		
		GSdk sdk = sdkService.findNew();
		print(JSONObject.fromObject(sdk).toString());
	}
	
	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String getOnline_state() {
		return online_state;
	}

	public void setOnline_state(String online_state) {
		this.online_state = online_state;
	}
	
	
}
