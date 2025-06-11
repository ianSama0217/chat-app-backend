package com.example.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chat.entity.Contact;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer>{

}
