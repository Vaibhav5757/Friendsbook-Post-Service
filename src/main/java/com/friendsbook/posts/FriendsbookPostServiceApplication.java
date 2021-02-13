package com.friendsbook.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.friendsbook"})
public class FriendsbookPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsbookPostServiceApplication.class, args);
	}
}
