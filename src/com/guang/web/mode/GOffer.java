package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class GOffer {
	private Long id;
	private String name;
	private String openSpotPicPath;//开屏图片
	private String bannerPicPath;
	private String apkDownloadPath;//下载路径
	private String apkPackageName;//APK包名
	
	private String apk_icon_path;
	private String apk_pic_path_1;
	private String apk_pic_path_2;
	private String apk_pic_path_3;
	private String apk_pic_path_4;
	private String apk_pic_path_5;
	private String apk_pic_path_6;
	private String apk_developer;//开发者
	private String apk_describe;//描述
	private Float apk_size; //apk 大小
	private String apk_summary;//一句话介绍
	private Integer apk_downloads;
	private String apk_version;
	private String apk_updatedDate;
	
	public GOffer(){}
	public GOffer(String name, String openSpotPicPath, String bannerPicPath,
			String apkDownloadPath, String apkPackageName,
			String apk_icon_path, String apk_pic_path_1, String apk_pic_path_2,
			String apk_pic_path_3, String apk_pic_path_4,
			String apk_pic_path_5, String apk_pic_path_6, String apk_developer,
			String apk_describe, Float apk_size, String apk_summary,
			Integer apk_downloads, String apk_version, String apk_updatedDate) {
		super();
		this.name = name;
		this.openSpotPicPath = openSpotPicPath;
		this.bannerPicPath = bannerPicPath;
		this.apkDownloadPath = apkDownloadPath;
		this.apkPackageName = apkPackageName;
		this.apk_icon_path = apk_icon_path;
		this.apk_pic_path_1 = apk_pic_path_1;
		this.apk_pic_path_2 = apk_pic_path_2;
		this.apk_pic_path_3 = apk_pic_path_3;
		this.apk_pic_path_4 = apk_pic_path_4;
		this.apk_pic_path_5 = apk_pic_path_5;
		this.apk_pic_path_6 = apk_pic_path_6;
		this.apk_developer = apk_developer;
		this.apk_describe = apk_describe;
		this.apk_size = apk_size;
		this.apk_summary = apk_summary;
		this.apk_downloads = apk_downloads;
		this.apk_version = apk_version;
		this.apk_updatedDate = apk_updatedDate;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length = 64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 128)
	public String getOpenSpotPicPath() {
		return openSpotPicPath;
	}
	public void setOpenSpotPicPath(String openSpotPicPath) {
		this.openSpotPicPath = openSpotPicPath;
	}
	@Column(length = 128)
	public String getBannerPicPath() {
		return bannerPicPath;
	}
	public void setBannerPicPath(String bannerPicPath) {
		this.bannerPicPath = bannerPicPath;
	}
	@Column(length = 128)
	public String getApkDownloadPath() {
		return apkDownloadPath;
	}
	public void setApkDownloadPath(String apkDownloadPath) {
		this.apkDownloadPath = apkDownloadPath;
	}
	@Column(length = 128)
	public String getApkPackageName() {
		return apkPackageName;
	}
	public void setApkPackageName(String apkPackageName) {
		this.apkPackageName = apkPackageName;
	}
	@Column(length = 128)
	public String getApk_icon_path() {
		return apk_icon_path;
	}
	public void setApk_icon_path(String apk_icon_path) {
		this.apk_icon_path = apk_icon_path;
	}
	@Column(length = 128)
	public String getApk_pic_path_1() {
		return apk_pic_path_1;
	}
	public void setApk_pic_path_1(String apk_pic_path_1) {
		this.apk_pic_path_1 = apk_pic_path_1;
	}
	@Column(length = 128)
	public String getApk_pic_path_2() {
		return apk_pic_path_2;
	}
	public void setApk_pic_path_2(String apk_pic_path_2) {
		this.apk_pic_path_2 = apk_pic_path_2;
	}
	@Column(length = 128)
	public String getApk_pic_path_3() {
		return apk_pic_path_3;
	}
	public void setApk_pic_path_3(String apk_pic_path_3) {
		this.apk_pic_path_3 = apk_pic_path_3;
	}
	@Column(length = 128)
	public String getApk_pic_path_4() {
		return apk_pic_path_4;
	}
	public void setApk_pic_path_4(String apk_pic_path_4) {
		this.apk_pic_path_4 = apk_pic_path_4;
	}
	@Column(length = 128)
	public String getApk_pic_path_5() {
		return apk_pic_path_5;
	}
	public void setApk_pic_path_5(String apk_pic_path_5) {
		this.apk_pic_path_5 = apk_pic_path_5;
	}
	@Column(length = 128)
	public String getApk_pic_path_6() {
		return apk_pic_path_6;
	}
	public void setApk_pic_path_6(String apk_pic_path_6) {
		this.apk_pic_path_6 = apk_pic_path_6;
	}
	@Column(length = 64)
	public String getApk_developer() {
		return apk_developer;
	}
	public void setApk_developer(String apk_developer) {
		this.apk_developer = apk_developer;
	}
	@Column(length = 512)
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
	@Column(length = 128)
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
	@Column(length = 64)
	public String getApk_version() {
		return apk_version;
	}
	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}
	@Column(length = 64)
	public String getApk_updatedDate() {
		return apk_updatedDate;
	}
	public void setApk_updatedDate(String apk_updatedDate) {
		this.apk_updatedDate = apk_updatedDate;
	}
	
	
}
