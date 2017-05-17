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
                
                <div id="element1">
                               <h>create new question</h>
                </div>
                
                <div id="element3"></div>
    <hr/>
                

    <form action="/fsd/questionPpost" method="post">
    
		<ul id = 'ul_checbox'>
			<li><label id='label_title2'>title <textarea name="Title" cols="80" rows="1"></textarea></label></li>      
			
			<li><label>what it's all about <textarea name="Text" cols="80" rows="8"></textarea></label></li>
			
			
			<li><label>select category</label></li>
			<li>${categories} <textarea name='newCategory'></textarea> </li>
			
			<div class='clear'></div>
			
			<li><input type="submit" value="post your questions"/></li>
		</ul>     
    </form> 

</body>
</html>