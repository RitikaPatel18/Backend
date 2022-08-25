package com.project.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired private UserRepository repository;
	
	@Autowired
	authUtils auth;
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public ResponseEntity<Map<String, Object>> register(User user) {
		Map<String, Object> response = new HashMap<>();
		User userData = repository.findByEmail(user.getEmail());
		if (userData == null) {
			User savedUser = repository.save(user);
			response.put("user", savedUser);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		response.put("error", "User with this email already exists!!");
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Map<String, Object>> login(User user) {
		Map<String, Object> response = new HashMap<>();

		User userData = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (userData == null) {
			response.put("error", "Please enter correct Credentials!!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.put("user", userData);
		//System.out.println("..............................."+auth.generateJwtToken(userData));
		response.put("token", auth.generateJwtToken(userData));
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
//	public Response getAllUsers(){
//		 List<User> users=repository.findAll();
//		 return new Response("Users="+users.size(),Boolean.TRUE);
//	}
//	
//	public Response login(User user) {
//		Map<String, Object> response = new HashMap<>();
//
//		User userData = repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//		if (userData == null) {
//			response.put("error", "Please enter correct Credentials!!");
//			ResponseEntity<Map<String, Object>> res= new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			return new Response(user.getId()+" not inserted",Boolean.FALSE);
//		}
//		response.put("user", userData);
//		ResponseEntity<Map<String, Object>> res1= new ResponseEntity<>(response,HttpStatus.OK);
//		return new Response(user.getId()+" inserted",Boolean.TRUE);
//	}
//	
//	
//	
//	public Response register(User user) {
//		Map<String, Object> response = new HashMap<>();
//		User userData = repository.findByEmail(user.getEmail());
//		if (userData == null) {
//			User savedUser = repository.save(user);
//			response.put("user", savedUser);
//			return new Response(user.getId()+" registered",Boolean.TRUE);
//		}
//		response.put("error", "User with this email already exists!!");
//		return new Response(user.getId()+" not Registered",Boolean.FALSE);
//	}
	
}
