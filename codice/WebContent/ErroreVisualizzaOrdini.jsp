<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/Errore.css">
<title>Impossibile accedere ai tuoi acquisti</title>
</head>
<body>
<%@include file="barra.jsp" %>

<div class="Errore col-10 col-m-10">
<h1 class="TitoleErr"> Errore!</h1>
<p class=DescrizioneErr> Per accedere ai tuoi acquisti  devi effettuare il login. <br>Accedi! </p>
  
  </div>
<%@include file="footerHome.jsp"%>
</body>
</html>