package com.guang.web.action;

import java.util.List;

import javax.annotation.Resource;

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
	//É¾³ýAdStatistics
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
}
