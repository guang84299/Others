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
	
	 
	private String company;//��˾
	
	private int type;//�������
	
	
	private String picPath;//ͼƬ·�� ����
	private String picHorizontalPath;//����
	private String picNotifyPath;//֪ͨͼƬ
	
	
	private String downloadPath;//����·��
	
	
	private String packageName;
	
	private int showNum;//չʾ����
	
	private int clickNum;//�������
	
	private int downloadNum;//���ش���
	
	private int installNum;//��װ����
	
	private Integer showLevel = 0;//�����ʾ����
	
	

	public GAd()
	{
		
	}
	
	public GAd(String company, int type,String packageName, String picPath,String picHorizontalPath,
			String picNotifyPath,String downloadPath) {
		super();
		this.company = company;
		this.type = type;
		this.packageName = packageName;
		this.picPath = picPath;
		this.picHorizontalPath = picHorizontalPath;
		this.picNotifyPath = picNotifyPath;
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
	@Column(length = 64) 
	public String getPicHorizontalPath() {
		return picHorizontalPath;
	}

	public void setPicHorizontalPath(String picHorizontalPath) {
		this.picHorizontalPath = picHorizontalPath;
	}
	@Column(length = 64) 
	public String getPicNotifyPath() {
		return picNotifyPath;
	}

	public void setPicNotifyPath(String picNotifyPath) {
		this.picNotifyPath = picNotifyPath;
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
	
	public Integer getShowLevel() {
		return showLevel;
	}

	public void setShowLevel(Integer showLevel) {
		this.showLevel = showLevel;
	}
	
}
