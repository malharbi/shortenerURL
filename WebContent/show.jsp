
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script>
base_url="http://localhost:8080/rest/shortenerUrl/";

$(document).ready(function(){
    $("#home").click(function(){
    	window.location="http://localhost:8080/rest/shortenerUrl/"
    	});
});
</script>

<style>
header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;     
}
nav {
    background-color:black;
    line-height:30px;
    height:600px;
    width:100px;
    float:left;
    padding:15px;         
}
div {
    width:450px;
    float:left;
    padding:10px;        
}
</style>
</head>
<body>
<header>
<h1>Shortener URL Project</h1>
</header>

<nav>
<button id="home" type="button" style="width: 90px">home</button><br>
<br>
</nav>
<div>
<a href="${it.url}">${it.url}</a>
</div>


<footer>
</footer>

</body>
</html>