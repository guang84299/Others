package com.guang.web.mode;

import java.util.Date;

public class GSessionUser {
	private long sessionId;
	private long id;
	private String name;
	private boolean online;
	private String ip;
	private Date createDate;
	public GSessionUser(){}
	public GSessionUser(long sessionId,long id, String name, boolean online, String ip,
			Date createDate) {
		super();
		this.sessionId = sessionId;
		this.id = id;
		this.name = name;
		this.online = online;
		this.ip = ip;
		this.createDate = createDate;
	}
	
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
