package com.guang.web.mode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gather_appinfo")
public class GatherAppInfo {
	private long id;
	private long deviceId;
	private String gdate;
	private String pack;
	private String className;
	private boolean inlay;
	
	
	
	public GatherAppInfo() {
		super();
	}
	public GatherAppInfo(long id, long deviceId, String gdate, String pack,
			String className, boolean inlay) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.gdate = gdate;
		this.pack = pack;
		this.className = className;
		this.inlay = inlay;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public String getGdate() {
		return gdate;
	}
	public void setGdate(String gdate) {
		this.gdate = gdate;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
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
				+ ", gdate=" + gdate + ", pack=" + pack + ", className="
				+ className + ", inlay=" + inlay + "]";
	}
}
