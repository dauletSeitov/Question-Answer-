package com.sf.fsd;

import javax.servlet.http.HttpSession;

public class User {
	private String 	name;
	private String 	login;
	private HttpSession 	session;
			
	public User(String login, String name){
		this(login);
		this.name = name;
	}
	
	public User(String login){
		this.login = login;
	}
	
	public String getName(){
		return name;	
	}
	
	public void setName(String name){
		this.name = name;	
	}
	public boolean isLogged(String session) {
		return this.session.equals(session);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public HttpSession getIdSession(){
		return session;	
	}
	
	public void SetSession(HttpSession session){
		this.session = session;
	}

}
