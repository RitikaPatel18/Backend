package com.project.backend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired private UserService userService;
	
	
	
	@GetMapping
	public List<User> getAllUsers(){
		List<User> list=userService.getAllUsers();
		return list ;
	}
	
	
//	@GetMapping
//	public Response getAllUsers(){
//		userService.getAllUsers();
//		return new Response("Users showed",Boolean.TRUE);
//	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		return userService.login(user);
	}
//	
//	@PostMapping("/login")
//	public Response login(@RequestBody User user){
//		 userService.login(user);
//		 return new Response(user.getId()+" logged in",Boolean.TRUE);
//	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody User user){
		return userService.register(user);
	}
	

//	@PostMapping("/register")
//	public Response register(@RequestBody User user){
//		 userService.register(user);
//		 return new Response(user.getId()+" Registered",Boolean.TRUE);
//	}
	
}