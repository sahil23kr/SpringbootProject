package com.online_shopping.DTO;

public class User {
	private String userName;
	private int age;
	private long mobileNo;
	private String emailId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public User(String userName, int age, long mobileNo, String emailId) {
		super();
		this.userName = userName;
		this.age = age;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
	}
	
	

}
