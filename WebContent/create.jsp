<!DOCTYPE html>
<html>
<head>
<style>
#header {
    background-color:blue;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#a2c3ca;
    height:500px;
    width:100px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	
 
}
#footer {
    background-color:blue;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
</style>
</head>
<body>

<div id="header">
<h1>URL Shortener</h1>
</div>

<div id="nav">
<br>
<br>

</div>

<div id="section">
<h2>Paste your URL here</h2>
<form method="POST">
<input type="text" name="givenURL" size="40"><br/>
<input type="submit" values="Shorten URL">
</form>
</div>

<div id="footer">
Copyright © Motaz.com
</div>
</body>
</html>
