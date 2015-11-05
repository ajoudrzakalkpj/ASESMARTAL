package com.ajou.ase.user;

public class User {

	private int userNumSeq;
	private String userID;
	private String userPassword;
	private String userName;
	private String userPhoneNumber;
	private String userBirthDate;
	private String userServerAdmin;
	private String userPrivilege;
	private String userRegdate;
	private int userConfirmed;
	private String userConfirmedTime;
	private String userLastSSID;
	


	// 생성자
	public User() {
	}



	public int getUserNumSeq() {
		return userNumSeq;
	}



	public void setUserNumSeq(int userNumSeq) {
		this.userNumSeq = userNumSeq;
	}



	public String getUserID() {
		return userID;
	}



	public void setUserID(String userID) {
		this.userID = userID;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}



	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}



	public String getUserBirthDate() {
		return userBirthDate;
	}



	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}



	public String getUserServerAdmin() {
		return userServerAdmin;
	}



	public void setUserServerAdmin(String userServerAdmin) {
		this.userServerAdmin = userServerAdmin;
	}







	public String getUserRegdate() {
		return userRegdate;
	}



	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}



	public int getUserConfirmed() {
		return userConfirmed;
	}



	public void setUserConfirmed(int userConfirmed) {
		this.userConfirmed = userConfirmed;
	}



	public String getUserConfirmedTime() {
		return userConfirmedTime;
	}



	public void setUserConfirmedTime(String userConfirmedTime) {
		this.userConfirmedTime = userConfirmedTime;
	}



	public String getUserLastSSID() {
		return userLastSSID;
	}



	public void setUserLastSSID(String userLastSSID) {
		this.userLastSSID = userLastSSID;
	}



	public String getUserPrivilege() {
		return userPrivilege;
	}



	public void setUserPrivilege(String userPrivilege) {
		this.userPrivilege = userPrivilege;
	}

}
