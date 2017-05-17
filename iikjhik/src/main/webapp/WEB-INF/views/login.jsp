<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Where's my stuff ???</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <style type="text/css">
	    #element1 {float:left}
	    #element2 {float:right;}
	    #element3 {clear:both;}
    </style>
    
</head>
<body>
	<div id="element1">
		<h2>Where's my stuff ???</h2>
	</div>

	<div id="element2">
		${sign} 
	</div>

    <div id="element3"></div>
    <hr/>
    
    <div id="element1">
	    <h1>login</h1>
	    <form action="/fsd/loginIng" method="post">
    	    <p><label> Login : <input type="text" name="login"/> </label></p>
            <p><label> Password: <input type="password" name="password"/> </label></p>
            <p><input type="submit" value="Sign In"/></p>
        </form> 
	</div>

    <div id="element2">
        <h1>signup</h1>
	    <form action="/fsd/SignUp" method="post">
    	    <p><label> Login : <input type="text" name="login"/> </label></p>
            <p><label> email: <input type="text" name="email"/> </label></p>
            <p><label> name: <input type="text" name="name"/> </label></p>
            <p><label> Password: <input type="password" name="password"/> </label></p>
            <p><input type="submit" value="Sign In"/></p>
        </form> 
	</div>	

    <div id="element3"></div>

</body>
</html>
