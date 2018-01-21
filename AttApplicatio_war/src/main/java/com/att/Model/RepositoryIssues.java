package com.att.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class RepositoryIssues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private Long number;
	private String comments_url;
	private int totalComments;
	private List<Long> commentId;
	
	public List<Long> getCommentId() {
		return commentId;
	}
	public void setCommentId(List<Long> commentId) {
		this.commentId = commentId;
	}
	public int getTotalComments() {
		return totalComments;
	}
	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getComments_url() {
		return comments_url;
	}
	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
	
}
