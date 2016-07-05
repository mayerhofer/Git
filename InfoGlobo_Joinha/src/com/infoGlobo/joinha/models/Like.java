package com.infoGlobo.joinha.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Like {
	public Like()
	{
		
	}
	
	public Like(int postId, int likes, int dislikes)
	{
		this.postId = postId;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	//private int userId; -- each user can like any number of times
	private int postId;
	private int likes;
	private int dislikes;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes)
	{
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes)
	{
		this.dislikes = dislikes;
	}
	public void incLike() {
		this.likes++;
	}
	public void incDislike() {
		this.dislikes++;
	}
}
