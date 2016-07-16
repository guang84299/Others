package com.guang.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.guang.server.handler.GSessionHandler;
import com.guang.server.session.GSession;
import com.guang.web.mode.GSessionUser;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GUserStt;
import com.guang.web.service.GUserService;
import com.guang.web.service.GUserSttService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GSessionUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private GUserService userService;
	@Resource private GUserSttService userSttService;

	public String list() {
		HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
		List<GSessionUser> list = new ArrayList<GSessionUser>();

		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		int num = sessions.size();
		int start = index * 20;
		int end = (index + 1) * 20;

		if (start > num) {
			start = 0;
			if (num <= 20)
				end = num;
			else
				end = 20;
		} else {
			if (num <= end)
				end = num;
		}

		Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
		int i = 0;
		while (iter.hasNext() && i < end) {
			if(i >= start)
			{
				Entry<Long, GSession> entry = (Entry<Long, GSession>) iter.next();
				GSession val = entry.getValue();
				String ip = val.getSession().getRemoteAddress().toString();
				ip = ip.replace("/", "").split(":")[0];
				GUser u = userService.find(val.getName());
				list.add(new GSessionUser(val.getSession().getId(),u.getId(), val.getName(),
						true,ip, u.getCreatedDate()));
			}
			i++;
		}
		GUserStt userStt = userSttService.find();
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("userStt", userStt);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("pages", "session");
		return "index";
	}

}
