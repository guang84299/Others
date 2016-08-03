package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.handler.GSessionHandler;
import com.guang.server.session.GSession;
import com.guang.web.mode.GAd;
import com.guang.web.mode.GAdApp;
import com.guang.web.mode.GApp;
import com.guang.web.mode.GSysVal;
import com.guang.web.mode.GUser;
import com.guang.web.service.GAdAppService;
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
	@Resource private GAdAppService adAppService;
	
	private File pic;
	private File picHorizontal;
	private File picNotify;
	private File apk;
	private String company;
	private String type;
	private String downloadPath;
	private String picFileName;
	private String picHorizontalFileName;
	private String picNotifyFileName;
	private String apkFileName;
	
	private String adId;
	private String name;
	private String developer;
	private String describe;
	private String m_size;
	private File icon_path;
	private File pic_path_1;
	private File pic_path_2;
	private File pic_path_3;
	private File pic_path_4;
	private File pic_path_5;
	private File pic_path_6;
	private String icon_pathFileName;
	private String pic_path_1FileName;
	private String pic_path_2FileName;
	private String pic_path_3FileName;
	private String pic_path_4FileName;
	private String pic_path_5FileName;
	private String pic_path_6FileName;
	private String summary;
	private String downloads;
	private String version;
	private String updatedDate;

	

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
		if(StringTools.isEmpty(company) || pic == null || picHorizontal == null || picNotify == null || 
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
			//上传横图
			file = new File(new File(img_relpath), picHorizontalFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(picHorizontal, file);
			String picHorizontalPath = "images/"+company_py + "/" + picHorizontalFileName;
			//上传通知图
			file = new File(new File(img_relpath), picNotifyFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(picNotify, file);
			String picNotifyPath = "images/"+company_py + "/" + picNotifyFileName;
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
			
			GAd ad = new GAd(company, Integer.parseInt(type), packageName, picPath,
					picHorizontalPath,picNotifyPath, downloadPath);
			adService.add(ad);
			ActionContext.getContext().put("uploadAd", "上传成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("uploadAd", "上传失败！");
		}
		ActionContext.getContext().put("pages", "ad");
		return "index";
	}
	
	public void getAdApp()
	{
		String ad_id = ServletActionContext.getRequest().getParameter("data");
		long adId = Long.parseLong(ad_id);
		GAdApp adApp = adAppService.findByAdId(adId);
		print(JSONObject.fromObject(adApp).toString());
	}
	
	public String updateAdApp()
	{
		if(StringTools.isEmpty(adId) || StringTools.isEmpty(name) || StringTools.isEmpty(developer) || StringTools.isEmpty(m_size) || icon_path == null)
		{
			ActionContext.getContext().put("updateAdApp", "提交失败！");
			ActionContext.getContext().put("pages", "ad");
			return "index";
		}
		long ad_id = Long.parseLong(adId);
		GAd ad = adService.find(ad_id);
		if(ad == null)
		{
			ActionContext.getContext().put("updateAdApp", "提交失败！");
			ActionContext.getContext().put("pages", "ad");
			return "index";
		}
		String developer_py = PinYinTools.getPinYin(ad.getCompany());
		String img_relpath = ServletActionContext.getServletContext().getRealPath(
				"images/"+developer_py);
		try {
			//上传图片
			File file = new File(new File(img_relpath), icon_pathFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(icon_path, file);
			icon_pathFileName = "images/"+developer_py + "/" + icon_pathFileName;
			if(pic_path_1 != null)
			{
				file = new File(new File(img_relpath), pic_path_1FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_1, file);
				pic_path_1FileName = "images/"+developer_py + "/" + pic_path_1FileName;
			}
			if(pic_path_2 != null)
			{
				file = new File(new File(img_relpath), pic_path_2FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_2, file);
				pic_path_2FileName = "images/"+developer_py + "/" + pic_path_2FileName;
			}
			if(pic_path_3 != null)
			{
				file = new File(new File(img_relpath), pic_path_3FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_3, file);
				pic_path_3FileName = "images/"+developer_py + "/" + pic_path_3FileName;
			}
			if(pic_path_4 != null)
			{
				file = new File(new File(img_relpath), pic_path_4FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_4, file);
				pic_path_4FileName = "images/"+developer_py + "/" + pic_path_4FileName;
			}
			if(pic_path_5 != null)
			{
				file = new File(new File(img_relpath), pic_path_5FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_5, file);
				pic_path_5FileName = "images/"+developer_py + "/" + pic_path_5FileName;
			}
			if(pic_path_6 != null)
			{
				file = new File(new File(img_relpath), pic_path_6FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(pic_path_6, file);
				pic_path_6FileName = "images/"+developer_py + "/" + pic_path_6FileName;
			}
			GAdApp adApp = adAppService.findByAdId(ad_id);
						
			if(adApp == null)
			{
				 adApp = new GAdApp(ad_id, name, icon_pathFileName, pic_path_1FileName, pic_path_2FileName,
						 pic_path_3FileName, pic_path_4FileName, pic_path_5FileName, pic_path_6FileName,
						 developer, describe, Float.parseFloat(m_size),summary,Integer.parseInt(downloads),version,updatedDate);
				 adAppService.add(adApp);				 
			}
			else
			{
				adApp.setName(name);
				adApp.setIcon_path(icon_pathFileName);
				adApp.setPic_path_1(pic_path_1FileName);
				adApp.setPic_path_2(pic_path_2FileName);
				adApp.setPic_path_3(pic_path_3FileName);
				adApp.setPic_path_4(pic_path_4FileName);
				adApp.setPic_path_5(pic_path_5FileName);
				adApp.setPic_path_6(pic_path_6FileName);
				adApp.setDeveloper(developer);
				adApp.setDescribe(describe);
				adApp.setSize_m(Float.parseFloat(m_size));
				adApp.setSummary(summary);
				adApp.setDownloads(Integer.parseInt(downloads));
				adApp.setVersion(version);
				adApp.setUpdatedDate(updatedDate);
				adAppService.update(adApp);
			}
			ActionContext.getContext().put("updateAdApp", "提交成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("updateAdApp", "提交失败！");
		}
		ActionContext.getContext().put("pages", "ad");
		return "index";
	}
	
	public void getAdPlatfrom()
	{
		GSysVal sv = sysValService.find();
		print(sv.getPlatfrom());
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
			synchronized (sessions) {
				Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
					GSession val = entry.getValue();
					val.changeAd(Integer.parseInt(ad_platfrom));
				}
			}
		}
		else if("app".equals(broadcast))
		{
			HashMap<Long, GSession> sessions = GSessionHandler.getSessions();
			synchronized (sessions) {
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

	public File getPicHorizontal() {
		return picHorizontal;
	}

	public void setPicHorizontal(File picHorizontal) {
		this.picHorizontal = picHorizontal;
	}

	public File getPicNotify() {
		return picNotify;
	}

	public void setPicNotify(File picNotify) {
		this.picNotify = picNotify;
	}

	public String getPicHorizontalFileName() {
		return picHorizontalFileName;
	}

	public void setPicHorizontalFileName(String picHorizontalFileName) {
		this.picHorizontalFileName = picHorizontalFileName;
	}

	public String getPicNotifyFileName() {
		return picNotifyFileName;
	}

	public void setPicNotifyFileName(String picNotifyFileName) {
		this.picNotifyFileName = picNotifyFileName;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getM_size() {
		return m_size;
	}

	public void setM_size(String m_size) {
		this.m_size = m_size;
	}

	public File getIcon_path() {
		return icon_path;
	}

	public void setIcon_path(File icon_path) {
		this.icon_path = icon_path;
	}

	public File getPic_path_1() {
		return pic_path_1;
	}

	public void setPic_path_1(File pic_path_1) {
		this.pic_path_1 = pic_path_1;
	}

	public File getPic_path_2() {
		return pic_path_2;
	}

	public void setPic_path_2(File pic_path_2) {
		this.pic_path_2 = pic_path_2;
	}

	public File getPic_path_3() {
		return pic_path_3;
	}

	public void setPic_path_3(File pic_path_3) {
		this.pic_path_3 = pic_path_3;
	}

	public File getPic_path_4() {
		return pic_path_4;
	}

	public void setPic_path_4(File pic_path_4) {
		this.pic_path_4 = pic_path_4;
	}

	public File getPic_path_5() {
		return pic_path_5;
	}

	public void setPic_path_5(File pic_path_5) {
		this.pic_path_5 = pic_path_5;
	}

	public File getPic_path_6() {
		return pic_path_6;
	}

	public void setPic_path_6(File pic_path_6) {
		this.pic_path_6 = pic_path_6;
	}

	public String getPic_path_1FileName() {
		return pic_path_1FileName;
	}

	public void setPic_path_1FileName(String pic_path_1FileName) {
		this.pic_path_1FileName = pic_path_1FileName;
	}

	public String getPic_path_2FileName() {
		return pic_path_2FileName;
	}

	public void setPic_path_2FileName(String pic_path_2FileName) {
		this.pic_path_2FileName = pic_path_2FileName;
	}

	public String getPic_path_3FileName() {
		return pic_path_3FileName;
	}

	public void setPic_path_3FileName(String pic_path_3FileName) {
		this.pic_path_3FileName = pic_path_3FileName;
	}

	public String getPic_path_4FileName() {
		return pic_path_4FileName;
	}

	public void setPic_path_4FileName(String pic_path_4FileName) {
		this.pic_path_4FileName = pic_path_4FileName;
	}

	public String getPic_path_5FileName() {
		return pic_path_5FileName;
	}

	public void setPic_path_5FileName(String pic_path_5FileName) {
		this.pic_path_5FileName = pic_path_5FileName;
	}

	public String getPic_path_6FileName() {
		return pic_path_6FileName;
	}

	public void setPic_path_6FileName(String pic_path_6FileName) {
		this.pic_path_6FileName = pic_path_6FileName;
	}

	public String getIcon_pathFileName() {
		return icon_pathFileName;
	}

	public void setIcon_pathFileName(String icon_pathFileName) {
		this.icon_pathFileName = icon_pathFileName;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDownloads() {
		return downloads;
	}

	public void setDownloads(String downloads) {
		this.downloads = downloads;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	//修改 ad model
	public void updateAdModel()
	{
		List<GAd> list = adService.findAds(0).getList();
		for(GAd ad : list)
		{
			ad.setShowLevel(1);
			adService.update(ad);
		}
	}
}
