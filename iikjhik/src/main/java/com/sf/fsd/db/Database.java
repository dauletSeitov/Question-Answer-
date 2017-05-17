package com.sf.fsd.db;

import java.sql.Connection;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sf.MyOwnLibrary.DBhelpAble;
import com.sf.MyOwnLibrary.DinamiTableCreater;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sf.MyOwnLibrary.DBhelpAble;
import com.sf.MyOwnLibrary.DinamiTableCreater;
import com.sf.fsd.User;

public class Database implements DBhelpAble {

	private JdbcTemplate jt; 
		
	public Database(){
		DriverManagerDataSource  dbcp = new DriverManagerDataSource (); //HARD WRITING I KNOW, i will fix its later	
		dbcp.setDriverClassName("com.mysql.jdbc.Driver");
		dbcp.setUsername("root");
		//dbcp.setUrl("jdbc:mysql://localhost:3306/qa");
		//dbcp.setPassword("bagdaulet5618A");
		
		dbcp.setUrl("jdbc:mysql://mysql30136-whiskey.j.dnr.kz/qa");
		dbcp.setPassword("nlSCTyUVFw");
		
		jt = new JdbcTemplate(dbcp);
	}
	
	@Override
	public User doUserExist(String login,String password) {
		
		String sql = " SELECT user.name name "
				+ " FROM qa.user user "
				+ " where 1=1 "
				+ " and user.login = '<0>' "
				+ " and user.password = '<1>'; ";
				
		ArrayList<String> params = new ArrayList<String>();
		
		params.add(login);
		params.add(password);
		
		String UserName = "";
		try {
			UserName = jt.queryForObject(DinamiTableCreater.replace(sql, params), String.class); // esli u nas nichego net a db to vozvrashayem nol
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	
		return new User(login, UserName); 
	}

	@Override
	public ArrayList<ArrayList<String>> getAllquestions(String category) {
		
		ArrayList<ArrayList<String>> MainTable = new ArrayList<ArrayList<String>>(); 
		ArrayList<String> row = new ArrayList<String>(); 
		
		String sql = " SELECT questions.title 										  title, " 
+" (select count(id) from qa.anwere  answere where answere.questionRef = questions.id) answere, "
+" categories.category																  category, "
+" questions.date 																	  date, "
+" user.name 																		  name,"
+" questions.id 																		  id "
+" FROM qa.questions questions "
+" left join qa.user user on questions.userRef_ = user.id "
+" left join qa.categories categories on questions.categoryRef = categories.id ";
		
		if (!(category != null) || (category.isEmpty()))
			sql = sql.concat(" order by date desc;");
		else
			{
			sql = sql.concat(" where categories.category = '<0>' "
					+ " order by date desc;");
			ArrayList<String> params = new ArrayList<String>();
			params.add(category);
			sql = DinamiTableCreater.replace(sql, params);
		}
		List<Map<String, Object>> rows = jt.queryForList(sql);		
		
		MainTable.add(row); //add empty row
		
		for(Map<String, Object> elem : rows){
			
			row = new ArrayList<String>();
			row.add(String.valueOf(elem.get("title")));
			row.add(String.valueOf(elem.get("answere")));
			row.add(String.valueOf(elem.get("category")));
			row.add(String.valueOf(elem.get("date")));
			row.add(String.valueOf(elem.get("name")));
			row.add(String.valueOf(elem.get("id")));
			
			MainTable.add(row);
		}
		
		return MainTable; 
	}

	@Override
	public ArrayList<ArrayList<String>> getAnswerByIDQuestion(int id) {

		ArrayList<ArrayList<String>> MainTable = new ArrayList<ArrayList<String>>(); 
		ArrayList<String> row; 
		
		String sql = "SELECT "
				+ "answere.anwere					answer, "
				+ "answere.time						date, "
				+ "user.name						user "
				+ "FROM qa.anwere answere left join "
				+ "qa.user user on answere.userRef = user.id " 
				+ "WHERE answere.questionRef = <0>; ";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(String.valueOf(id));
		
		List<Map<String, Object>> rows = jt.queryForList(DinamiTableCreater.replace(sql, params));		
		
		for(Map<String, Object> elem : rows){
			
			row = new ArrayList<String>();
			row.add(String.valueOf(elem.get("answer")));
			row.add(String.valueOf(elem.get("date")));
			row.add(String.valueOf(elem.get("user")));
			row.add(String.valueOf(id));
			
			MainTable.add(row);
		}
		
		return MainTable;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllCategories() {

		ArrayList<ArrayList<String>> MainTable = new ArrayList<ArrayList<String>>(); 
		ArrayList<String> row = new ArrayList<String>(); 
		
		String sql = "SELECT "
				+ "category.id			id, "
				+ "category.category 	category "
				+ "FROM qa.categories category;";

		List<Map<String, Object>> rows = jt.queryForList(sql);		
		
		for(Map<String, Object> elem : rows){
	
			row.add(String.valueOf(elem.get("category")));
			row.add(String.valueOf(elem.get("id")));
						
		}
		
		MainTable.add(row);
		
		
		
		return MainTable;
	}
	
	@Override
	public void addCategory(String category) {
				 
        String sql = " INSERT INTO qa.categories  (category) "
        		+ " VALUES ('<0>'); ";

        ArrayList<String> params = new ArrayList<String>();
		params.add(category);
		
		try{
			jt.update(DinamiTableCreater.replace(sql, params));
		}
		catch(DuplicateKeyException e){
	        System.out.println("value " + category + " exist in table category");
		}
		
	}

	@Override
	public int getCategoryId(String category) {
		
		String sql = " SELECT  category.id id " 
				+ " FROM qa.categories category "
				+ " where category.category = '<0>';";
				
		ArrayList<String> params = new ArrayList<String>();
		params.add(category);
		
		try {
			return jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class); // esli u nas nichego net a db to vozvrashayem nol
		}catch(EmptyResultDataAccessException e){
			return 0;
		}
	}

	@Override
	public int getUserId(String user) {

		
		String sql = "SELECT user.id id"
				 + " FROM qa.user"
				 + " where user.login = '<0>';";
				
		ArrayList<String> params = new ArrayList<String>();
		params.add(user);
		
		try {
			return jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class); // esli u nas nichego net a db to vozvrashayem nol
		}catch(EmptyResultDataAccessException e){
			return 0;
		}
	}
	
