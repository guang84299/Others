package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_whitelist")
public class GAdConfig {
	private Long id;
	private String name;//配置方案名称
	private Boolean open;//是否打开
	private String whiteList;//白名单
	private String timeSlot;//时间段 
	private Integer showNum;//每天广告展示次数
	private Float showTimeInterval;//广告时间间隔
	private String appSwitch;//媒体开关
	private String adPositionSwitch;//广告位开关
	
	public GAdConfig(){}

	public GAdConfig(String name, Boolean open, String whiteList,
			String timeSlot, Integer showNum, Float showTimeInterval,
			String appSwitch, String adPositionSwitch) {
		super();
		this.name = name;
		this.open = open;
		this.whiteList = whiteList;
		this.timeSlot = timeSlot;
		this.showNum = showNum;
		this.showTimeInterval = showTimeInterval;
		this.appSwitch = appSwitch;
		this.adPositionSwitch = adPositionSwitch;
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

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	@Column(length = 10240)
	public String getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}
	@Column(length = 512)
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Float getShowTimeInterval() {
		return showTimeInterval;
	}

	public void setShowTimeInterval(Float showTimeInterval) {
		this.showTimeInterval = showTimeInterval;
	}
	@Column(length = 512)
	public String getAppSwitch() {
		return appSwitch;
	}

	public void setAppSwitch(String appSwitch) {
		this.appSwitch = appSwitch;
	}
	@Column(length = 128)
	public String getAdPositionSwitch() {
		return adPositionSwitch;
	}

	public void setAdPositionSwitch(String adPositionSwitch) {
		this.adPositionSwitch = adPositionSwitch;
	}
	
	
}
