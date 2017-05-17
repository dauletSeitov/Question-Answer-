package com.sf.MyOwnLibrary;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sf.fsd.User;

public interface DBhelpAble {

	public User doUserExist(String str1, String str2);
	
	public ArrayList<ArrayList<String>> getAllquestions(String category);
	
	public ArrayList<ArrayList<String>> getAnswerByIDQuestion(int id);
	
	public ArrayList<ArrayList<String>> getAllCategories();
	
	public void addQuestion(String title, String question, String date, int userId, int categoryId);
	
	public void addCategory(String category);
		
	public int getUserId(String user);
	
	public int getCategoryId(String category);
	
	public void addUser(String name, String login, String email, String password);
	
	public void addAnswer(String answer, String date, int questionId, int UserId);
	
	

}


