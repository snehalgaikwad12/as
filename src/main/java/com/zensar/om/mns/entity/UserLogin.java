package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLogin {
	
	@Id
	String userName;
	String userPassword;
	String projectName;
	int userRole;
	
	
	
	public UserLogin() {
		super();
	}
	public UserLogin(String userName, int userRole) {
		super();
		this.userName = userName;
		this.userRole = userRole;
	}
	public UserLogin(String userName, String userPassword, int userRole) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public String toString() {
		return "UserLogin [userName=" + userName + ", userPassword=" + userPassword + ", projectName=" + projectName
				+ ", userRole=" + userRole + "]";
	}
	public UserLogin(String userName, int userRole, String projectName ) {
	super();
	this.userName = userName;
	
	this.projectName = projectName;
	this.userRole = userRole;
}

	

}
