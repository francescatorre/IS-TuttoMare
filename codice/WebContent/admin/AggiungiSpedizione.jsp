<%@page import="model.Amministratore.PaymentData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/AggiungiSpedizioni.css">
<title>Aggiungi spedizione</title>
</head>
<body>
<%@include file="../barra.jsp" %>

<div class="barraVisualizza col-12 col-m-12">

  <h1> VISUALIZZA SPEDIZIONI </h1>
 </div>


<span class="Visualizza col-12 col-m-12">
Questi sono i metodi di spedizione già aggiunti e visibili 
al cliente. </span>

<section class="VisualizzaSpedizioni col-12 col-m-12">
<form action="VisualizzaSpeseAdmin" method="post">
<table class="col-12 col-m-12">
<tr>
 <th> Tipo spedizione </th>
 <th> Nome Corriere </th>
 <th> Costo Spedizione </th>
 <th> Giorni necessari per la spedizione </th>
 <th> Elimina </th>
</tr>
<tr>
<%
Collection<?> listaSpedizione = (Collection<?>)request.getAttribute("listaSpedizione");

if(listaSpedizione!=null&& listaSpedizione.size()!=0){	 
    Iterator<?> it=listaSpedizione.iterator();
     while(it.hasNext()){
    	 ShipmentData data =(ShipmentData)it.next();

	%>

	<td>	<%=data.getTipoSpedizione()%> </td>
	<td>	<%=data.getNomeCorriere()%></td>
	<td>	<%=data.getCosto()%> </td>
	<td>	<%=data.getGiorni()%> </td>
	<td>    <input type="submit" name="elimina" value="<%=data.getIdSpedizione()%>"  style="background-image:url('icone/false.png');background-position:center;font-size: 0px; height: 20px;"></td>
	
</tr>	


<%}} %>
</table>
</form>

</section>

<section class="AggiungiSpedizioni col-12 col-m-12">

<div class="barraVisualizza col-12 col-m-12">
  <h1> AGGIUNGI SPEDIZIONI </h1>
 </div>

<span class="Visualizza col-12 col-m-12"> Qui puoi aggiungere i metodi di spedizione </span>

<form action="AggiungiSpeseSpedizione" method="get">
<input type="text" name="tipo" class="inputTe" placeholder="tipo" > <br>
<input type="text" name="nome" class="inputTe" placeholder="nome" > <br>
<input type="text" name=spese class="inputTe"  placeholder="spese"> <br>
<input type="number" name=giorni class="inputTe"  placeholder="giorni"> <br>
<input type="submit" value="conferma" class="Conferma">
</form>

</section>


<%@include file="../footerHome.jsp" %>
</body>
</html>