package com.va.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.va.repository.UserRepository;

@Component
public class UserModel {
	@Autowired
	UserRepository userrepository;
	@Autowired
	JWTModel JWTModel;
	public int getRegister(String token) {
		String id=JWTModel.decode(token);
		return userrepository.getRegister(id);
	}
}
