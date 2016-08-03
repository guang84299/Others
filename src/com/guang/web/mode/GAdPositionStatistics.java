package com.guang.web.mode;

public class GAdPositionStatistics {
	private int adPositionType;
	private String adPositionName;
	private long requestNum;
	private long showNum;	
	private long clickNum;
	private long downloadNum;
	private long downloadSuccessNum;
	private long installNum;
	private long installSuccessNum;
	private long activateNum;
	private float income;//收入
	
	public GAdPositionStatistics(){}
	public GAdPositionStatistics(int adPositionType, String adPositionName,
			long requestNum, long showNum, long clickNum, long downloadNum,
			long downloadSuccessNum, long installNum, long installSuccessNum,
			long activateNum, float income) {
		super();
		this.adPositionType = adPositionType;
		this.adPositionName = adPositionName;
		this.requestNum = requestNum;
		this.showNum = showNum;
		this.clickNum = clickNum;
		this.downloadNum = downloadNum;
		this.downloadSuccessNum = downloadSuccessNum;
		this.installNum = installNum;
		this.installSuccessNum = installSuccessNum;
		this.activateNum = activateNum;
		this.income = income;
	}
	public int getAdPositionType() {
		return adPositionType;
	}
	public void setAdPositionType(int adPositionType) {
		this.adPositionType = adPositionType;
	}
	public String getAdPositionName() {
		return adPositionName;
	}
	public void setAdPositionName(String adPositionName) {
		this.adPositionName = adPositionName;
	}
	public long getRequestNum() {
		return requestNum;
	}
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
	}
	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}
	public long getClickNum() {
		return clickNum;
	}
	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}
	public long getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(long downloadNum) {
		this.downloadNum = downloadNum;
	}
	public long getDownloadSuccessNum() {
		return downloadSuccessNum;
	}
	public void setDownloadSuccessNum(long downloadSuccessNum) {
		this.downloadSuccessNum = downloadSuccessNum;
	}
	public long getInstallNum() {
		return installNum;
	}
	public void setInstallNum(long installNum) {
		this.installNum = installNum;
	}
	public long getInstallSuccessNum() {
		return installSuccessNum;
	}
	public void setInstallSuccessNum(long installSuccessNum) {
		this.installSuccessNum = installSuccessNum;
	}
	public long getActivateNum() {
		return activateNum;
	}
	public void setActivateNum(long activateNum) {
		this.activateNum = activateNum;
	}
	public float getIncome() {
		return income;
	}
	public void setIncome(float income) {
		this.income = income;
	}
	
	
}
