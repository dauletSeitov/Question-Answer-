<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	    ${userName} 
	</div>
	
	<div id="element2">
		${editView}
	</div>
			
	<div id="element3"></div>
	
	<hr/>
	
	<img src="<c:url value="/resources/img/defult_picture.jpg" />" width="255" height="255" />
			${form} 

</body>
</html>
