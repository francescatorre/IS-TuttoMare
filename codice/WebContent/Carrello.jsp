<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Account.UserData"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiziUtente.CarrelloData"%>
<%@page errorPage="ErroreCarrello.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/Carrello.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrello</title>
</head>

<%
ArrayList<ProdottiData> listaProdotti = (ArrayList<ProdottiData>)request.getAttribute("showProduct");
%>

<body>
<%@include file="barra.jsp" %>

<h1 class="Carrello col-10 col-m-10"> Carrello(<%=listaProdotti.size()%>)</h1>
<form class="FormProdottiC col-10 col-m-11" action="acquistaProdotto" method="get">
<%
    float totale=0;
	for(ProdottiData data: listaProdotti){
    totale =totale+ ( data.getQuantita()* data.getPrezzo());
%>
	
	<div class="ProdottoCarrello col-12 col-m12">
	<div class="imgP col-3 col-m-3">
	<img  class="ImmagineP" alt="img" height="20px" src="<%=data.getImmagine()%>" >
	</div>
	<div class="nome_Descrizion_P col-5 col-m-5">
		<p class="NomeP"> <%=data.getNome() %> </p>
		<p class="descrizioneP"><%=data.getDescrizione()%> </p>
	</div>	
	
	<div class="quantita_P col-2 col-m-2">
		<p class="Quantita">Quantita <br> <%=data.getQuantita()%> </p>
	<a id="plus" href="ModificaQuantita?id=<%=data.getIdProdotto()%>&quantita=<%=data.getQuantita()%>">+</a>
	<a id="meno" href="ModificaQuantita?id=<%=data.getIdProdotto()%>&quantita=<%=data.getQuantita()%>&meno=true">-</a>
	</div>
	
	<div class="Prezzo_P col-2 col-m-2">
		<p class="Prezzo"> <span>Prezzo </span> <br>
		<%=Math.floor(data.getPrezzo()*data.getQuantita() * 100.0) / 100.0%> EURO</p>
		 <input type="submit" class="cancella "name="cancella" value="<%=data.getIdProdotto() %>">
    </div>
    
</div>
<br>
<%}%>
 <hr>
 <p class="Totale"> TOTALE:<br><%=Math.floor(totale * 100.0) / 100.0%>EURO </p> 
 <input type="submit" name="acquista" value="acquista" class="btnAcquista col-11">  


</form>
</body>
<%@include file="footerHome.jsp"%>
</html>