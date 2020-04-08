package jfc.entities;

import java.io.InputStream;

public class UserInfo {
	
	private int id;                                //���ݿ����ͣ�int,����
	private String name;                           //���ݿ����ͣ�varchar(255)
	private String password;                       //���ݿ�varchar,�Զ�����
	private String realName;
	private java.util.Date registerDate;           //���Ե�����ְ����,���ݿ�����timestamp,ע�������������java.util.date
	
	private java.util.Date birthDay;               //ͬ�ϣ�java.util.date,��ʱ�䲿��
	
	private InputStream picture;                   //������ݿ���ڵ��ֶ���blob,��ôֻ�ܴ洢65535�ֽ����ݣ������64k����������ͱ���������ݿ���ڵ��ֶ������longblob���ͣ�longblob�ܴﵽ4G
	
	public UserInfo() {
		super();
	}

	public UserInfo(String name, String password, java.util.Date registerDate,
			java.util.Date birthDay) {
		super();
		this.name = name;
		this.password = password;
		this.registerDate = registerDate;
		this.birthDay = birthDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public java.util.Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(java.util.Date registerDate) {
		this.registerDate = registerDate;
	}
	public java.util.Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(java.util.Date birthDay) {
		this.birthDay = birthDay;
	}
	public InputStream getPicture() {
		return picture;
	}

	public void setPicture(InputStream picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", password=" + password + ", realName=" + realName
				+ ", registerDate=" + registerDate + ", birthDay=" + birthDay + ", picture=" + picture + "]";
	}
}
