
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script>

$(document).ready(function(){
    $("#homePage").show();
    $("#createPage").hide();
    $("#deletePage").hide();
    $("#updatePage").hide();
    
    $("#home").click(function(){
        $("#homePage").show();
        $("#createPage").hide();
        $("#deletePage").hide();
        $("#updatePage").hide();
     });
    $("#create").click(function(){
        $("#homePage").hide();
        $("#createPage").show();
        $("#deletePage").hide();
        $("#updatePage").hide();
     });
    $("#delete").click(function(){
        $("#homePage").hide();
        $("#createPage").hide();
        $("#deletePage").show();
        $("#updatePage").hide();
        $('#delete_form').submit(function(){
        	deleteData($('.ids').val());
        	return false;
        });
        function deleteData(ids){
            $.ajax({
                    type: "DELETE",
                    url: ids,
                    success: function (data, status, jqXHR) {
                        alert("deleted");
                    },
                    error: function (jqXHR, status) {
                        console.log(status);
                        alert('failed, The URL incorrect');
                    }
                 });
           }

     });
    $("#update").click(function(){
        $("#homePage").hide();
        $("#createPage").hide();
        $("#deletePage").hide();
        $("#updatePage").show();
        $('#update_form').submit(function(){
        	updateData($('.id').val());
        	return false;
        });
        function updateData(id){
            $.ajax({
                    type: "PUT",
                    url: id,
                    success: function (data, status, jqXHR) {
                    	alert(data);
                    },
                    error: function (jqXHR, status) {
                        console.log(status);
                        alert('failed, The URL incorrect ');
                    }
                 });
           }
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
    line-height:30px;
    height:600px;
    width:100px;
    float:left;
    padding:15px;         
}
div {
    width:450px;
    float:center;
    padding:10px;        
}
footer {
    color:white;
    clear:both;
    text-align:center;
    padding:5px;         
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
<button id="create" type="button" style="width: 90px">Shorten URL</button><br>
<br>
<button type="button" id="delete" style="width: 90px">Delete  URL</button><br>
<br>
<button type="button" id="update" style="width: 90px">Update  URL</button><br>
<br>
</nav>


<div id="homePage">
<h1 id="titel"></h1>
<p id="demo">This project for software as server (SaaS) course. I used JAVA on the server based on restful.
<br>
In this project can create short URL, delete it and updete it by regnerate short URL for the same orjginal URL.
<br>
The database is Mongodb on cloud server in mlab.com .
<br>You can get the source code on git hub on this link.
<br>https://github.com/malharbi/shortenerURL 
</p>
</div>

<div id="createPage">
<h1 style="text-align:center;">Create a Shorten URL</h1>
<form method="POST">
Post your long URL here
<input type="text" name="givenURL" size="40"><br/>
<input type="submit" values="Shorten URL">
</form>
</div>

<div id="deletePage">
<h1 style="text-align:center;">Delete a URL</h1>
<form id="delete_form" method="POST">
Enter you Shorten URL to be deleted
<input type="text" class="ids" size="40"><br/>
<input type="submit">
</form>
</div>

<div id="updatePage">
<h1 style="text-align:center;">Update shorten URL</h1>
<form id="update_form" method="POST" >
Enter you shorten URL to be update
<input type="text" class="id" size="40"><br/>
<input type="submit">
</form>
<div id="results"></div>

</div>


<footer>
Copyright © motaz2.herokuapp.com
</footer>

</body>
</html>