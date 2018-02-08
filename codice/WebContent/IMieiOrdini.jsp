<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="model.Account.AddressData"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%> 
<%@page import="model.ServiziUtente.OridiniEffettuati"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/IMieiOrdini.css">
<title>I miei ordini</title>
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

 %>
 <% 
 ArrayList<ProdottiData> prodottiOrdine= lista.getProdottiComprati();
 if(prodottiOrdine.size()!=0){ %>
 
<div class="divOrdine col-11 col-m-12"> 
<h3 class="h3"> Prodotti:</h3>
  
  
   <% 
  
     for(int i=0; i<prodottiOrdine.size();i++){
   
   %>

<div class="PrdodottiOrdine col-12 col-m-12">
    
    <span class="nomeP col-6 col-m-6" > <%=prodottiOrdine.get(i).getNome()%> </span>
    <span class="quantitaP col-3 col-m-3"> Quantit&agrave;:<%=prodottiOrdine.get(i).getQuantita()%> </span> 
    <span class="PrezzoP col-3 col-m-3"> Prezzo:<%=prodottiOrdine.get(i).getPrezzo()%>€ </span>
    <span class="descrizioneP col-6 col-m-6"><%=prodottiOrdine.get(i).getDescrizione()%> </span>
    <div class="col-12 col-m-12">
    <img class="imgP col-2 col-m-2" src="<%=prodottiOrdine.get(i).getImmagine()%>"> 
    </div>
    
    
</div>    
<%} %>

<div class="Dettagli ordine col-12 col-m-12"> 

    
     <p class="Data col-2 col-m-2 ">Consegna Prevista per il:<bR><span class="span"><%=lista.getDataOridne()%> </span> </p> 
     <p class="StatoO col-2 col-m-2" >Stato ordine: <bR><span class="span"><%=lista.getStatoOrdine() %> </span> </p>
 
   
     <%
       ShipmentData spedizione= lista.getMetodoSpedizone();%>
        <p class="metodoSpedizione col-m-3 col-3"> Metodo Spedizone:<bR><span class="span"><%=spedizione.getTipoSpedizione()%> </span>
        <p class="metodoSpedizione col-m-3 col-3"> Nome Corriere:<bR> <span class="span"> <%=spedizione.getNomeCorriere()%></span>
         <p class="totaleO col-m-2 col-2"> Totale ordine: <bR> <span class="span"><%=lista.getTotateoridne()+spedizione.getCosto()%>€ </span></p>
     <%} %>  
 </div>
 </div>
<%}} %>
</div>

<%@include file="../footerHome.jsp" %>
</body>
</html>