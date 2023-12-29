package com.clonex.classes;

public class User{

	private String username;
	private String password;
	private String phoneNumber;
	private Profile profile;
	
	public User() {
		super();
	}

	public User (String username,String password,String phoneNumber) {
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.profile = new Profile();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "\u001B[34m"+username+"-->\u001B[0m" + profile;
	}
	
}
