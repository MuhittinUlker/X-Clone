package com.clonex.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {

	private String content;
	private LocalDateTime commentDateTime;
	private int likes;
	private List<User> listOfLikers;
	
	public Comment(String content) {
		super();
		this.content = content;
		this.commentDateTime=LocalDateTime.now();
		listOfLikers=new ArrayList<>();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCommentDateTime() {
		return commentDateTime;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<User> getListOfLikers() {
		return listOfLikers;
	}

	public void setListOfLikers(List<User> listOfLikers) {
		this.listOfLikers = listOfLikers;
	}
	
	
}
