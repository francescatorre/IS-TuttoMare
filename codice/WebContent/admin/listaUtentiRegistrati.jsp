<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account.UserData"%>



<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/ListaUtentiRegistrati.css">
<title>Utenti registrati</title>
</head>


<body>
<%@include file="../barra.jsp" %>

<section class="col-12 col-m-12">
<form action="AbilitaAmministratore" method="get">

      <table class="col-12 col-m-12 ListaUtenti">
		<tr>
           <th> Immagine </th>
           <th> Nome </th>
           <th> Cognome </th>
           <th> Email </th>
           <th> Amministratore/Cliente </th>
         </tr>
         <tr>
	<%
		ArrayList<UserData> userList = (ArrayList<UserData>) request.getAttribute("userList");
		for (UserData data : userList) {
	%>
	
	
   
			<td><img class="imgU" alt="img" height="20px" src="<%=data.getAvatar()%>">
			</td>
			<td><%=data.getNome()%></td>
			<td><%=data.getCognome()%></td>
			<td><%=data.getEmail()%></td>
			

			<%
				if (data.isAdmin()) {
			%>

			<td>Amministratore: 
			<input type="submit" name="pulsante" value="<%=data.getEmail()%>" style="background-image: url('icone/false.png'); background-position: center; font-size: 0px; height: 25px;">
			</td>


			<%
				} else {
			%>
			<td>Cliente <input type="submit" name="pulsante"
				value="<%=data.getEmail()%>"
				style="background-image: url('icone/true.jpg'); background-position: center; font-size: 0px; height: 25px;">

			</td>


			<%
				}
			%>

		</tr>

		<%
}
%>
		</table>
	</form>
</section>


<%@include file="../footerHome.jsp" %>
</body>
</html>