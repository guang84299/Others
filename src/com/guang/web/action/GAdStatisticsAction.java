package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

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

public class GAdStatisticsAction extends ActionSupport{

	private static final long serialVersionUID = -1559603936397543079L;
	private static final Logger logger = LoggerFactory.getLogger(GAdStatisticsAction.class);
	@Resource private GAdService adService;
	@Resource private GPushService pushService;
	@Resource private GUserPushService userPushService;

	public String list() {

		QueryResult<GAd>  qr = adService.findAds(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (!StringTools.isEmpty(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GAd> adList = adService.findAds(start).getList();
			
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", adList);		
		ActionContext.getContext().put("pages", "adStatistics");
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
	//删除AdStatistics
	public void deleteAdStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			adService.delete(Long.parseLong(id));
			List<GPush> list = pushService.findByAdId(Long.parseLong(id)).getList();
			for(GPush push : list)
			{
				List<GUserPush> ups = userPushService.findByPushId(push.getId(), 0).getList();			
				for(GUserPush up : ups)
				{
					userPushService.delete(up.getId());
				}
				pushService.delete(push.getId());
			}
		}
	}
	
	public void findAdStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			GAd ad = adService.find(Long.parseLong(id));
			print(JSONObject.fromObject(ad).toString());
		}
	}
	
	public String updateAdStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String showLevel = ServletActionContext.getRequest().getParameter("showLevel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(showLevel))
		{
			GAd ad = adService.find(Long.parseLong(id));
			ad.setShowLevel(Integer.parseInt(showLevel));
			ad.setPackageName(packageName);
			adService.update(ad);
			ActionContext.getContext().put("updateAdStatistics","更改成功！");
			list();
			return "index";
		}
		ActionContext.getContext().put("updateAdStatistics","更改失败！");
		list();
		return "index";
	}
}
