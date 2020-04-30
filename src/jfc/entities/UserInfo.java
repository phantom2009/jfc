package jfc.entities;

import java.io.InputStream;

public class UserInfo implements Cloneable{
	
	private int id;                                //数据库类型：int,自增
	private String name;                           //数据库类型：varchar(255)
	private String password;                       //数据库varchar,自动对于
	private String realName;
	private java.util.Date registerDate;           //可以当做入职日期,数据库类型timestamp,注意这里的类型是java.util.date
	
	private java.util.Date birthDay;               //同上，java.util.date,带时间部分
	
	private InputStream picture;                   //如果数据库对于的字段是blob,那么只能存储65535字节数据，大概是64k，超过这个就报错，因此数据库对于的字段最好是longblob类型，longblob能达到4G
	
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
	
	//把这个方法重写一下就行，什么都不写
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
