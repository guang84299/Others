package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app")
public class GApp {
	private Long id;
	private Long userId;
	private String name;// Ӧ������
	private String packageName;// ����
	private String versionName;// �汾��
	private String sdkVersion;// ������sdk�汾

	public GApp() {

	}

	public GApp(Long userId, String name, String packageName,String versionName,String sdkVersion) {
		super();
		this.userId = userId;
		this.name = name;
		this.packageName = packageName;
		this.versionName = versionName;
		this.sdkVersion = sdkVersion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "packageName", length = 128)
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "versionName", length = 64)
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	@Column(name = "sdkVersion", length = 64)
	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

}
