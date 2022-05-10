package com.va.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.va.repository.UserRepository;

@Component
public class VerifyModel {
	@Autowired
	JWTModel jwtmodel;
	@Autowired
	UserRepository userrepository;
	@Autowired
	UserModel usermodel;
	public String user(String id, String NHI) {
		/*
		0000 login success
		0001 wrong id
		0002 wrong NHI
		9999 unknown error
		*/
		String result="9999";
		int count=userrepository.getIdCount(id);
		if(count!=0){
			String oNHI=userrepository.getNHI(id);
			if(NHI.equals(oNHI)) {
				result=jwtmodel.create(id);
			}else{
				result="0002";
			}
		}else{
			result="0001";
		}
		return result;
	}
}
