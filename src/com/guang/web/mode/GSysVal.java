package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sysval")
public class GSysVal {

	private Long id;

	private int platfrom;// 0 青露 1有米 2广点通

	private boolean autoState = false;// 是否自动推送

	private int autoPushType = 2;// 1:消息 2:插屏

	private String title;// 消息标题

	private String message;// 消息内容

	private int adId;// 要推送的广告id

	private float waitTime = 1.0f;// 等待时间 分钟
	
	//主动请求push配置
	private Boolean requestPushState = false;
	private Float requestPushRand = 0.3f;
	
	//基本推送配置
	private Boolean relationPushState = false;//关联推送

	public GSysVal() {
	}

	public GSysVal(int platfrom, boolean autoState, int autoPushType,
			String title, String message, int adId, float waitTime) {
		super();
		this.platfrom = platfrom;
		this.autoState = autoState;
		this.autoPushType = autoPushType;
		this.title = title;
		this.message = message;
		this.adId = adId;
		this.waitTime = waitTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(int platfrom) {
		this.platfrom = platfrom;
	}

	public boolean isAutoState() {
		return autoState;
	}

	public void setAutoState(boolean autoState) {
		this.autoState = autoState;
	}

	public int getAutoPushType() {
		return autoPushType;
	}

	public void setAutoPushType(int autoPushType) {
		this.autoPushType = autoPushType;
	}

	@Column(name = "title", length = 128)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "message", length = 2048)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public float getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(float waitTime) {
		this.waitTime = waitTime;
	}

	public Boolean getRequestPushState() {
		return requestPushState;
	}

	public void setRequestPushState(Boolean requestPushState) {
		this.requestPushState = requestPushState;
	}

	public Float getRequestPushRand() {
		return requestPushRand;
	}

	public void setRequestPushRand(Float requestPushRand) {
		this.requestPushRand = requestPushRand;
	}

	public Boolean getRelationPushState() {
		return relationPushState;
	}

	public void setRelationPushState(Boolean relationPushState) {
		this.relationPushState = relationPushState;
	}

}
