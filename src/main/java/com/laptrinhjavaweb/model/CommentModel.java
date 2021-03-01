package com.laptrinhjavaweb.model;

import java.awt.TextArea;

public class CommentModel extends AbstractModel{
	private TextArea content;
	private Long userId;
	private Long newId;
	
	
	public TextArea getContent() {
		return content;
	}
	public void setContent(TextArea content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	
	
}
