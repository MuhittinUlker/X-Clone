package com.clonex.utilities;

import java.util.ArrayList;
import java.util.List;

import com.clonex.CloneXRunner;
import com.clonex.classes.User;
import com.clonex.enums.EHashTag;

public class Database {

	public List<User> userList = new ArrayList<>();
	public EHashTag[] hashTags = {EHashTag.BABOOST,EHashTag.BILGEADAM,EHashTag.CLONEX,EHashTag.HELLO};
	
	public Database() {
		User admin = new User("admin", "admin123", "0123456789");
		userList.add(admin);
		User u1 = new User("u1", "admin123", "0123456789");
		userList.add(u1);
		User u2 = new User("u2", "admin123", "0123456789");
		userList.add(u2);
		User u3 = new User("u3", "admin123", "0123456789");
		userList.add(u3);
	}
	
	public void getUsers() {
		System.out.println("*****USERS*****");
		userList.stream().forEach(u->{
			if (!u.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println(u.getProfile().getId()+"\t"+u.getUsername()+"\t"+u.getProfile().getStatus());
			}
		});
	}
	
	public void getProfiles() {
		System.out.println("*****PROFILES*****");
		userList.stream().forEach(p->{
			if (!p.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println("ID: "+p.getProfile().getId()+" Username: "+p.getUsername()
				+"\nStatus: "+p.getProfile().getStatus()+" PhoneNumber: "+p.getPhoneNumber()
				+" Following: "+p.getProfile().getFollowing()+" Followers: "+p.getProfile().getFollowers());
			}
		});
	}
	
	public void getProfilesWithTweets() {
		CloneXRunner.database.userList.forEach(u->{
			if (!u.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println("\u001B[33mProfile ID: \u001B[0m"+ u.getProfile().getId());
				u.getProfile().getTweetsAsList();
			}
		});
	}
}
