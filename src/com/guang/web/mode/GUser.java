package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user",
uniqueConstraints={@UniqueConstraint(columnNames = {"name","password"})})
//alter table user add constraint name UNIQUE(name,password);
//alter table user drop index name;
public class GUser {
	private long id;
	private String name;
	private String password;
	private Date createdDate = new Date();
	private Date updatedDate = new Date();
	private String onlineTime = "0";
	private String lastOnlineTime = "0";
	private boolean online;
	// �豸���
	private String deviceId;// imei
	private String phoneNumber;// �ֻ�����
	private String networkOperatorName;// ��Ӫ������
	private String simSerialNumber;// sim�����к�
	private String networkCountryIso;// sim�����ڹ���
	private String networkOperator;// ��Ӫ�̱��
	private String networkType;// ��������
	private String location;// �ƶ��ն˵�λ��
	/**
	 * �ƶ��ն˵����� PHONE_TYPE_CDMA �ֻ���ʽΪCDMA������ 2 PHONE_TYPE_GSM �ֻ���ʽΪGSM���ƶ�����ͨ 1
	 * PHONE_TYPE_NONE �ֻ���ʽδ֪ 0
	 */
	private int phoneType;//
	private String model;// �ֻ��ͺ�
	private String release;// ϵͳ�汾
	private String province;// ʡ��
	private String city;// ����
	private String district;// ����
	private String street;// �ֵ�
	
	private Date spotDate;//����ʱ��
	private String spotAdId;//�������id
	private Date openSpotDate;//����ʱ��
	private String openSpotAdId;//�������id
	private Date pushDate;//pushʱ��
	private String pushAdId;//push���id
	
	
	public GUser() {
	}

	public GUser(String name, String password, String deviceId,
			String phoneNumber, String networkOperatorName,
			String simSerialNumber, String networkCountryIso,
			String networkOperator, String networkType, String location,
			int phoneType, String model, String release) {
		super();
		this.name = name;
		this.password = password;
		this.deviceId = deviceId;
		this.phoneNumber = phoneNumber;
		this.networkOperatorName = networkOperatorName;
		this.simSerialNumber = simSerialNumber;
		this.networkCountryIso = networkCountryIso;
		this.networkOperator = networkOperator;
		this.networkType = networkType;
		this.location = location;
		this.phoneType = phoneType;
		this.model = model;
		this.release = release;

		this.updatedDate = new Date();
		this.online = false;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "created_date", updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(length = 64)
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	@Column(name = "lastOnlineTime", length = 64)
	public String getLastOnlineTime() {
		return lastOnlineTime;
	}

	public void setLastOnlineTime(String lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}

	@Transient
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Column(name = "deviceId", length = 64)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "phoneNumber", length = 64)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "networkOperatorName", length = 64)
	public String getNetworkOperatorName() {
		return networkOperatorName;
	}

	public void setNetworkOperatorName(String networkOperatorName) {
		this.networkOperatorName = networkOperatorName;
	}

	@Column(name = "simSerialNumber", length = 64)
	public String getSimSerialNumber() {
		return simSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}

	@Column(name = "networkCountryIso", length = 64)
	public String getNetworkCountryIso() {
		return networkCountryIso;
	}

	public void setNetworkCountryIso(String networkCountryIso) {
		this.networkCountryIso = networkCountryIso;
	}

	@Column(name = "networkOperator", length = 64)
	public String getNetworkOperator() {
		return networkOperator;
	}

	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}

	@Column(name = "networkType", length = 12)
	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	@Column(name = "location", length = 64)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}

	@Column(name = "model", length = 64)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "sys_release", length = 64)
	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	@Column(name = "province", length = 32)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 32)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "district", length = 32)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "street", length = 64)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}
	@Column(length = 128)
	public String getSpotAdId() {
		return spotAdId;
	}

	public void setSpotAdId(String spotAdId) {
		this.spotAdId = spotAdId;
	}

	public Date getOpenSpotDate() {
		return openSpotDate;
	}

	public void setOpenSpotDate(Date openSpotDate) {
		this.openSpotDate = openSpotDate;
	}
	@Column(length = 128)
	public String getOpenSpotAdId() {
		return openSpotAdId;
	}

	public void setOpenSpotAdId(String openSpotAdId) {
		this.openSpotAdId = openSpotAdId;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}
	@Column(length = 128)
	public String getPushAdId() {
		return pushAdId;
	}

	public void setPushAdId(String pushAdId) {
		this.pushAdId = pushAdId;
	}

	@Override
	public String toString() {
		return "GUser [id=" + id + ", name=" + name + ", password=" + password
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", onlineTime=" + onlineTime
				+ ", lastOnlineTime=" + lastOnlineTime + ", online=" + online
				+ ", deviceId=" + deviceId + ", phoneNumber=" + phoneNumber
				+ ", networkOperatorName=" + networkOperatorName
				+ ", simSerialNumber=" + simSerialNumber
				+ ", networkCountryIso=" + networkCountryIso
				+ ", networkOperator=" + networkOperator + ", networkType="
				+ networkType + ", location=" + location + ", phoneType="
				+ phoneType + ", model=" + model + ", release=" + release
				+ ", province=" + province + ", city=" + city + ", district="
				+ district + ", street=" + street + "]";
	}
	
	
}
