package com.va.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimulationRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	String sql = "";
	public long getLastNHI() {
		sql="SELECT NHI_id FROM national_information order by NHI_id DESC LIMIT 1;";
		long last=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, long.class);
		return last;
	}
	public void insertPBF(String id, String NHI, String name, String date, int register) {
		sql="INSERT INTO national_information VALUES ('"+id+"', '"+NHI+"', '"+name+"', '"+date+"', '"+register+"');";
		jdbcTemplate.update(sql);
	}	
	public void insertVR(String id, int dose, String manufacturer, String date, String HOSPID) {
		sql="INSERT INTO vaccine_record VALUES ('"+id+"', '"+dose+"', '"+manufacturer+"', '"+date+"', '"+HOSPID+"');";
		jdbcTemplate.update(sql);
	}
	public void insertA(String id, int dose, String HOSPID, String manufacturer, String date) {
		sql="INSERT INTO appointment VALUES ('"+id+"', '"+dose+"', '"+HOSPID+"', '"+manufacturer+"', '"+date+"');";
		jdbcTemplate.update(sql);
	}
	public void insertRegister(String id, String phone_num, String prefer_zip_code_int, String prefer_zip_code_str, int prefer_manufacturer_ID, String date) {
		sql="replace INTO register VALUES ('"+id+"', '"+phone_num+"', '"+prefer_zip_code_int+"', '"+prefer_zip_code_str+"', '"+prefer_manufacturer_ID+"', '"+date+"');";
		jdbcTemplate.update(sql);
	}
	public int getZipCodeCount(int zipCode) {
		sql="SELECT COUNT(*) FROM hosp_detail where zip_code="+zipCode+";";
		int count=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return count;
	}
	public String getHOSPID(int zipCode, int row) {
		sql="SELECT HOSP_ID FROM hosp_detail where zip_code="+zipCode+" LIMIT "+row+",1;";
		String HOSPID=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, String.class);
		return HOSPID;
	}
	public int getNHICount() {
		sql="SELECT COUNT(*) FROM national_information;";
		int count=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return count;
	}
	public int getPhoneCount(String phoneNum) {
		sql="SELECT COUNT(*) FROM register where phone_num='"+phoneNum+"';";
		int count=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return count;
	}
	public void updateDose(String level, int dose) {
		sql="update hosp_dose set dose='"+dose+"' where level='"+level+"';";
		jdbcTemplate.update(sql);
	}
}
