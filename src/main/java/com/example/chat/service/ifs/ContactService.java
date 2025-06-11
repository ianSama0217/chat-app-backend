package com.example.chat.service.ifs;

import com.example.chat.entity.Contact;

public interface ContactService {
	/**
	 * 新增聯絡人
	 *
	 * @param Contact 聯絡人關係
	 * @return void
	 * @throws Exception 新增失敗
	 */
	public void createContact(Contact contact) throws Exception;
}
