package com.example.chat.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.chat.entity.User;
import com.example.chat.repository.UserDao;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("使用者帳號不存在" + username);
		}

		String userEmail = user.getEmail();
		String userPassword = user.getPassword();
		// 權限
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// 轉換成 spring security 指定的 User 格式
		return new org.springframework.security.core.userdetails.User(userEmail, userPassword, authorities);

	}

}
