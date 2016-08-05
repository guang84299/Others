package com.guang.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GStatistics;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GStatisticsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GStatisticsService statisticsService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GOfferService offerService;
	
	public String list()
	{
		QueryResult<GStatistics>  qr = statisticsService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GStatistics> list = statisticsService.findAlls(start).getList();
		
		for(GStatistics statistics : list)
		{
			statistics.setStatisticsType(GStatisticsType.Types[statistics.getType()]);
			statistics.setAdPosition(adPositionService.find(statistics.getAdPositionType()).getName());
			statistics.setOffer(offerService.find(statistics.getOfferId()).getName());
		}
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "statistics");
		
		return "index";
	}
		
	//删除statistics
	public void deleteStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			statisticsService.delete(Long.parseLong(id));
		}
	}
}
