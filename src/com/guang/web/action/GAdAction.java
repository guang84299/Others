package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.handler.GSessionHandler;
import com.guang.server.session.GSession;
import com.guang.web.mode.GAd;
import com.guang.web.mode.GApp;
import com.guang.web.mode.GSysVal;
import com.guang.web.mode.GUser;
import com.guang.web.service.GAdService;
import com.guang.web.service.GAppService;
import com.guang.web.service.GSysValService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.ApkTools;
import com.guang.web.tools.PinYinTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdAction extends ActionSupport {

	private static final long serialVersionUID = -1710380125163940191L;
	private static final Logger logger = LoggerFactory.getLogger(GAdAction.class);
	@Resource private GAdService adService;
	@Resource private GUserService userService;
	@Resource private GAppService appService;
	@Resource private GSysValService sysValService;
	private File pic;
	private File apk;
	private String company;
	private String type;
	private String downloadPath;
	private String picFileName;
	private String apkFileName;

	

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String list() {
		int platfrom = sysValService.find().getPlatfrom();
		ActionContext.getContext().put("platfrom", platfrom);
		ActionContext.getContext().put("pages", "ad");
		return "index";
	}

	// 返回数据
	public void print(Object data) {
		try {
			ServletActionContext.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String uploadAd() {		
		if(StringTools.isEmpty(company) || pic == null ||
				(StringTools.isEmpty(downloadPath) && apk == null))
		{
			ActionContext.getContext().put("uploadAd", "上传失败！");
			ActionContext.getContext().put("pages", "ad");
			return "index";
		}
		String company_py = PinYinTools.getPinYin(company);
		
		String img_relpath = ServletActionContext.getServletContext().getRealPath(
				"images/"+company_py);
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"apks/"+company_py);
		try {
			//上传图片
			File file = new File(new File(img_relpath), picFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(pic, file);
			String picPath = "images/"+company_py + "/" + picFileName;
			//上传apk		
			String packageName = "未知";
			if(apk != null)
			{
				file = new File(new File(apk_relpath), apkFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk, file);
				
				downloadPath = apk_relpath + "/" + apkFileName;
				String []str = ApkTools.unZip(downloadPath, "");	
				packageName = str[1];
				downloadPath = "apks/"+company_py+ "/" + apkFileName;
			}
			else
			{
				if(StringTools.isEmpty(downloadPath))
				{
					ActionContext.getContext().put("uploadAd", "上传失败！");
					ActionContext.getContext().put("pages", "ad");
					return "index";
				}
			}
			
			GAd ad = new GAd(company, Integer.parseInt(type), packageName, picPath, downloadPath);
			adService.add(ad);
			ActionContext.getContext().put("uploadAd", "上传成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("uploadAd", "上传失败！");
		}
		ActionContext.getContext().put("pages", "ad");
		return "index";
	}
	
	public String changeAd()
	{
		String broadcast = ServletActionContext.getRequest().getParameter("broadcast");
		String username = ServletActionContext.getRequest().getParameter("username");
		String appname = ServletActionContext.getRequest().getParameter("appname");
		String ad_platfrom = ServletActionContext.getRequest().getParameter("ad_platfrom");
		
		if("all".equals(broadcast))
		{
			HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
			Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
				GSession val = entry.getValue();
				val.changeAd(Integer.parseInt(ad_platfrom));
			}
		}
		else if("app".equals(broadcast))
		{
			HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
			Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
				GSession val = entry.getValue();
				GUser user = userService.find(val.getName());
				if(user != null && isAppName(user,appname))
				{
					val.changeAd(Integer.parseInt(ad_platfrom));
				}
			}
		}
		else
		{
			GSession session = GSessionHandler.getInstance().getSessionByName(username);
			if(session != null)
				session.changeAd(Integer.parseInt(ad_platfrom));
		}
		GSysVal sv = sysValService.find();
		sv.setPlatfrom(Integer.parseInt(ad_platfrom));
		sysValService.update(sv);
		
		ActionContext.getContext().put("platfrom", sv.getPlatfrom());
		ActionContext.getContext().put("pages", "ad");
		return "index";
	}
	
	public boolean isAppName(GUser user,String appname)
	{
		List<GApp> list = appService.findAppsByUserId(user.getId()).getList();
		if(list.size() > 0)
		{
			for(GApp app : list)
			{
				if(app.getPackageName().equals(appname))
					return true;
			}
		}
		return false;
	}
}
