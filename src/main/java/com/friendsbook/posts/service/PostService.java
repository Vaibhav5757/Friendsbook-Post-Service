package com.friendsbook.posts.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.friendsbook.posts.PostRepository;
import com.friendsbook.posts.model.Posts;

@Service
@Log
public class PostService {
	
	@Autowired
	private PostRepository postRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<String> post(String author, byte[] file, String text) throws IOException {
		Posts post = new Posts(author);
		post.setImage(compressBytes(file));
		post.setPostText(text);
		post.setCreated(new Date());

		try{
			this.postRepo.save(post);
			return new ResponseEntity<String>("Post created Successfully", HttpStatus.OK);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	public List<Posts> getAllPosts(String author) {
		Posts[] temp = this.postRepo.findAllByOwner(author);
		List<Posts> result = new ArrayList<Posts>();
		for(int itr = 0; itr < temp.length; ++itr) {
			Posts temporary = new Posts(temp[itr].getOwner());
			temporary.setId(temp[itr].getId());
			temporary.setPostText(temp[itr].getPostText());
			temporary.setImage(decompressBytes(temp[itr].getImage()));
			temporary.setCreated(temp[itr].getCreated());
			result.add(temporary);
		}
		return result;
	}


	public byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		try {
			outputStream.close();
		} catch (IOException e) {}

		return outputStream.toByteArray();
	}
	
	public byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (DataFormatException e) {
			logger.error(e.getMessage());
		}
		return outputStream.toByteArray();

	}
}
