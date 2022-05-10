package com.va.repository;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	String sql = "";
	public int getSumDose(int zipCode[]) {
		sql="select SUM(dose) from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where zip_code in("+Arrays.toString(zipCode).replace("[","").replace("]","")+");";
		int sum=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return sum;
	}
	public int getSumDose(int zipCode) {
		sql="select SUM(dose) from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where zip_code="+zipCode+";";
		int sum=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return sum;
	}
	public int getSumDose(String zipCode) {
		sql="select SUM(dose) from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where address like '%"+zipCode+"%';";
		int sum=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return sum;
	}
	public int getLevelDose(String level) {
		sql="select SUM(dose) from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where a.level='"+level+"';";
		int sum=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return sum;
	}
	public String[][] getNAD(int zipCode) {
		sql="select HOSP_name,address,dose from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where zip_code="+zipCode+";";
		List<Map<String, Object>> results = (List<Map<String, Object>>)jdbcTemplate.queryForList(sql);
		String NAD[][]=new String[results.size()][3];
		int point=0;
		for(Map<String, Object> result : results) {
			NAD[point][0]=(String)result.get("HOSP_name");
			NAD[point][1]=(String)result.get("address");
			NAD[point][2]=result.get("dose")+"";
			point++;
		}
		return NAD;
	}
	public String[][] getNAD(String zipCode) {
		sql="select HOSP_name,address,dose from hosp_detail as a LEFT OUTER JOIN hosp_dose as b on a.level=b.level where address like '%"+zipCode+"%';";
		List<Map<String, Object>> results = (List<Map<String, Object>>)jdbcTemplate.queryForList(sql);
		String NAD[][]=new String[results.size()][3];
		int pointer=0;
		for(Map<String, Object> result : results) {
			NAD[pointer][0]=(String)result.get("HOSP_name");
			NAD[pointer][1]=(String)result.get("address");
			NAD[pointer][2]=result.get("dose")+"";
			pointer++;
		}
		return NAD;
	}
	public String[] getNational(String id) {
		sql="SELECT n.id,n.name,n.birthday,\r\n"
				+ "r.phone_num,r.prefer_zip_code_str,r.prefer_manufacturer,r.date\r\n"
				+ "FROM national_information as n\r\n"
				+ "  LEFT JOIN register as r ON n.id=r.id\r\n"
				+ "  where n.id='"+id+"';";
		List<Map<String, Object>> results = (List<Map<String, Object>>)jdbcTemplate.queryForList(sql);
		String national[]=new String[7];
		for(Map<String, Object> result : results) {
			national[0]=(String)result.get("id");
			national[1]=(String)result.get("name");
			national[2]=result.get("birthday")+"";
			national[3]=result.get("phone_num")+"";
			national[4]=result.get("prefer_manufacturer")+"";
			national[5]=result.get("prefer_zip_code_str")+"";
			national[6]=result.get("date")+"";
		}
		return national;
	}
	public String[][] getRecord(String id) {
		sql="SELECT n.id,n.name,v.vaccinated_dose,v.manufacturer_ID,v.vaccinated_date,\r\n"
				+ "h.HOSP_name,h.address\r\n"
				+ "FROM vaccine_record as v\r\n"
				+ "  LEFT JOIN hosp_detail as h ON v.HOSP_ID=h.HOSP_ID\r\n"
				+ "  LEFT JOIN national_information as n ON v.id=n.id\r\n"
				+ "  where n.id='"+id+"';";
		List<Map<String, Object>> results = (List<Map<String, Object>>)jdbcTemplate.queryForList(sql);
		String record[][]=new String[results.size()][7];
		int pointer=0;
		for(Map<String, Object> result : results) {
			record[pointer][0]=(String)result.get("id");
			record[pointer][1]=(String)result.get("name");
			record[pointer][2]=result.get("vaccinated_dose")+"";
			record[pointer][3]=result.get("manufacturer_ID")+"";
			record[pointer][4]=(String)result.get("HOSP_name");
			record[pointer][5]=(String)result.get("address");
			record[pointer][6]=result.get("vaccinated_date")+"";
			pointer++;
		}
		return record;
	}
	public String getName(String id) {
		sql="SELECT name FROM national_information where id='"+id+"';";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, String.class);
	}
	public int getRecordCount(String id) {
		sql="SELECT COUNT(*) FROM vaccine_record where id='"+id+"';";
		int count=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return count;
	}
}
