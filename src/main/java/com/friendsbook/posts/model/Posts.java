package com.friendsbook.posts.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class Posts {
	
	@Id
	private String id;
	@Field
	private String owner, postText;
	@Field
	private byte[] image;
	@Field
	private Date created;
	
	public Posts(String owner){
		this.owner = owner;
	}
}
