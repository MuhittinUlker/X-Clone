package com.clonex.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.clonex.enums.EHashTag;
import com.clonex.utilities.Utilities;

public class Tweet {

	Utilities util = new Utilities();
	private Profile profile;
	private int id;
	private String content;
	private LocalDateTime tweetTime;
	private int likes;
	private int comments;
	private List<Comment> listOfComments;
	private List<User> listOfLikers;
	private List<User> listOfRetweeters;
	private List<EHashTag> hashTags;
	
	public Tweet (Tweet tweet) {
		this.profile=tweet.getProfile();
		this.content=tweet.getContent();
		this.tweetTime=LocalDateTime.now();
		this.id = util.randomIntGenerator();
		listOfLikers = new ArrayList<>();
		listOfRetweeters = new ArrayList<>();
		listOfComments = new ArrayList<>();
		hashTags = new ArrayList<>();
	}
	public Tweet(String content) {
		this.content=content;
		this.tweetTime=LocalDateTime.now();
		this.id = util.randomIntGenerator();
		listOfLikers = new ArrayList<>();
		listOfRetweeters = new ArrayList<>();
		listOfComments = new ArrayList<>();
		hashTags = new ArrayList<>();
	}

	public List<EHashTag> getHashTags() {
		return hashTags;
	}
	
	public void setHashTags(List<EHashTag> hashTags) {
		this.hashTags = hashTags;
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTweetTime() {
		return tweetTime;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public List<User> getListOfLikers() {
		return listOfLikers;
	}

	public void setListOfLikers(List<User> listOfLikers) {
		this.listOfLikers = listOfLikers;
	}

	public List<User> getListOfRetweeters() {
		return listOfRetweeters;
	}

	public void setListOfRetweeters(List<User> listOfRetweeters) {
		this.listOfRetweeters = listOfRetweeters;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}
	
}
