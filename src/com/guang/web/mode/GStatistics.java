package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_statistics")
public class GStatistics {
	private Long id;
	private Integer type;//统计类型 
	private Long userId;	
	private Integer adPositionType;
	private Long offerId;
	private String packageName;
	private String appName;
	private Date uploadTime;
	
	public GStatistics(){}
	public GStatistics(Integer type, Long userId, Integer adPositionType,
			Long offerId, String packageName, String appName, Date uploadTime) {
		super();
		this.type = type;
		this.userId = userId;
		this.adPositionType = adPositionType;
		this.offerId = offerId;
		this.packageName = packageName;
		this.appName = appName;
		this.uploadTime = uploadTime;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getAdPositionType() {
		return adPositionType;
	}
	public void setAdPositionType(Integer adPositionType) {
		this.adPositionType = adPositionType;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	@Column(length = 64)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(length = 64)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	
}
