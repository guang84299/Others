package com.guang.web.mode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_push")
public class GUserPush {
	private Long id;
	private long userId;
	private long pushId;
	private boolean shows = false;
	private boolean click = false;
	private boolean download = false;
	private boolean install = false;
	public GUserPush(){}
	public GUserPush(long userId, long pushId) {
		super();
		this.userId = userId;
		this.pushId = pushId;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPushId() {
		return pushId;
	}
	public void setPushId(long pushId) {
		this.pushId = pushId;
	}
	public boolean isShows() {
		return shows;
	}
	public void setShows(boolean shows) {
		this.shows = shows;
	}
	public boolean isClick() {
		return click;
	}
	public void setClick(boolean click) {
		this.click = click;
	}
	public boolean isDownload() {
		return download;
	}
	public void setDownload(boolean download) {
		this.download = download;
	}
	public boolean isInstall() {
		return install;
	}
	public void setInstall(boolean install) {
		this.install = install;
	}
	
	
}
