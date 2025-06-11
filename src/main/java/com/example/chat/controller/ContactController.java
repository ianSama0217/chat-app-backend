package com.example.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat.entity.Contact;
import com.example.chat.service.ifs.ContactService;

@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@PostMapping("contact")
	public void createContact(@RequestBody Contact contact) throws Exception{
		contactService.createContact(contact);
	}
}
