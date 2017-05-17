<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Where's my stuff ???</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <style type="text/css">
        #element1 {float:left}
        #element2 {float:right;}
        #element3, .clear {clear:both;}
        #label_title2{
        margin-right:87px;
        }
                                                                              
        #label_title3{
        margin-left 50px;
        }
                                                                              
        #ul_checbox li{
        list-style-type: none;
        }
        #ul_checbox1{
        
        float:right;
        margin:0;
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
                    
	<div>
		${question}      
		<hr/>
		${table}
	</div>	
	
	<div id="element3"></div>
	
	<br>
	<h>Your ansewr</h>
    <form action="/fsd/answer" method="post">
		<textarea name="Text" cols="80" rows="8"></textarea>
		<input type="hidden" value="${questionId}" name="questionId" />
		<br>
		<input type="submit" value="post"/>
    </form>
      

</body>
</html>
