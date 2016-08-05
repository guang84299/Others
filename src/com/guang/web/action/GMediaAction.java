package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GMedia;
import com.guang.web.service.GMediaService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GMediaAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GMediaService mediaService;
	
	public String list()
	{
		QueryResult<GMedia>  qr = mediaService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GMedia> list = mediaService.findAlls(start).getList();
			
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "media");
		
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
	
	public String addMedia()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(!StringTools.isEmpty(name) && !StringTools.isEmpty(packageName))
		{
			mediaService.add(new GMedia(name, packageName));
			ActionContext.getContext().put("addMedia", "添加成功！");
		}
		else
		{
			ActionContext.getContext().put("addMedia", "添加失败！");
		}
		return list();
	}
	
	public void deleteMedia()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			mediaService.delete(Long.parseLong(id));
		}
	}
	
	public String updateMedia()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String name = ServletActionContext.getRequest().getParameter("name");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(name) && !StringTools.isEmpty(packageName))
		{
			GMedia media = mediaService.find(Long.parseLong(id));
			media.setName(name);
			media.setPackageName(packageName);
			
			mediaService.update(media);
			ActionContext.getContext().put("updateMedia", "更改成功！");
		}
		else
		{
			ActionContext.getContext().put("updateMedia", "更改失败！");
		}
		return list();
	}
	
	public void findMedia()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			GMedia media = mediaService.find(Long.parseLong(id));
			print(JSONObject.fromObject(media).toString());
		}
	}
}
