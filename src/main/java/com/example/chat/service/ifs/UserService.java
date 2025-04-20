package com.example.chat.service.ifs;

import com.example.chat.entity.User;
import com.example.chat.vo.LoginReq;

public interface UserService {

	// auth類: logout
	
	/**
	 * 註冊帳號
	 *
	 * @param user 使用者
	 * @return 註冊後的使用者資訊
	 * @throws Exception 註冊失敗
	 */
	public User register(User user) throws Exception;
	
	/**
	 * 登入
	 *
	 * @param LoginReq (信箱, 密碼)
	 * @return void
	 * @throws Exception 登入失敗
	 */
	public void login(LoginReq req) throws Exception;
	
	// user類: findUser, findConnect
	/**
	 * 查詢user(單筆)資料
	 *
	 * @param user id
	 * @return 使用者資訊
	 * @throws Exception 使用者不存在, 帳號禁用
	 */
	public User findUser(Integer userId) throws Exception;
}
