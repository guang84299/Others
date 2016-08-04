package com.guang.web.mode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gather_runinfo")
public class GatherAppRunInfo {
	private long id;
	private long deviceId;
	private String gdate;
	private String pack;
	private String className;
	private boolean inlay;
	private String startTime;
	private String useTime;
	private boolean isWifi;
	
	
	public GatherAppRunInfo() {
		super();
	}


	public GatherAppRunInfo(long id, long deviceId, String gdate, String pack,
			String className, boolean inlay, String startTime, String useTime,
			boolean isWifi) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.gdate = gdate;
		this.pack = pack;
		this.className = className;
		this.inlay = inlay;
		this.startTime = startTime;
		this.useTime = useTime;
		this.isWifi = isWifi;
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


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getUseTime() {
		return useTime;
	}


	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}


	public boolean isWifi() {
		return isWifi;
	}


	public void setWifi(boolean isWifi) {
		this.isWifi = isWifi;
	}


	@Override
	public String toString() {
		return "GatherAppRunInfo [id=" + id + ", deviceId=" + deviceId
				+ ", gdate=" + gdate + ", pack=" + pack + ", className="
				+ className + ", inlay=" + inlay + ", startTime=" + startTime
				+ ", useTime=" + useTime + ", isWifi=" + isWifi + "]";
	}
	
	
}
