package com.va.model;

import java.sql.Date;
import java.util.Base64;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTModel {
	public String create(String id){
        Date expireDate = new Date(System.currentTimeMillis()+ 10 * 60 * 1000);//設定10min過期
        String jwtToken = Jwts.builder()
        .setSubject(id) //id當subject
        .setExpiration(expireDate)
        //MySecret是自訂的私鑰，HS512是自選的演算法，可以任意改變
        .signWith(SignatureAlgorithm.HS512,"celeste")
        .compact();
        //直接將JWT傳回
        return jwtToken;
	}
	public String decode(String token) {
		String[] chunks=token.split("\\.");
		Base64.Decoder decoder=Base64.getDecoder();
		String payload=new String(decoder.decode(chunks[1]));
		return payload.substring(8,18);
	}
}
