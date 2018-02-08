"WebContent/jsp/formLogin.jsp"<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/loginStyle.css">

</head>
<body>


    <div class="inputForm">
      <label><b>Username</b></label>
      <input class="input" type="text" placeholder="Enter Username" name="usernameLogin" required>

      <label><b>Password</b></label>
      <input class="inputPassword" type="password" placeholder="Enter Password" name="pswLogin" required>
        
      <button class="buttonForm" type="submit">Login</button>
      <input class="inputCheckbox" type="checkbox" checked="checked"> Remember me
		
		<p>Non possiedi un account?</p>
      <button class="buttonRegistrati" type="submit" onclick="avvioRegistrazione()">Registrati</button>

    </div>

    <div class="inputForm" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('divForm').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>


</body>
</html>