package com.va.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*public List<String> getNHI(String id){
		List<String> usernameList = new ArrayList<>();
		usernameList.addAll(jdbcTemplate.queryForList("SELECT NHI_id FROM national_information where id='"+id+"';", String.class));
		return usernameList;
	}*/
	String sql = "";
	public String getNHI(String id) {
		sql="SELECT NHI_id FROM national_information where id='"+id+"';";
		String a=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, String.class);
		return a;
	}
	public int getIdCount(String id) {
		sql="SELECT COUNT(*) FROM national_information where id='"+id+"';";
		int count=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return count;
	}
	public int getRegister(String id) {
		sql="SELECT register FROM national_information where id='"+id+"';";
		int register=jdbcTemplate.queryForObject(sql, new java.lang.Object[] {}, Integer.class);
		return register;
	}
	public void updateNation(String id) {
		sql="update national_information set register='1' where id='"+id+"';";
		jdbcTemplate.update(sql);
	}
}