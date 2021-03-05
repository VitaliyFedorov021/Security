package com.example.demo.dao;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public interface UserDao extends JpaRepository<User, Integer>
{
	List<User> findAll();

	User findByUsername(String username);
}
