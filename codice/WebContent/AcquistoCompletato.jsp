<%@page import="java.sql.Date"%>
<%@page import="model.ServiziUtente.OrderData"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="model.Amministratore.PaymentData"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/AcquistoCompletato.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acquisto Completato</title>
</head>
<body>
<%@include file="barra.jsp" %>

<h1 class="titolo col-12 col-m-12"> Acquisto Completato </h1>
<div class="OrdineCompletato col-10 col-m-12"> 

<p class="h1"> Prodotti acquistati:</p>
<%
Collection<?> prodotti = (Collection<?>)request.getAttribute("prodotti");
if(prodotti!=null&& prodotti.size()!=0){
	 
    Iterator<?> itera=prodotti.iterator();
     while(itera.hasNext()){
	 ProdottiData prodotto=(ProdottiData)itera.next();
	%>

<div class="ProdottoAcquistato col-10 col-m-10">
     <div class="imgP col-4 col-m-4">
	<img alt="img" height="20px" class="ImmagineP" src="<%=prodotto.getImmagine()%>" >
	</div>
	<div class="nome_Descrizion_P col-6 col-m-6">
		<p class="NomeP"> <%=prodotto.getNome() %> </p>
		<p class="descrizioneP"><%=prodotto.getDescrizione() %> </p>
	</div>	
	</div>
<%}} %>



<%ShipmentData spedizione= (ShipmentData)request.getAttribute("spedizione"); 
Calendar c = Calendar.getInstance();
%>
<div class="col-12 col-m-12">
<p class="dettagli col-5 col-m-5">Consegna prevista per il:<br>
<%=c.get(Calendar.DAY_OF_MONTH)+spedizione.getGiorni()%>/<%=c.get(Calendar.MONTH)%>/<%=c.get(Calendar.YEAR)%></p>

<%Double totale= (Double)request.getAttribute("totale");%>

<p class="Totale col-5 col-m-5"> Totale: <br><%=Math.floor(totale * 100.0) / 100.0%>€
</div>
</div>
<%@include file="footerHome.jsp"%>
</body>
</html>