

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
	<style type="text/css">
    table {
        border-collapse: collapse;
    }
    table th,
    table td {
        padding: 0 3px;
    }
    table.brd th,
    table.brd td {
        border: 1px solid #000;
    }
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
	    <H>Categories: </H> 
	</div>
	
	<div id="element1">
		<H1>&nbsp;&nbsp;</H1>
	</div>
	
	<div id="element1">
		${categories} 
	</div>
	
	
	<div id="element2">
	    
    <form action="/fsd/question" method="post">
    	    <!--<p><label> User Name : <input type="text" name="username"/> </label></p>
            <p><label> Password: <input type="password" name="password"/> </label></p>-->
            <p><input type="submit" value="Ask question"/></p>
    </form> 
		 
	</div>
	
	<div id="element3"></div>
${table}
</body>
</html>
