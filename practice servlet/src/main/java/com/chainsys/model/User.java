package com.chainsys.model;

public class User {
	int id;
	String name;
	String mailid;
	String password;
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
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mailid=" + mailid + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String name, String mailid, String password) {
		this.id = id;
		this.name = name;
		this.mailid = mailid;
		this.password = password;
	}
	public User( String name, String mailid, String password) {
	
		this.name = name;
		this.mailid = mailid;
		this.password = password;
	}
	public User() {
		
	}
	

}
