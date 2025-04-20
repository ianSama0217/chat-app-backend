package com.example.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.chat.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
	@Query(value = "SELECT r.name "
			+ "FROM user AS u "
			+ "JOIN user_role AS ur "
			+ "ON u.id = ur.user_id "
			+ "JOIN role AS r "
			+ "ON ur.role_id = r.role_id "
			+ "WHERE u.id = :id", nativeQuery = true)
	public List<String> findUserRoleList(@Param("id")Integer userId);
	
	// 註冊時設定user role(role預設2)
	@Modifying
	@Query(value = "INSERT INTO user_role(user_id, role_id) "
			+ "VALUES(:userId, 2) ", nativeQuery = true)
	public int setUserRole(@Param("userId")Integer userId);
}
