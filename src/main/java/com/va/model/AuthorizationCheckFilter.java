package com.va.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthorizationCheckFilter extends OncePerRequestFilter{
	@Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		//如果不是登入就攔截
		boolean [] aa = {
				!req.getServletPath().equals("/api/user/login"),
				!req.getServletPath().equals("/"),
				!req.getServletPath().equals("/choice"),
				!req.getServletPath().equals("/jwt/get"),
				!req.getServletPath().equals("/index"),
				!req.getServletPath().equals("/api/user/test"),
				!req.getServletPath().equals("/api/user/generate"),
				!req.getServletPath().equals("/simulation"),
				!req.getServletPath().equals("/searchCounty"),
				!req.getServletPath().equals("/searchDistrict"),
				!req.getServletPath().equals("/searchHosp"),
				!req.getServletPath().equals("/searchDistrictDetail"),
				!req.getServletPath().equals("/searchPersonal"),
				!req.getServletPath().equals("/search"),
				!req.getServletPath().equals("/searchRecord"),
				!req.getServletPath().equals("/register"),
				!req.getServletPath().equals("/informationSheet")	
		};
		if(aa[0]&&aa[1]&&aa[2]&&aa[3]&&aa[4]&&aa[5]&&aa[6]&&aa[7]&&aa[8]&&aa[9]&&aa[10]&&aa[11]&&aa[12]&&aa[13]&&aa[14]&&aa[15]&&aa[16]){
        	String authorHeader =  req.getHeader(AUTHORIZATION);
            String bearer ="Bearer ";
            //以jjwt驗證token，只要驗證成功就放行
            //驗證失敗會拋exception，直接將錯誤訊息傳回
            if(authorHeader!= null && authorHeader.startsWith(bearer)){
            	try{
                    String token = authorHeader.substring(bearer.length());
                    Claims claims = Jwts.parser().setSigningKey("celeste")
                    		.parseClaimsJws(token).getBody();

                    System.out.println("JWT payload:"+claims.toString());

                    chain.doFilter(req, res);
                    
                    }catch(Exception e){
                        System.err.println("Error : "+e);
                        res.setStatus(FORBIDDEN.value());
                        
                        Map<String, String> err = new HashMap<>();
                        err.put("jwt_err", e.getMessage());
                        res.setContentType(APPLICATION_JSON_VALUE);
                        new ObjectMapper().writeValue(res.getOutputStream(), err);
                    }
                }else{
                    res.setStatus(UNAUTHORIZED.value());
                }
            }else{
                chain.doFilter(req, res);
            }
    }
}
