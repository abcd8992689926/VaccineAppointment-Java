package com.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.va.model.JWTModel;
import com.va.model.UserModel;
import com.va.model.VerifyModel;
@RestController
@RequestMapping("jwt")
public class JwtController {
	@Autowired
    JWTModel jwtmodel;
	@Autowired
	VerifyModel verifymodel;
	@Autowired
	UserModel usermodel;
	/*@PostMapping("/get")
	public String getJwt() {
		String jwt=usermodel.getToken();
		return jwt;
	}*/
	@PostMapping("/verify")
    public void verifyJwt(){
		//null
    }
}
