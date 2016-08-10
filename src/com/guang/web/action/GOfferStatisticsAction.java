package com.guang.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOffer;
import com.guang.web.mode.GOfferStatistics;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GStatisticsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GStatisticsService statisticsService;	
	@Resource private GOfferService offerService;
	
	@SuppressWarnings("deprecation")
	public String list() 
	{		
		QueryResult<GOffer>  qr = offerService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		Date date = new Date();	
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		String from = date.toLocaleString();
		date.setDate(date.getDate()+1);
		String to = date.toLocaleString();
		
		List<GOffer> list = offerService.findAlls(start).getList();
		List<GOfferStatistics> slist = new ArrayList<GOfferStatistics>();
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("uploadTime >=", "'"+from+"'");
		colvals.put("uploadTime <", "'"+to+"'");
		for(GOffer offer : list)
		{
			long offerId = offer.getId();
			String offerName  = offer.getName();
			
			colvals.remove("offerId =");
			colvals.put("offerId =", offerId+"");
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.REQUEST+"");
			long requestNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.SHOW+"");
			long showNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.CLICK+"");
			long clickNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOWNLOAD+"");
			long downloadNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS+"");
			long downloadSuccessNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.INSTALL+"");
			long installNum = statisticsService.findAlls(colvals).getNum();
					
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.ACTIVATE+"");
			long activateNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_SHOW + "");
			showNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_CLICK + "");
			clickNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD + "");
			downloadNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD_SUCCESS + "");
			downloadSuccessNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_INSTALL + "");
			installNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_ACTIVATE + "");
			activateNum += statisticsService.findAlls(colvals).getNum();
			
			float income = activateNum;
			
			GOfferStatistics offerStatistics = new GOfferStatistics(offerId, offerName, requestNum, showNum, clickNum, downloadNum,
					downloadSuccessNum, installNum, activateNum, income);
			slist.add(offerStatistics);
		}
				
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", slist);
		ActionContext.getContext().put("pages", "offerStatistics");
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
	
	public void list2() 
	{		
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String doubleSta = ServletActionContext.getRequest().getParameter("doubleSta");
						
		List<GOffer> list = offerService.findAlls(0).getList();
		List<GOfferStatistics> slist = new ArrayList<GOfferStatistics>();
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("uploadTime >=", "'"+from+"'");
		colvals.put("uploadTime <", "'"+to+"'");
		for(GOffer offer : list)
		{
			long offerId = offer.getId();
			String offerName  = offer.getName();
				
			colvals.remove("offerId =");
			colvals.put("offerId =", offerId+"");	
			
			colvals.remove("type =");			
			colvals.put("type =", GStatisticsType.REQUEST+"");			
			long requestNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.SHOW+"");
			long showNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.CLICK+"");
			long clickNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOWNLOAD+"");
			long downloadNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS+"");
			long downloadSuccessNum = statisticsService.findAlls(colvals).getNum();
			
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.INSTALL+"");
			long installNum = statisticsService.findAlls(colvals).getNum();
						
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.ACTIVATE+"");
			long activateNum = statisticsService.findAlls(colvals).getNum();
			
			if(doubleSta != null && "1".equals(doubleSta))
			{
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_SHOW + "");
				showNum += statisticsService.findAlls(colvals).getNum();

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_CLICK + "");
				clickNum += statisticsService.findAlls(colvals).getNum();

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD + "");
				downloadNum += statisticsService.findAlls(colvals).getNum();

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD_SUCCESS + "");
				downloadSuccessNum += statisticsService.findAlls(colvals).getNum();

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_INSTALL + "");
				installNum += statisticsService.findAlls(colvals).getNum();

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOUBLE_ACTIVATE + "");
				activateNum += statisticsService.findAlls(colvals).getNum();
			}
			
			float income = activateNum;
			
			GOfferStatistics offerStatistics = new GOfferStatistics(offerId, offerName, requestNum, showNum, clickNum, downloadNum,
					downloadSuccessNum, installNum, activateNum, income);
			slist.add(offerStatistics);
		}
				
		print(JSONArray.fromObject(slist).toString());
	}
	
	
}
