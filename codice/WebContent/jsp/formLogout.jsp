<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/loginStyle.css">

</head>
<body>



<div id="divForm" class="classDivForm">
  
 <form id="logoutForm" action="loginServlet" method="get">

	<button id="logout" type="submit">Logout!</button>
	<input type="hidden" name="action" value="logout"/>
</form>
</div>

</body>
</html>