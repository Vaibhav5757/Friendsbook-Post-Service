package com.friendsbook.posts.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.friendsbook.posts.model.Posts;
import com.friendsbook.posts.service.PostService;

@RestController
@Log
public class PostController {
	
	@Autowired
	private PostService postSvc;
	
	@PostMapping(value="/post", consumes = MediaType.MULTIPART_FORM_DATA)
	public ResponseEntity<String> post(@RequestParam("owner") String author, @RequestParam("file") byte[] file, @RequestParam("text") String text) throws IOException {
		return this.postSvc.post(author, file, text);
	}
	
	@PostMapping(value="/getPosts")
	@CrossOrigin
	public List<Posts> getAllPosts(@RequestParam("owner") String author) {
		log.info("here");
		return this.postSvc.getAllPosts(author);
	}

}
