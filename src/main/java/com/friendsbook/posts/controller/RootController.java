package com.friendsbook.posts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@Log
public class RootController {
	
	@GetMapping("/")
	public String wakeUp() {
		log.info("Wake up call recieved!!!");
		return "Yo, I Woke up!!!";
	}

}
