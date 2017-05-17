package com.sf.fsd;

import java.util.HashMap;
import java.util.Map;
/*
 * Etot klass soderzhit spisok polzovateley 
 * 
 * 
 * 
 * Problema v tom chto esli polzovatel ushel, on ostanetsya zombiem v etom spiske 
 * po etoi prichine vremya ot vremeni nado proveryat aktiven li polzovatel
 * 
 */
public class UserHandler {
	
	private Map<String, User> users = new HashMap<String, User>();

	public void putUser(String sesion, User user){
		if(!(user != null) || !(sesion != null) || sesion.isEmpty() )
			return;
		users.put(sesion, user);
	}
	
	public boolean doUserLogged(String sesion){
		if(!(sesion != null) || sesion.isEmpty())
			return false;
		
		if(users.get(sesion) != null)
			return true;
		
		return false;
	}
	
	
	public User getUserBysesion(String sesion){
		return users.get(sesion);
		
	}
	
	public void deleteSesion(String sesion){
		 users.remove(sesion);
	}
	
}