	@Override
	public void addQuestion(String title, String question, String date, int userId, int categoryId) {
		 
		String sql = " INSERT INTO qa.questions "
				+ "(question, date, title, userRef_, categoryRef)"
				+ "VALUES ('<0>','<1>','<2>', '<3>','<4>');";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(question);
		params.add(date);
		params.add(title);
		params.add(String.valueOf(userId));
		params.add(String.valueOf(categoryId));
		
		jt.update(DinamiTableCreater.replace(sql, params));
		
	}

	@Override
	public void addUser(String name, String login, String email, String password) {
		 
        String sql = " INSERT INTO qa.user (name, email, login, password)"
        		+ "VALUES('<0>','<1>','<2>','<3>');";

        ArrayList<String> params = new ArrayList<String>();
		params.add(name);
		params.add(email);
		params.add(login);
		params.add(password);
		
		try{
			jt.update(DinamiTableCreater.replace(sql, params));
		}
		catch(DuplicateKeyException e){
	        System.out.println("value " + login + " exist in table users");
		}

		
	}

	@Override
	public void addAnswer(String answer, String date, int questionId, int UserId) {
		 
		String sql = " INSERT INTO qa.anwere "
				+ " (anwere, time, questionRef, userRef) "
				+ " VALUES('<0>','<1>',<2>,<3>); ";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(answer);
		params.add(date);
		params.add(String.valueOf(questionId));
		params.add(String.valueOf(UserId));
		
		jt.update(DinamiTableCreater.replace(sql, params));

	}

}
