<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/Errore.css">
<title>Acquisto non effettuato</title>
</head>
<body>
<%@include file="barra.jsp" %>

<div class="Errore col-10 col-m-10">
<h1 class="TitoleErr"> Errore Acquisto non effettuato!</h1>
<p class=DescrizioneErr>La quantità del prodotto scelta non è disponibile.
   Ci scusiamo per l'inconveniente </p>
  
  </div>
<%@include file="footerHome.jsp"%>
</body>
</html>