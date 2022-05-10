package com.va.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SpringBoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@GetMapping("/x")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World !";
	}
	
	@GetMapping("/index")
	public String index(){
		return "index";
	}
	@GetMapping("/choice")
	public String choice(){
		return "choice.html";
	}
	@GetMapping("/simulation")
	public String simulation(){
		return "simulation.html";
	}
	@GetMapping("/searchCounty")
	public String searchCounty(){
		return "searchCounty.html";
	}
	@GetMapping("/searchDistrict")
	public String searchDistrict(){
		return "searchDistrict.html";
	}
	@GetMapping("/searchHosp")
	public String searchHosp(){
		return "searchHosp.html";
	}
	@GetMapping("/searchDistrictDetail")
	public String searchDistrictDetail(){
		return "searchDistrictDetail.html";
	}
	@GetMapping("/searchPersonal")
	public String personal(){
		return "searchPersonal.html";
	}
	@GetMapping("/search")
	public String search(){
		return "search.html";
	}
	@GetMapping("/searchRecord")
	public String record(){
		return "searchRecord.html";
	}
	@GetMapping("/register")
	public String register(){
		return "register.html";
	}
	@GetMapping("/informationSheet")
	public String informationSheet(){
		return "informationSheet.html";
	}
}