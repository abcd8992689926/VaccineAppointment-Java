package com.va.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.va.demo","com.va.model","com.va.controller","com.va.service","com.va.repository"})
@EntityScan({"com.va.demo","com.va.model","com.va.controller","com.va.service","com.va.repository"})
public class JavaVaccineAppointmentApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JavaVaccineAppointmentApplication.class, args);
	}

}
