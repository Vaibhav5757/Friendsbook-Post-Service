package com.friendsbook.posts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.friendsbook.posts.model.Posts;

@Repository
public interface PostRepository extends CrudRepository<Posts, Integer> {

	Posts[] findAllByOwner(String author);

}
