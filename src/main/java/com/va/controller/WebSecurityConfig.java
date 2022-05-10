	package com.va.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.va.model.AuthorizationCheckFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		//授權請求規則
		http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/api/user/**").hasRole("root");
		//開啟登入功能
		http.formLogin();
		*/
        http
        //關閉csrf保護
        .csrf().disable()
        //關閉session
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
		//攔截filter
        .addFilterBefore(new AuthorizationCheckFilter(), BasicAuthenticationFilter.class);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}
}
