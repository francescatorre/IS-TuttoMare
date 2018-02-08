<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="model.Account.AddressData"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%> 
<%@page import="model.ServiziUtente.OridiniEffettuati"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/VisualizzaOrdini.css">
<title>Ordini</title>
</head>
<body>
<%@include file="../barra.jsp" %>


<div class="ContenitoreOrdini col-12 col-m-12">
<h1 class="titoloO"> Ordini</h1>
<%Collection<?> ordini= (Collection<?>)request.getAttribute("ordini"); 
 
if(ordini!=null&& ordini.size()!=0){
	 
    Iterator<?> it=ordini.iterator();
     while(it.hasNext()){
	 OridiniEffettuati lista=(OridiniEffettuati)it.next();
	 
	 if(lista.getStatoOrdine().equalsIgnoreCase("Pagato")){
	
 %>
<div class="divOrdine col-11 col-m-12"> 
<h3 class="h3"> Prodotti:</h3>
   <% ArrayList<ProdottiData> prodottiOrdine= lista.getProdottiComprati();
   
     for(int i=0; i<prodottiOrdine.size();i++){
   
   %>

<div class="PrdodottiOrdine col-12 col-m-12">
    
    <span class="nomeP col-6 col-m-6" > <%=prodottiOrdine.get(i).getNome()%> </span>
    <span class="quantitaP col-6 col-m-6"> Quantit&agrave;:<%=prodottiOrdine.get(i).getQuantita()%> </span>
    <img class="imgP col-2 col-m-2" src="<%=prodottiOrdine.get(i).getImmagine()%>"> 
    
</div>    
<%} %>

<div class="Dettagli ordine col-12 col-m-12"> 

    
     <p class="Data col-2 col-m-2 ">Data ordine:<bR><span class="span"><%=lista.getDataOridne()%> </span> </p> 
     <p class="StatoO col-2 col-m-2" >Stato ordine: <bR><span class="span"><%=lista.getStatoOrdine() %> </span> </p>
 
   
     <% AddressData indirizzo= lista.getIndirizzoFatturazione();
       ShipmentData spedizione= lista.getMetodoSpedizone();%>
        <p class="metodoSpedizione col-m-3 col-3"> Metodo Spedizone:<bR><span class="span"><%=spedizione.getTipoSpedizione()%> </span>
        <p class="metodoSpedizione col-m-3 col-3"> Nome Corriere:<bR> <span class="span"> <%=spedizione.getNomeCorriere()%></span>
         <p class="totaleO col-m-2 col-2"> Totale ordine: <bR> <span class="span"><%=lista.getTotateoridne()+spedizione.getCosto()%>€ </span></p>
       
       <h3 class="h3"> Indirizzo di spedizione:</h3>   
     <table class="TabellaIndirizzo col-12 col-m-12">
<tr>
<th>Nome </th>
<th>Cognome </th>
<th>Telefono </th>
<th>Stato</th> 
<th>Provincia</th>
<th>Citt&agrave;</th>
<th>Via </th>
<th>N°civico </th>
<th>Cap </th>
</tr>
<tr>
	<td><%=indirizzo.getNome() %></td>
	<td><%=indirizzo.getCognome() %> </td>
	<td><%=indirizzo.getTelefono() %> </td>
	<td><%=indirizzo.getStato() %> </td>
	<td><%=indirizzo.getProvincia()%> </td>
	<td><%=indirizzo.getCitta() %> </td>
	<td><%=indirizzo.getVia() %> </td>
	<td><%=indirizzo.getNumeroCivico() %></td>
	<td><%=indirizzo.getCap()%></td>
   
</tr>
</table>
    
    
   </div>
   <form action="InviaOrdini" method="post">
   <input type="hidden" name="nOrdine" value="<%=lista.getNumeroOrdine()%>">
   <input class="btnSpedito" type=submit value="Contrassegna come spedito"> 
   </form>
</div>   
  
<%}}} %>

 </div>

<%@include file="../footerHome.jsp" %>
</body>
</html>