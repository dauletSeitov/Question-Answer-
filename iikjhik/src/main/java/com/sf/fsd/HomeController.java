package com.sf.fsd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXTransformerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sf.MyOwnLibrary.DBhelpAble;
import com.sf.MyOwnLibrary.DinamiTableCreater;
import com.sf.fsd.db.Database;


@Controller
public class HomeController {

	private DBhelpAble dataBase;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//private User user;

	private UserHandler users = new UserHandler();
	String sign =  "\""; // "
	
	//@RequestMapping(value = "/questionPpost", method = RequestMethod.POST)
	@RequestMapping(value = "/questionPpost", method = RequestMethod.POST)
	public String homePost2(
			Locale locale, 
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "Title", 			defaultValue = "") String title,
			@RequestParam(value = "Text", 			defaultValue = "") String text,
			@RequestParam(value = "category", 		defaultValue = "") String category,
			@RequestParam(value = "newCategory", 	defaultValue = "") String newCategory
			){
		
		User user = null;
		
		System.out.println(title);
		System.out.println(text);
		System.out.println(category);
		
		//Proverka vhodnih dannih
				
		if((title.isEmpty()) || (text.isEmpty()))//esli dannie ne pravilni vozvrashaemsya
			return "question";
		
		if(category.isEmpty() && newCategory.isEmpty())//esli ne vibran kategorii i ne propisan novi to vozvrashayemsya
			return "question";
		
		if(category.equals("new") && newCategory.isEmpty())//esli vibran new i ne propisan novi to vozvrashayemsya
			return "question";
		
		if(!category.equals("new") && !newCategory.isEmpty())//esli vibran ne new i propisan novi to vozvrashayemsya
			return "question";
	
		//Proverka vhodnih dannih
		
		if(users.doUserLogged(request.getSession(false).getId())){
			user = users.getUserBysesion(request.getSession(false).getId());
									 //String df =   "<a href='/fsd/user?name='" + user.getName() + "'> + '" + user.getName() + "' </a>";
			model.addAttribute("sign", "<a href='/fsd/user?name='" + user.getName() + ">" + user.getName() + "</a>");
		}
		else
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
		
		
		
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		
		if(category.equals("new")){ //ESLI kategoria novaya to zapisivaem v db
			dataBase.addCategory(newCategory.trim());
			System.out.println("we cewated new category " + newCategory);
		}else						//inache polzuemsya staroi
			newCategory = category.replace(",", ""); //udalyaem zapyatie
		
		//get user
		int userId = dataBase.getUserId(user.getName());
			if (userId == 0) System.out.println("user not exist");
		//get user
			
		//get category
		int categoryId = dataBase.getCategoryId(newCategory);
		if (categoryId == 0) System.out.println("category not exist");
		//get category	
		
		dataBase.addQuestion(title, text, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), userId, categoryId);
		System.out.println("added to questions " + title + " " + text + " " + dataBase.getUserId(user.getName()) + " " + newCategory);
		
		return "redirect:/";
	}
	
	//@RequestMapping(value = "/question", method = RequestMethod.POST)
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String question(Model model, HttpServletRequest request){
		
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		
		//category
		ArrayList<ArrayList<String>> myTable = dataBase.getAllCategories();
				
		ArrayList<String> at = myTable.get(0);
		
		
				
		for (int i = 0; i < at.size(); i += 2) {
			try{
				at.set(i, "<input type='radio' name='category' value='"+ at.get(i) +"'> " + at.get(i));		
					
			}catch(IndexOutOfBoundsException e){System.out.println("we skip some notes ");}
		}
		
		at.add( "<input type='radio' name='category' value='new' checked> new");
				
		String dinamycTable = DinamiTableCreater.fillTable(myTable);
				
		model.addAttribute("categories", dinamycTable);
		//category
				
		if(users.doUserLogged(request.getSession(false).getId())){
			User user = users.getUserBysesion(request.getSession(false).getId());	
			model.addAttribute("sign", "<a href='/fsd/user?name='" + user.getName() + ">" + user.getName() + "</a>");
		}
		else
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
		
			return "question";
	}
	
	//@RequestMapping(value = "/")
	@RequestMapping(value = "/")
	public String home(
			Locale locale, 
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "category", defaultValue = "") String category) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		
		//questions
		ArrayList <ArrayList <String>> myTable = dataBase.getAllquestions(category); 
		
		String userLocal= "";
		for(ArrayList <String> row : myTable){
			try{
				userLocal = row.get(4);
				row.set(4, "<a href=" + sign + "/fsd/user?name=" + userLocal + sign + ">" + userLocal + "</a>");
				row.set(0, "<a href=" + sign + "/fsd/answer?"
						+ "question=" + row.get(0) 
						+ "&category="+ row.get(2)
						+ "&date="+ row.get(3)
						+ "&user=" + userLocal
						+ "&id=" + row.get(5)
						+ sign + ">" + row.get(0) + "</a>");
				
			}catch(IndexOutOfBoundsException e){System.out.println("we skip some notes ");}
		}
		
		ArrayList <String> head = new ArrayList <String>();
		
		head.add("Losted stuffs");
		head.add("Answeres");
		head.add("Categories");
		head.add("When was losted");
		head.add("Whos stuffs");
  
		myTable.set(0, head);
		
		String dinamycTable = DinamiTableCreater.fillTable(myTable);
		
		model.addAttribute("table", dinamycTable);
		//questions
		
		//category
		myTable = dataBase.getAllCategories();
		
		ArrayList<String> at = myTable.get(0);
		
		
		for (int i = 0; i < at.size(); i += 2) {
			try{
				at.set(i, "<a href=" + sign + "/fsd?category=" + at.get(i) + sign + ">" + at.get(i) + "</a>" + "<br>");		
			}catch(IndexOutOfBoundsException e){System.out.println("we skip some notes ");}
		}
		
		dinamycTable = DinamiTableCreater.fillTable(myTable);
		
		model.addAttribute("categories", dinamycTable);
		//category
		try {
			
			if(users.doUserLogged(request.getSession(false).getId())){
				User user = users.getUserBysesion(request.getSession(false).getId());
				model.addAttribute("sign", "<a href='/fsd/user?name=" + user.getName() + "'>" + user.getName() + "</a>");
			}else
				model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
			
			
			
			
			
			
		} catch (Exception e) {
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
		}
		
		return "home";
	}
	
	//@RequestMapping(value = "/user")
	@RequestMapping(value = "/user")
	public String user(
			Locale locale, 
			Model model, 
			@RequestParam(value = "name", defaultValue = "") String userName,
			HttpServletRequest request) {	

		// proverka vhodyashih parametrov
		
		if(!(userName != null) || (userName.isEmpty())) //esli peredan nikto to vozvrashayemsya v home
			return "redirect:/";
		
		// proverka vhodyashih parametrov
		
		if(users.doUserLogged(request.getSession(false).getId())){					//if user logged
			User user = users.getUserBysesion(request.getSession(false).getId());
			model.addAttribute("sign", "<a href='/fsd/user?name=" + user.getName() + "'>" + user.getName() + "</a>");// otobrazhaem ego imya
			model.addAttribute("editView","<a href='/fsd/logOut'>" + "log out" + "</a>"); // edit
			model.addAttribute("form","<form th:action=" + sign + "@{/olo}" + sign + " method=" + sign + "post" + sign + "> <p><label> user name : <input type=" + sign + "text" + sign + " name=" + sign + "username" + sign + "/> </label></p> <p><label> email : <input type=" + sign + "text" + sign + " name=" + sign + "text" + sign + "/> </label></p> <p><input type=" + sign + "submit" + sign + " value=" + sign + "Save" + sign + "/></p></form>");
		}
		else{
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
			model.addAttribute("editView","do  nothing");
			model.addAttribute("form","<h2>username:" + userName + "</h2> <h2>email:" + "matthewb" + "</h2>");//get user email
		}
		
		model.addAttribute("userName","<h>" +  userName + "'s profile</h>");
		
		
		return "user";
	}
	
	
	@RequestMapping(value = "/logOut")
	public String logOut(HttpServletRequest request) {	
		//udalyaem sesiy		
		users.deleteSesion(request.getSession(false).getId());
		
		return "redirect:/";
	}

	
	
	//@RequestMapping(value = "/answer")
	@RequestMapping(value = "/answer")
	public String answer(
			Locale locale, 
			Model model, 
			@RequestParam(value = "question", 	defaultValue = "error") String question,
			@RequestParam(value = "category", 	defaultValue = "error") String category,
			@RequestParam(value = "date", 		defaultValue = "error") String date,
			@RequestParam(value = "user", 		defaultValue = "error") String userLocal,
			@RequestParam(value = "id", 		defaultValue = "0"	  ) int    id,
			HttpServletRequest request) {	

		if(users.doUserLogged(request.getSession(false).getId())){
			User user = users.getUserBysesion(request.getSession(false).getId());	
			model.addAttribute("sign", "<a href='/fsd/user?name=" + user.getName() + "'>" + user.getName() + "</a>");
		}
		else
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");
		
		//add question to answer.jsp		
		ArrayList<ArrayList<String>> all= new ArrayList<ArrayList<String>>();
		ArrayList <String> questionRow = new ArrayList <String>();
		questionRow.add("<h2>" + question + "<h2>");
		all.add(questionRow);
		questionRow = new ArrayList <String>();
		questionRow.add("<br>" + category);
		questionRow.add("&#9&#9&#9&#9");
		questionRow.add(date);
		questionRow.add("<a href=" + sign + "/fsd/user?name=" + userLocal + sign + ">" + userLocal + "</a>");
		all.add(questionRow);
		model.addAttribute("question", DinamiTableCreater.fillTable(all));
		//add question to answer.jsp
		
		//add answeres
		
		all = dataBase.getAnswerByIDQuestion(id);
		
		for(ArrayList<String> row : all){
			try{
				userLocal = row.get(2);
				row.set(2, "<a href=" + sign + "/fsd/user?name=" + userLocal + sign + ">" + userLocal + "</a>");
					/*row.set(0, "<a href=" + sign + "/fsd/answer?"
						+ "question=" + row.get(0) 
						+ "&category="+ row.get(2)
						+ "&date="+ row.get(3)
						+ "&user=" + userLocal
						+ "&id=" + row.get(5)
						+ sign + ">" + row.get(0) + "</a>");*/
				
			}catch(IndexOutOfBoundsException e){System.out.println("we skip some notes {answere}");}
			
		}
		model.addAttribute("table", DinamiTableCreater.fillTable(all));
		//add answeres
		
		// send question id
		model.addAttribute("questionId", id);
		// send question id
		
		return "answer";
	}
	
	//@RequestMapping(value = "*")
	@RequestMapping(value = "*")
	@ResponseBody
	public String defaultt(Locale locale, Model model) {
			return "<h2>no data found<h2>";
	}
	
	//@RequestMapping("/olo")
	@RequestMapping("/olo")
	public String olo(Locale locale, Model model) {
		logger.info("we into olo", locale);
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		return "olo";
	}
	
	//@RequestMapping("/add")
	@RequestMapping("/add")
	public String add(Model model, @RequestParam(value = "firstName") String firstName){
		System.out.println(firstName);
		
		model.addAttribute("path", firstName);
		
		return "user";
	}
	
	//@RequestMapping(value= "/loginIng", method = RequestMethod.POST)
	@RequestMapping(value= "/loginIng", method = RequestMethod.POST)
	public String loginPost(
			HttpServletRequest request,
			@RequestParam(value="login") String login, 
	        @RequestParam(value="password") String password){
		
		System.out.println(login);
		System.out.println(password);
		
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		
		if(dataBase.doUserExist(login, password) != null){
			
			System.out.println("session id: " + request.getSession(false).getId());
			
			User user = dataBase.doUserExist(login, password); //logined
			user.SetSession(request.getSession(false));//got sesion
			
			users.putUser(request.getSession(false).getId(), user);
						
			System.out.println(login);
			System.out.println(password);
			return "redirect:/";
		}
		
		return "login";
  		
	}

	//@RequestMapping(value = "/login")
	@RequestMapping(value = "/login")  // for hadling get
	public String loginGet(Model model, HttpServletRequest request){
		
		if(users.doUserLogged(request.getSession(false).getId())){
			User user = users.getUserBysesion(request.getSession(false).getId());	
			model.addAttribute("<a href='/fsd/user?name='" + user.getName() + "'> + '" + user.getName() + "' </a>");
		}
		else
			model.addAttribute("sign", "<a href='/fsd/login'> login/Sign up </a>");		
		return "login";
	}
	
	//@RequestMapping(value= "/SignUp", method = RequestMethod.POST)
	@RequestMapping(value= "/SignUp", method = RequestMethod.POST)
	public String signUP(
			HttpServletRequest request,
			@RequestParam(value="login") 	String login, 
		    @RequestParam(value="email") 	String email,
			@RequestParam(value="password") String password,
			@RequestParam(value="name") 	String name){
			
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
					
		dataBase.addUser(name, login, email, password);
		System.out.println("added new user" + login +  " " + name);
		
		User user =  new User(login, name);
		user.SetSession(request.getSession(false));//got sesion
		
		users.putUser(request.getSession(false).getId(), user);
		
		return "redirect:/";
	}

	//@RequestMapping(value= "/answer", method = RequestMethod.POST)
	@RequestMapping(value= "/answer", method = RequestMethod.POST)
	public String answere(
			HttpServletRequest request,
			@RequestParam(value="Text", defaultValue = "") 			String text,
			@RequestParam(value="questionId", defaultValue = "") 	int questionId){
		
		// Proverka vhodnih parametrov
		if(text.isEmpty())
			return "answer";
		// Proverka vhodnih parametrov
		
		if (!(dataBase != null)) // if dataBase is null
			dataBase = new Database();
		
		//get user
		User usr = users.getUserBysesion(request.getSession(false).getId());
		
		if (usr == null) return "login"; 
		
		int userId = dataBase.getUserId(usr.getName());
		
		if (userId == 0) System.out.println("user not exist");
		//get user
							
		
		dataBase.addAnswer(text, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), questionId, userId);
		System.out.println("we added " + questionId + " " + text + " " + userId);
		return "redirect:/";
	}

}
