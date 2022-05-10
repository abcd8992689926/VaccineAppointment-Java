package com.va.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.va.model.PparameterModel;
import com.va.model.SimulationModel;
import com.va.model.UserModel;
import com.va.model.VerifyModel;
import com.va.repository.UserRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin(origins = " http://localhost:8080 ")
@RequestMapping("api/user")
public class UserController {
	@Autowired
	UserRepository userrepository;
	@Autowired
	VerifyModel verifymodel;
	@Autowired
	UserModel usermodel;
	@Autowired
	SimulationModel simulationmodel;
	@Autowired
	PparameterModel pparametermodel;

	@PostMapping("/login")
	public @ResponseBody String login(@RequestParam String id, @RequestParam String NHI) {
		return verifymodel.user(id, NHI);
	}

	@PostMapping("/getRegistered")
	public @ResponseBody int getRegistered(@RequestParam String token) {
		return usermodel.getRegister(token);
	}

	@PostMapping("/generate")
	public void generate(@RequestBody Map<String, Integer> map) {
		int people = map.get("people");
		pparametermodel.setTal(people);
		pparametermodel.setAge(0, map.get("child") * people / 100);
		pparametermodel.setAge(1, map.get("teenager") * people / 100);
		pparametermodel.setAge(2, map.get("youth") * people / 100);
		pparametermodel.setAge(3, map.get("middle") * people / 100);
		pparametermodel.setAge(4, map.get("elderly") * people / 100);
		pparametermodel.setAge(5, 0);
		pparametermodel.setMale(map.get("male") * people / 100);
		pparametermodel.setFemale(map.get("female") * people / 100);
		pparametermodel.setPreferenceTal(0, map.get("Moderna") * people / 100);
		pparametermodel.setPreferenceTal(1, map.get("BioNTech") * people / 100);
		pparametermodel.setPreferenceTal(2, map.get("AstraZeneca") * people / 100);
		pparametermodel.setPreferenceTal(3, map.get("MVC") * people / 100);
		for (int i = 0; i < 26; i++) {
			pparametermodel.setCity(i, map.get("city" + i) * people / 100);
		}
		pparametermodel.setCity(26, 0);
		int ZDLength = map.get("ZDLength");
		if (ZDLength != 0)
			pparametermodel.setZDLength(ZDLength * people / 100);
		else
			pparametermodel.setZDLength(ZDLength);
		int FDLength = map.get("FDLength");
		if (FDLength != 0)
			pparametermodel.setFDLength(FDLength * people / 100);
		else
			pparametermodel.setZDLength(FDLength);
		for (int i = 0; i < 26; i++) {
			pparametermodel.setManufacturer(i, 0, map.get("FDModerna" + i));
			pparametermodel.setManufacturer(i, 1, map.get("FDBioNTech" + i));
			pparametermodel.setManufacturer(i, 2, map.get("FDAstraZeneca" + i));
			pparametermodel.setManufacturer(i, 3, map.get("FDMVC" + i));
		}
		pparametermodel.setLast(0, 0);
		pparametermodel.setLast(1, 0);
		System.out.println("before age:" + Arrays.toString(pparametermodel.getAge()));
		System.out.println("before male:" + pparametermodel.getMale());
		System.out.println("before female:" + pparametermodel.getFemale());
		System.out.println("before Preference:" + Arrays.deepToString(pparametermodel.getPreference()));
		System.out.println("before city:" + Arrays.toString(pparametermodel.getCity()));
		System.out.println("before ZD:" + pparametermodel.getZDLength());
		System.out.println("before FD:" + pparametermodel.getFDLength());
		System.out.println("before Manufacturer:" + Arrays.deepToString(pparametermodel.getManufacturer()));
		simulationmodel.simulation();
		System.out.println("after age:" + Arrays.toString(pparametermodel.getAge()));
		System.out.println("after male:" + pparametermodel.getMale());
		System.out.println("after female:" + pparametermodel.getFemale());
		System.out.println("after Preference:" + Arrays.deepToString(pparametermodel.getPreference()));
		System.out.println("after city:" + Arrays.toString(pparametermodel.getCity()));
		System.out.println("after ZD:" + pparametermodel.getZDLength());
		System.out.println("after FD:" + pparametermodel.getFDLength());
		System.out.println("after Manufacturer:" + Arrays.deepToString(pparametermodel.getManufacturer()));
	}

	@PostMapping("/searchCounty")
	public @ResponseBody int[] searchCounty(@RequestParam int county) {
		return simulationmodel.getCountyDose(county);
	}

	@PostMapping("/searchDistrict")
	public @ResponseBody int[] searchDistrict(@RequestParam int county) {
		return simulationmodel.getDistrictDose(county);
	}

	@PostMapping("/searchHosp")
	public int[] searchHosp() {
		return simulationmodel.getHospDose();
	}

	@PostMapping("/searchDistrictDetail")
	public @ResponseBody String[][] searchDistrictDetail(@RequestParam int county, @RequestParam int district) {
		return simulationmodel.getDistrictDetail(county, district);
	}

	@PostMapping("/searchPersonal")
	public @ResponseBody String[] searchPersonal(@RequestParam String token) {
		return simulationmodel.searchPersonal(token);
	}

	@PostMapping("/searchRecord")
	public @ResponseBody String[][] searchRecord(@RequestParam String token) {
		return simulationmodel.searchRecord(token);
	}

	@PostMapping("/searchName")
	public String[] searchName(@RequestParam String token) {
		return simulationmodel.searchName(token);
	}

	@PostMapping("/insertRegister")
	public void insertRegister(@RequestParam String token, @RequestParam int county, @RequestParam int district,
			@RequestParam String zipStr, @RequestParam String phoneNum, @RequestParam int manufacturer) {
		simulationmodel.insertRegister(token, county, district, zipStr, phoneNum, manufacturer);
	}

	@PostMapping("/converterManufacturer")
	public String[] converterManufacturer(@RequestParam int manufacturer0, @RequestParam int manufacturer1,
			@RequestParam int manufacturer2, @RequestParam int manufacturer3) {
		int manufacturer[] = new int[4];
		manufacturer[0] = manufacturer0;
		manufacturer[1] = manufacturer1;
		manufacturer[2] = manufacturer2;
		manufacturer[3] = manufacturer3;
		return simulationmodel.converterManufacturer(manufacturer);
	}
}
