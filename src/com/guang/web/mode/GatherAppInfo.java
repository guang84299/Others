package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gather_appinfo")
public class GatherAppInfo {
	private long id;
	private String deviceId;	
	private String packageName;
	private String appName;
	private String className;
	private boolean inlay;
	private Date gdate;
	
	
	
	public GatherAppInfo() {
	}
	public GatherAppInfo(String deviceId, String packageName,
			String appName,String className, boolean inlay) {
		this.deviceId = deviceId;
		this.packageName = packageName;
		this.appName = appName;
		this.className = className;
		this.inlay = inlay;
		this.gdate = new Date();
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(length = 64)
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getGdate() {
		return gdate;
	}
	public void setGdate(Date gdate) {
		this.gdate = gdate;
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
	@Column(length = 128)
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isInlay() {
		return inlay;
	}
	public void setInlay(boolean inlay) {
		this.inlay = inlay;
	}
	@Override
	public String toString() {
		return "GatherAppInfo [id=" + id + ", deviceId=" + deviceId
				+ ", packageName=" + packageName + ", appName=" + appName
				+ ", className=" + className + ", inlay=" + inlay + ", gdate="
				+ gdate + "]";
	}
	
}
