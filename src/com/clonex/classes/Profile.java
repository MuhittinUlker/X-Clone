package com.clonex.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.clonex.enums.EStatus;
import com.clonex.utilities.Utilities;

public class Profile{

	Utilities util = new Utilities();
	
	private int tweets;
	private int id;
	private String photo;
	private String bio;
	private LocalDate registerDate;
	private Address address;
	private int followers;
	private int following;
	private Map<Integer, Tweet> tweetList;
	private List<Message> messages;
	private Map<Integer,Message> inbox;
	private EStatus status;
	private Map<Integer, User> followingList;
	private Map<Integer, User> followerList;
	private Map<Integer,User> blockedList;
	
	public Profile() {
		this.registerDate = LocalDate.now();
		this.address = new Address();
		this.id = util.randomIntGenerator();
		this.status = EStatus.ACTIVE;
		this.photo = UUID.randomUUID().toString();
		tweetList = new HashMap<>();
		messages = new ArrayList<>();
		inbox = new HashMap<>();
		followerList = new HashMap<>();
		followingList = new HashMap<>();
		blockedList = new HashMap<>();
	}
	
	public Map<Integer, User> getBlockedList() {
		return blockedList;
	}

	public Map<Integer, User> getFollowingList() {
		return followingList;
	}

	public Map<Integer, User> getFollowerList() {
		return followerList;
	}

	public int getTweets() {
		return tweets;
	}
	
	public void setTweets(int tweets) {
		this.tweets = tweets;
	}
	
	public String getPhoto() {
		String[] newPhoto = photo.split("-");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < newPhoto.length; i++) {
			sb.append("\n"+newPhoto[i]);
		}
		return sb.toString();
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(String street, String city, String country, String postalCode) {
		this.address.setStreet(street);
		this.address.setCity(city);
		this.address.setCountry(country);
		this.address.setPostalCode(postalCode);
	}
	
	public int getFollowers() {
		return followers;
	}
	
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	
	public int getFollowing() {
		return following;
	}
	
	public void setFollowing(int following) {
		this.following = following;
	}
	
	public Map<Integer, Tweet> getTweetList() {
		return tweetList;
	}

	public void getTweetsAsList() {
		System.out.println("\u001B[34m*****TWEETS*****\u001B[0m");
		if (tweetList.size()==0) {
			System.out.println("Hiç yayınlanan tweet'iniz bulunmamaktadır.");
		}
		tweetList.forEach((k,v)->{
			System.out.println("\u001B[33m"+(k+1)+"-ID: \u001B[0m"+v.getId());
			System.out.println("\u001B[35m"+v.getContent()+"\u001B[0m");
			v.getHashTags().stream().forEach(t->{
				System.out.print("\u001B[35m#"+t.name());
			});
			System.out.println();
			System.out.print("\u001B[33m(Y)\u001B[0m"+v.getLikes()+"\u001B[33m (C)\u001B[0m"+v.getComments());
			System.out.println("\t\t\t"+v.getTweetTime().getDayOfMonth()+"."
					+v.getTweetTime().getMonthValue()+"."+v.getTweetTime().getYear()+" "
					+v.getTweetTime().getHour()+":"+v.getTweetTime().getMinute());
			System.out.println();
			});	
	}
	
	public EStatus getStatus() {
		return status;
	}
	
	public void setStatus(EStatus status) {
		this.status = status;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public int getId() {
		return id;
	}

	public Map<Integer, Message> getInbox() {
		return inbox;
	}

	@Override
	public String toString() {
		return tweets + "\u001B[33m Tweets\nPhoto=\u001B[31m" + this.getPhoto() + "\u001B[33m\nBio=\u001B[0m" + bio + "\u001B[33m\nRegisteration Date=\u001B[0m" 
	+ registerDate+ "\u001B[33m\nAdress=\u001B[0m" + address + "\u001B[33m\nFollowers=\u001B[0m" + followers + "\u001B[33m\nFollowing=\u001B[0m" + following;
	}

}
