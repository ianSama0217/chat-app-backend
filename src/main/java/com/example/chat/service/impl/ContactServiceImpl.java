package com.example.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chat.entity.Contact;
import com.example.chat.repository.ContactDao;
import com.example.chat.repository.UserDao;
import com.example.chat.service.ifs.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ContactDao contactDao;
	
	@Override
	public void createContact(Contact contact) throws Exception {
		
		// 檢查 user_id, contact_id 是否存在
		if(!userDao.existsById(contact.getUserId()) || !userDao.existsById(contact.getContactId())) {
			throw new Exception("使用者不存在");
		}
		
		try {
			contactDao.save(contact);
		}catch (Exception e) {
			throw new Exception("新增聯絡人失敗" + e.getMessage());
		}
		
	}

}
