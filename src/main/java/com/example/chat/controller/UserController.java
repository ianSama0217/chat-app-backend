package com.example.chat.controller;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat.entity.User;
import com.example.chat.jwt.JwtUtil;
import com.example.chat.security.UserDetailServiceImpl;
import com.example.chat.service.ifs.UserService;
import com.example.chat.vo.LoginReq;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
    private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody User user) throws Exception{
			User userRes = userService.register(user);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(userRes);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginReq req, Authentication authentication) throws Exception{
			userService.login(req);
			userDetailService.loadUserByUsername(req.getEmail());
			
			Map<String, Object> response = new HashMap<>();
			String token = jwtUtil.generateToken(req.getEmail());
			response.put("token", token);
			response.put("status", 200);
			response.put("message", "登入成功");
			return ResponseEntity.ok(response);
	}

	@GetMapping("user")
	public User findUser(@RequestParam(value = "id")Integer userId) throws Exception{
		return userService.findUser(userId);
	}
}
