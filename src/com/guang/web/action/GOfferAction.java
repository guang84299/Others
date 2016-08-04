package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOffer;
import com.guang.web.service.GOfferService;
import com.guang.web.tools.ApkTools;
import com.guang.web.tools.PinYinTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Resource private GOfferService offerService;
	
	private Long id;
	private String name;
	private String apk_developer;
	private String apk_describe;
	private Float apk_size;
	private String apk_summary;
	private Integer apk_downloads;
	private String apk_version;
	private String apk_updatedDate;
	private String apkDownloadPath;
	private String apkPackageName;
	
	private File openSpotPic;
	private File bannerPic;
	private File apk;
	private File apk_icon;
	private File apk_pic_1;
	private File apk_pic_2;
	private File apk_pic_3;
	private File apk_pic_4;
	private File apk_pic_5;
	private File apk_pic_6;

	private String openSpotPicFileName;
	private String bannerPicFileName;
	private String apkFileName;
	private String apk_iconFileName;
	private String apk_pic_1FileName;
	private String apk_pic_2FileName;
	private String apk_pic_3FileName;
	private String apk_pic_4FileName;
	private String apk_pic_5FileName;
	private String apk_pic_6FileName;

	
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
		
		List<GOffer> list = offerService.findAlls(start).getList();
				
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "offer");
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
	
	public String addOffer()
	{
		if(StringTools.isEmpty(name) || (StringTools.isEmpty(apkDownloadPath) && apk == null)
				|| (StringTools.isEmpty(apkPackageName) && apk == null))
		{
			ActionContext.getContext().put("addOffer", "添加失败！");
			return list();
		}
		String name_py = PinYinTools.getPinYin(name);
		
		String img_relpath = ServletActionContext.getServletContext().getRealPath(
				"images/"+name_py);
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"apks/"+name_py);
		try {
			//上传开屏图片
			String openSpotPicPath = null;
			if(openSpotPic != null)
			{
				File file = new File(new File(img_relpath), openSpotPicFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(openSpotPic, file);
				openSpotPicPath = "images/"+name_py + "/" + openSpotPicFileName;
			}			
			//上传banner图片
			String bannerPicPath = null;
			if(bannerPic != null)
			{
				File file = new File(new File(img_relpath), bannerPicFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(bannerPic, file);
				bannerPicPath = "images/"+name_py + "/" + bannerPicFileName;
			}			
			//上传apk_icon图片
			String apk_icon_path = null;
			if(apk_icon != null)
			{
				File file = new File(new File(img_relpath), apk_iconFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_icon, file);
				apk_icon_path = "images/"+name_py + "/" + apk_iconFileName;
			}
			//上传apk_pic_1图片
			String apk_pic_path_1 = null;
			if(apk_pic_1 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_1FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_1, file);
				apk_pic_path_1 = "images/"+name_py + "/" + apk_pic_1FileName;
			}
			//上传apk_pic_2图片
			String apk_pic_path_2 = null;
			if(apk_pic_2 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_2FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_2, file);
				apk_pic_path_2 = "images/"+name_py + "/" + apk_pic_2FileName;
			}
			//上传apk_pic_3图片
			String apk_pic_path_3 = null;
			if(apk_pic_3 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_3FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_3, file);
				apk_pic_path_3 = "images/"+name_py + "/" + apk_pic_3FileName;
			}
			//上传apk_pic_4图片
			String apk_pic_path_4 = null;
			if(apk_pic_4 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_4FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_4, file);
				apk_pic_path_4 = "images/"+name_py + "/" + apk_pic_4FileName;
			}
			//上传apk_pic_5图片
			String apk_pic_path_5 = null;
			if(apk_pic_5 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_5FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_5, file);
				apk_pic_path_5 = "images/"+name_py + "/" + apk_pic_5FileName;
			}
			//上传apk_pic_6图片
			String apk_pic_path_6 = null;
			if(apk_pic_6 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_6FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_6, file);
				apk_pic_path_6 = "images/"+name_py + "/" + apk_pic_6FileName;
			}
			//上传apk
			if(apk != null)
			{
				File file = new File(new File(apk_relpath), apkFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk, file);
				apkDownloadPath = "apks/"+name_py + "/" + apkFileName;
				
				String []str = ApkTools.unZip(file.getAbsolutePath(), "");	
				apkPackageName = str[1];
			}
			GOffer offer = new GOffer(name, openSpotPicPath, bannerPicPath, apkDownloadPath,
					apkPackageName, apk_icon_path, apk_pic_path_1, apk_pic_path_2, apk_pic_path_3,
					apk_pic_path_4, apk_pic_path_5, apk_pic_path_6, apk_developer, 
					apk_describe, apk_size, apk_summary, apk_downloads, apk_version, apk_updatedDate);
			offerService.add(offer);
			ActionContext.getContext().put("addOffer", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addOffer", "添加失败！");
		}
		return list();
	}
	
	public String updateOffer()
	{
		if(StringTools.isEmpty(name) || id == null)
		{
			ActionContext.getContext().put("updateOffer", "更改失败！");
			return list();
		}
		GOffer offer = offerService.find(id);
		
		String name_py = PinYinTools.getPinYin(name);
		
		String img_relpath = ServletActionContext.getServletContext().getRealPath(
				"images/"+name_py);
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"apks/"+name_py);
		try {
			//上传开屏图片
			String openSpotPicPath = null;
			if(openSpotPic != null)
			{
				File file = new File(new File(img_relpath), openSpotPicFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(openSpotPic, file);
				openSpotPicPath = "images/"+name_py + "/" + openSpotPicFileName;
				offer.setOpenSpotPicPath(openSpotPicPath);
			}			
			//上传banner图片
			String bannerPicPath = null;
			if(bannerPic != null)
			{
				File file = new File(new File(img_relpath), bannerPicFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(bannerPic, file);
				bannerPicPath = "images/"+name_py + "/" + bannerPicFileName;
				offer.setBannerPicPath(bannerPicPath);
			}			
			//上传apk_icon图片
			String apk_icon_path = null;
			if(apk_icon != null)
			{
				File file = new File(new File(img_relpath), apk_iconFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_icon, file);
				apk_icon_path = "images/"+name_py + "/" + apk_iconFileName;
				offer.setApk_icon_path(apk_icon_path);
			}
			//上传apk_pic_1图片
			String apk_pic_path_1 = null;
			if(apk_pic_1 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_1FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_1, file);
				apk_pic_path_1 = "images/"+name_py + "/" + apk_pic_1FileName;
				offer.setApk_pic_path_1(apk_pic_path_1);
			}
			//上传apk_pic_2图片
			String apk_pic_path_2 = null;
			if(apk_pic_2 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_2FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_2, file);
				apk_pic_path_2 = "images/"+name_py + "/" + apk_pic_2FileName;
				offer.setApk_pic_path_2(apk_pic_path_2);
			}
			//上传apk_pic_3图片
			String apk_pic_path_3 = null;
			if(apk_pic_3 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_3FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_3, file);
				apk_pic_path_3 = "images/"+name_py + "/" + apk_pic_3FileName;
				offer.setApk_pic_path_3(apk_pic_path_3);
			}
			//上传apk_pic_4图片
			String apk_pic_path_4 = null;
			if(apk_pic_4 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_4FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_4, file);
				apk_pic_path_4 = "images/"+name_py + "/" + apk_pic_4FileName;
				offer.setApk_pic_path_4(apk_pic_path_4);
			}
			//上传apk_pic_5图片
			String apk_pic_path_5 = null;
			if(apk_pic_5 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_5FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_5, file);
				apk_pic_path_5 = "images/"+name_py + "/" + apk_pic_5FileName;
				offer.setApk_pic_path_5(apk_pic_path_5);
			}
			//上传apk_pic_6图片
			String apk_pic_path_6 = null;
			if(apk_pic_6 != null)
			{
				File file = new File(new File(img_relpath), apk_pic_6FileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk_pic_6, file);
				apk_pic_path_6 = "images/"+name_py + "/" + apk_pic_6FileName;
				offer.setApk_pic_path_6(apk_pic_path_6);
			}
			//上传apk
			if(apk != null)
			{
				File file = new File(new File(apk_relpath), apkFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(apk, file);
				apkDownloadPath = "apks/"+name_py + "/" + apkFileName;
				
				String []str = ApkTools.unZip(file.getAbsolutePath(), "");	
				apkPackageName = str[1];
				offer.setApkDownloadPath(apkDownloadPath);
				offer.setApkPackageName(apkPackageName);
			}
			
			offer.setName(name);
			if(!StringTools.isEmpty(apk_developer))
				offer.setApk_developer(apk_developer);
			if(!StringTools.isEmpty(apk_describe))
				offer.setApk_describe(apk_describe);
			if(apk_size != null)
				offer.setApk_size(apk_size);
			if(!StringTools.isEmpty(apk_summary))
				offer.setApk_summary(apk_summary);
			if(apk_downloads != null)
				offer.setApk_downloads(apk_downloads);
			if(apk_version != null)
				offer.setApk_version(apk_version);
			if(!StringTools.isEmpty(apk_updatedDate))
				offer.setApk_updatedDate(apk_updatedDate);
			
			offerService.update(offer);
			ActionContext.getContext().put("updateOffer", "更改成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("updateOffer", "更改失败！");
		}
		return list();
	}

	public void deleteOffer()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			offerService.delete(Long.parseLong(id));
		}
	}
	
	public void findOffer()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(data))
		{
			GOffer offer = offerService.find(Long.parseLong(data));
			print(JSONObject.fromObject(offer).toString());
		}
	}		
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApk_developer() {
		return apk_developer;
	}

	public void setApk_developer(String apk_developer) {
		this.apk_developer = apk_developer;
	}

	public String getApk_describe() {
		return apk_describe;
	}

	public void setApk_describe(String apk_describe) {
		this.apk_describe = apk_describe;
	}

	public Float getApk_size() {
		return apk_size;
	}

	public void setApk_size(Float apk_size) {
		this.apk_size = apk_size;
	}

	public String getApk_summary() {
		return apk_summary;
	}

	public void setApk_summary(String apk_summary) {
		this.apk_summary = apk_summary;
	}

	public Integer getApk_downloads() {
		return apk_downloads;
	}

	public void setApk_downloads(Integer apk_downloads) {
		this.apk_downloads = apk_downloads;
	}

	public String getApk_version() {
		return apk_version;
	}

	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}

	public String getApk_updatedDate() {
		return apk_updatedDate;
	}

	public void setApk_updatedDate(String apk_updatedDate) {
		this.apk_updatedDate = apk_updatedDate;
	}

	public String getApkDownloadPath() {
		return apkDownloadPath;
	}

	public void setApkDownloadPath(String apkDownloadPath) {
		this.apkDownloadPath = apkDownloadPath;
	}

	public String getApkPackageName() {
		return apkPackageName;
	}

	public void setApkPackageName(String apkPackageName) {
		this.apkPackageName = apkPackageName;
	}

	public File getOpenSpotPic() {
		return openSpotPic;
	}

	public void setOpenSpotPic(File openSpotPic) {
		this.openSpotPic = openSpotPic;
	}

	public File getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(File bannerPic) {
		this.bannerPic = bannerPic;
	}

	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public File getApk_icon() {
		return apk_icon;
	}

	public void setApk_icon(File apk_icon) {
		this.apk_icon = apk_icon;
	}

	public File getApk_pic_1() {
		return apk_pic_1;
	}

	public void setApk_pic_1(File apk_pic_1) {
		this.apk_pic_1 = apk_pic_1;
	}

	public File getApk_pic_2() {
		return apk_pic_2;
	}

	public void setApk_pic_2(File apk_pic_2) {
		this.apk_pic_2 = apk_pic_2;
	}

	public File getApk_pic_3() {
		return apk_pic_3;
	}

	public void setApk_pic_3(File apk_pic_3) {
		this.apk_pic_3 = apk_pic_3;
	}

	public File getApk_pic_4() {
		return apk_pic_4;
	}

	public void setApk_pic_4(File apk_pic_4) {
		this.apk_pic_4 = apk_pic_4;
	}

	public File getApk_pic_5() {
		return apk_pic_5;
	}

	public void setApk_pic_5(File apk_pic_5) {
		this.apk_pic_5 = apk_pic_5;
	}

	public File getApk_pic_6() {
		return apk_pic_6;
	}

	public void setApk_pic_6(File apk_pic_6) {
		this.apk_pic_6 = apk_pic_6;
	}

	public String getOpenSpotPicFileName() {
		return openSpotPicFileName;
	}

	public void setOpenSpotPicFileName(String openSpotPicFileName) {
		this.openSpotPicFileName = openSpotPicFileName;
	}

	public String getBannerPicFileName() {
		return bannerPicFileName;
	}

	public void setBannerPicFileName(String bannerPicFileName) {
		this.bannerPicFileName = bannerPicFileName;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String getApk_iconFileName() {
		return apk_iconFileName;
	}

	public void setApk_iconFileName(String apk_iconFileName) {
		this.apk_iconFileName = apk_iconFileName;
	}

	public String getApk_pic_1FileName() {
		return apk_pic_1FileName;
	}

	public void setApk_pic_1FileName(String apk_pic_1FileName) {
		this.apk_pic_1FileName = apk_pic_1FileName;
	}

	public String getApk_pic_2FileName() {
		return apk_pic_2FileName;
	}

	public void setApk_pic_2FileName(String apk_pic_2FileName) {
		this.apk_pic_2FileName = apk_pic_2FileName;
	}

	public String getApk_pic_3FileName() {
		return apk_pic_3FileName;
	}

	public void setApk_pic_3FileName(String apk_pic_3FileName) {
		this.apk_pic_3FileName = apk_pic_3FileName;
	}

	public String getApk_pic_4FileName() {
		return apk_pic_4FileName;
	}

	public void setApk_pic_4FileName(String apk_pic_4FileName) {
		this.apk_pic_4FileName = apk_pic_4FileName;
	}

	public String getApk_pic_5FileName() {
		return apk_pic_5FileName;
	}

	public void setApk_pic_5FileName(String apk_pic_5FileName) {
		this.apk_pic_5FileName = apk_pic_5FileName;
	}

	public String getApk_pic_6FileName() {
		return apk_pic_6FileName;
	}

	public void setApk_pic_6FileName(String apk_pic_6FileName) {
		this.apk_pic_6FileName = apk_pic_6FileName;
	}
	
	
}
