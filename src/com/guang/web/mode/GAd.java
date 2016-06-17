package com.guang.web.mode;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad")
public class GAd {
	
	private Long id;
	
	 
	private String company;//公司
	
	private int type;//广告类型
	
	
	private String picPath;//图片路径
	
	
	private String downloadPath;//下载路径
	
	
	private String packageName;
	
	private int showNum;//展示次数
	
	private int clickNum;//点击次数
	
	private int downloadNum;//下载次数
	
	private int installNum;//安装次数
	
	public GAd()
	{
		
	}
	
	public GAd(String company, int type,String packageName, String picPath,String downloadPath) {
		super();
		this.company = company;
		this.type = type;
		this.packageName = packageName;
		this.picPath = picPath;
		this.downloadPath = downloadPath;
		this.showNum = 0;
		this.clickNum = 0;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "company",  length = 64)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name = "picPath",  length = 64) 
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(name = "downloadPath",  length = 128) 
	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	public int getInstallNum() {
		return installNum;
	}

	public void setInstallNum(int installNum) {
		this.installNum = installNum;
	}

	@Column(name = "packageName",  length = 64) 
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	
}
