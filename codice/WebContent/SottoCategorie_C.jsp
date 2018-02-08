<%@page import="model.Amministratore.SottoCatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<%
	Collection<?> SottoCategorie_C= (Collection<?>)request.getAttribute("SottoCategorie_C");
    String nomeCategoria=(String)request.getAttribute("nomeCategoria");
%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/SottoCategorie_C.css">
<title> Scegli una categoria di prodotti</title>
</head>
<body>
<%@include file="barra.jsp" %>

	<div class=" BarraCat col-12 col-m-12 ">
		<h1 class="nomeCat">
			TIPOLOGIE DI
	      <%=nomeCategoria%></h1>
	</div>

	<section class="SottoCategorie col-12 col-m-12">


		<%
			if (SottoCategorie_C != null && SottoCategorie_C.size() != 0) {

				Iterator<?> it = SottoCategorie_C.iterator();
				while (it.hasNext()) {
					SottoCatData bean = (SottoCatData) it.next();
		%>
		<div class="col-3 col-m-3">
			<h1 class="titoloSCategoria" style="text-transform: uppercase;">
				<a class="linkP" href="ProdottiSC_Servlet?action=visualizza&id=<%=bean.getIdSottoCategoria()%>"> 
				<%=bean.getNomeSottoCategoria()%> </a>
			</h1>
             <p class="DescrizioneSC"> <%=bean.getDescrizione() %> </p>
	
		</div>
		<%
			}
			}
		%>
	</section>

	<%@include file="footerHome.jsp" %>
</body>
</html>