package com.example.chat.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chat.entity.User;
import com.example.chat.repository.UserDao;
import com.example.chat.service.ifs.UserService;
import com.example.chat.vo.LoginReq;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public User register(User user) throws Exception {
		// 檢查參數
		if(user == null || user.getEmail().isEmpty() || user.getPassword().isEmpty()
				|| user.getName().isEmpty()) {
			throw new Exception("參數為空");
		}
		
		if(userDao.existsByEmail(user.getEmail())) {
			throw new Exception("信箱已註冊");
		}
		
		String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
		
		if(!Pattern.matches(passwordPattern, user.getPassword())) {
			throw new Exception("密碼格式不符");
		}
		
		// 設定 user 資料
		user.setPassword(encoder.encode(user.getPassword()));
		user.setSignupDate(LocalDateTime.now());
		
		try {
			User res = userDao.save(user);
			userDao.setUserRole(res.getId());
		}catch (Exception e) {
			throw new Exception("註冊失敗" + e.getMessage());
		}
		
		return user;
	}

	@Override
	public void login(LoginReq req) throws Exception {
		// 檢查參數
		if(req == null || req.getEmail().isEmpty() || req.getPassword().isEmpty()) {
			throw new Exception("參數為空");
		}
		
	    User user = userDao.findByEmail(req.getEmail());
	    
	    if(user == null) {
	    	throw new Exception("帳號不存在");
	    }
	    
	    if(!encoder.matches(req.getPassword(), user.getPassword())) {
	    	throw new Exception("密碼錯誤");
	    }
	}

	@Override
	public User findUser(Integer userId) throws Exception {
		Optional<User> userOp = userDao.findById(userId);
		
		if(userOp.isEmpty()) {
			throw new Exception("使用者不存在");
		}
		
		// 清除密碼
		userOp.get().setPassword(null);
		
		return userOp.get();
	}


}
