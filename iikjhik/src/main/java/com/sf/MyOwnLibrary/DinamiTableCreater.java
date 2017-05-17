package com.sf.MyOwnLibrary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DinamiTableCreater {
	
	public static String fillTable (String [][] table, int row, int col){
		String htmlString = "<table> <tr> <th>Losted stuffs</th> <th>Answeres</th> "
				+ "<th>Categories</th> <th>When was losted</th> <th>Whos stuffs</th> </tr>"; //head of table
  
		for (int i = 0; i < row; i++){
			htmlString +="<tr>";
			for (int k = 0; k < col; k++){
				htmlString += "<td>";
				htmlString += table[i][k];
				htmlString += "</td>";
				}
			htmlString +="<tr>";
		}
		htmlString += "</table>";
		
		return htmlString;
	}

	public static String fillTable (String [][] table){//sample 2d array
		String htmlString = "<table> "; 
		for (int i = 0; i < table.length; i++){
			htmlString +="<tr>";
			for (int k = 0; k < table[0].length; k++){
				htmlString += "<td>";
				htmlString += table[i][k];
				htmlString += "</td>";
				}
			htmlString +="<tr>";
		}
		htmlString += "</table>";
		return htmlString;
	}
	
	public static String fillTable (ArrayList<ArrayList<String>>  list){ // list of list
		String htmlString = "<table> "; 
		for (ArrayList<String> row : list){
			htmlString +="<tr>";
			for (String str : row){
				htmlString += "<td>";
				htmlString += str;
				htmlString += "</td>";
				}
			htmlString +="<tr>";
		}
		htmlString += "</table>";
		return htmlString;
	}


	public static String correct(String request){
		return request.replace("select","select_")
				.replace("delete", "delete_")
				.replace("update", "update_")
				.replace("insert", "insert_");
	} 
	
	public static String check(String request){
		
		return request.replace("select","select_")
				.replace("delete", "delete_")
				.replace("update", "update_")
				.replace("insert", "insert_");
	}
	
	/* FUNKTSIA DLYA vstraivania v sql kod parametrov
	 * sql - sam sql kod k primeru select * from somewhere where id = <0>
	 * v ArrayList hranitsya parametri, pervi parametr iz arraylist syadet na mesto <0>, vtoroi na <1> itd
	 * esli v sql tekste est parametr <8> no v arrayList net vosmogo elementa tot funktsia ne zamenyaet <8> ni na chto
	 * Skynet 15/11/16 
	 */
	public static String replace (String sql, ArrayList<String> params){
	    for (int i = 0; i < params.size(); i++){
	        sql = sql.replace("<"+ i +">", params.get(i)); 
	    }
	return sql;
	}

}
