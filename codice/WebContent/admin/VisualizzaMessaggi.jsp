<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="model.ServiziUtente.ContattaciData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>    
    
<%
	Collection<?> messaggi= (Collection<?>)request.getAttribute("messaggi");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Visualizza Messaggi</title>
<link rel="stylesheet" type="text/css" href="css/VisualizzaMessaggi.css">
</head>
<body>
<%@include file="../barra.jsp" %>

	<div class="Messaggi col-12 col-m-12">
		<h1 class="TitoloM">Messaggi dei Clienti</h1>


		<%
			if (messaggi != null && messaggi.size() != 0) {

				Iterator<?> it = messaggi.iterator();
				while (it.hasNext()) {
					ContattaciData bean = (ContattaciData) it.next();
		%>
		<div class="DivMessaggio col-10 col-m-12">
			<p class="Nome">
				<span class="TT">Nome Cliente: </span>
				<%=bean.getNome()%></p>
			<p class="Cognome">
				<span class="TT">Cognome Cliente: </span><%=bean.getCognome()%></p>
			<p class="Email">
				<span class="TT">Email:</span>
				<%=bean.getEmail()%>
			</p>
			<p class="Messaggio">
				<span class="TT"> Messaggio: <br></span><%=bean.getCommento()%>
			</p>
			<a class="Elimina"
				href="VisualizzaMessaggiCliente?action=elimina&idMessaggio=<%=bean.getIdMessaggio()%>">
				Elimina Messaggio</a>
		</div>
		<%
			}
			}
		%>

	</div>
	<%@include file="../footerHome.jsp" %>

</body>
</html>