package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;


import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.mode.GatherAppRunInfo;
import com.guang.web.service.GGatherDataService;
import com.guang.web.service.RunAppInfoService;
import com.opensymphony.xwork2.ActionContext;

public class GGatherDataAction {
	//app上传信息
	@Resource GGatherDataService dataService;
	//app运行
	@Resource RunAppInfoService appInfoService;
	/**
	 * 每天上传的app信息
	 * @return
	 */
	public String list(){
		QueryResult<GatherAppInfo>  qr = dataService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		System.out.println("sindex~~~~~~~~~~~~~~:"+sindex);
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {	
			start = 0;
		}
		List<GatherAppInfo> appInfoList = dataService.findAlls(start).getList();
		ActionContext.getContext().put("appInfoList", appInfoList);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("pages", "gather");
		
		//app运行信息
		QueryResult<GatherAppRunInfo>  qrg = appInfoService.findAlls(0);
		String rSindex = ServletActionContext.getRequest().getParameter("index");
		int rIndex = 0;
		if (rSindex != null && !"".equals(rSindex))
		rIndex = Integer.parseInt(rSindex);
		Long sNum = qrg.getNum();
		int starts = rIndex * 20;
		if (starts > sNum) {
			starts = 0;
		}
		List<GatherAppRunInfo> runInfoList = appInfoService.findAlls(starts).getList();
		ActionContext.getContext().put("runInfoList", runInfoList);
		ActionContext.getContext().put("rmaxNum", sNum);
		ActionContext.getContext().put("pages", "gather");
		return "index";
	}
	
	public String list2(){
		QueryResult<GatherAppInfo>  qr = dataService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {	
			start = 0;
		}
		List<GatherAppInfo> appInfoList = dataService.findAlls(start).getList();
		ActionContext.getContext().put("appInfoList", appInfoList);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("pages", "gather");
		
		
		//app运行信息
		QueryResult<GatherAppRunInfo>  qrg = appInfoService.findAlls(0);
		String rSindex = ServletActionContext.getRequest().getParameter("index");
		System.out.println("rSindex~~~~~~~~~~~~~"+rSindex);
		int rIndex = 0;
		if (rSindex != null && !"".equals(rSindex))
			rIndex = Integer.parseInt(rSindex);
		Long sNum = qrg.getNum();
		int starts = rIndex * 20;
		if (starts > sNum) {
			starts = 0;
		}
		System.out.println("starts~~~~~~~~~~~~~"+starts);
		List<GatherAppRunInfo> runInfoList = appInfoService.findAlls(starts).getList();
		System.out.println("runInfoList~~~~~~~~~~~~~"+runInfoList);
		ActionContext.getContext().put("runInfoList", runInfoList);
		ActionContext.getContext().put("rmaxNum", sNum);
		ActionContext.getContext().put("pages", "gather");
		return "index";
	}
	/**
	 *  删除APP上传信息
	 */
	public void deleteAppInfo(){
		String id = ServletActionContext.getRequest().getParameter("id");
		dataService.delete(Integer.parseInt(id));
	}
	
	
	/**
	 *  删除APP运行信息
	 */
	public void deleteRunInfo(){
		String id = ServletActionContext.getRequest().getParameter("id");
		appInfoService.delete(Integer.parseInt(id));
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
