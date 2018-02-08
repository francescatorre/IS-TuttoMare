<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.ProdottiDataDao"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="java.util.Collection"%>  

    
<%
	
	ProdottiData prodotto_dettagli =(ProdottiData) request.getAttribute("prodotto_dettagli");
   Collection<?> prodottiSC= (Collection<?>)request.getAttribute("Prodotti_SC");

%> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scheda Prodotto</title>
<link rel="stylesheet" type="text/css" href="css/SchedaProdotto.css">
</head>
<body>
<%@include file="barra.jsp" %>
<% if(prodotto_dettagli!=null){

%>
<section class="DettagliProdotto col-12 col-m-12">

<h2 class="nomeP col-12 col-m-12">  <%=prodotto_dettagli.getNome()%>(<%=prodotto_dettagli.getMarca()%>) </h2>

<div class="contenitore_imgP col-5 col-m-5"> 
<img class="imgP" src=" <%=prodotto_dettagli.getImmagine()%>">
</div>


<div class="compra col-4 col-m-4">
<p class="prezzoP"> € <%=prodotto_dettagli.getPrezzo()%> </p>
<%if(prodotto_dettagli.getQuantita()>0){  %>
<p class="quantitaP"> Ancora <%=prodotto_dettagli.getQuantita()%> pezzi diponibili.Affrettati! </p>
<p> <a href="#descrizione"> Leggi la descrizione </a></p>
<p> <a href="#Spedizioni" onclick="infoSpe()"> Info Spedizioni </a></p>
<form action="AcquistoDiretto" class="FormCS" method="get">
<input type="hidden" name="id" value="<%=prodotto_dettagli.getIdProdotto()%>">
<input type="submit" value="Compralo Subito" name="compra" class="Compra"> <br><br>
</form>
<%}else if(prodotto_dettagli.getQuantita()==0) { %>
<p class="prezzoP">Prodotto non disponibile!</p>
<p> <a href="#descrizione"> Leggi la descrizione </a></p>
<p> <a href="#Spedizioni" onclick="infoSpe()"> Info Spedizioni </a></p>
<%} %>
</div>

<aside class="aside col-3 col-m-3">
<p> Venduto e Spedito da Tutto Mare </p>
<p class="prezzoPR"> € <%=prodotto_dettagli.getPrezzo()%> </p><br>

<form action="CarrelloServlet" method="get">
  <label class="label "for="quantita" > Quantita </label>
  <input class="input" type="number" name="quantita" min="1" maxlength="1" required value="1"><br>
  <input type="hidden" name="id" value="<%=prodotto_dettagli.getIdProdotto()%>">
  <%if(prodotto_dettagli.getQuantita()>0){  %>
  <input type="submit" value="Aggiungi al Carrello" name="aggiungiC" class="AggiungiC">
  <%}else{ %>
   <input type="submit" value="Aggiungi al Carrello" disabled="disabled" name="aggiungiC" class="AggiungiC">
   <%} %>
</form>

</aside>

<div class="Descrizione col-12 col-m-12">
<div class="barradescrizione col-12 col-m-12">
<h2> Descrizione</h2>
</div>
<a name="descrizione"></a>
<p class="DescrizioneProdotto"> <%=prodotto_dettagli.getDescrizione()%> </p>
<p class="peso"> <strong>PESO IN KG:</strong><%=prodotto_dettagli.getPeso()%> </p>
</div>

<div class="barradescrizione col-12 col-m-12" id="divS" style="display:none;">
<a name="Spedizioni"></a>
  <h1> TIPI DI SPEDIZIONI </h1>
 </div>

<section class="VisualizzaSpedizioni col-12 col-m-12" id="sectionS" style="display:none;">
<p class="Spedizioni"> Tipi di spedizoni disponibili per questo prodotto: </p>
<table class="tabellaSpedizioni col-8 col-m-8">
<tr>
 <th> Tipo spedizione </th>
 <th> Nome Corriere </th>
 <th> Costo Spedizione </th>
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
	<td>	<%=data.getCosto()%> €</td>	
</tr>	


<%}} %>
</table>

</section>


</section>

<%} %>
<section class="ProdottiCorrelati col-12">

  <div class=" divH1 col-12">
  <h1> PRODOTTI CORRELATI</h1>
 </div>
 
<table class="prodottiC col-12 ">
   <tr class="col-12 ">
<% if(prodottiSC!=null&& prodottiSC.size()!=0){
    	 
    	     Iterator<?> it=prodottiSC.iterator();
    	      while(it.hasNext()){
    		  ProdottiData bean=(ProdottiData)it.next();
    		  
    		  if(prodotto_dettagli.getIdProdotto()!=bean.getIdProdotto()){ 
    	  %>

		<td class="P col-3 ">
				    
				 <span class="Marca"><a href="SchedaProdotto?action=dettagli&id=<%=bean.getIdProdotto()%>&sc=<%=bean.getSottoCategoria()%>" > <%=bean.getNome()%>(<%=bean.getMarca()%>)</a> </span><br>
				 <br> <img class="fotoProdotto" src="<%=bean.getImmagine()%>"
					alt="fotoProdotto"><br> <span class="Prezzo">
						<%=bean.getPrezzo()%> €</span> 
						
			
<%}} %>
           </td>
            </tr>

		</table>						
<% }else if( prodottiSC.size()==1 ){%> 

<p class="col-3 col-m-3"> Nessun Prodotto correlato.</p>

<%} %>

</section>

<script> 
 function infoSpe(){
	 var div = document.getElementById("divS");
	 var section = document.getElementById("sectionS");
	 section.style.display="inline";
	 div.style.display="inline";
 }

</script>
<%@include file="footerHome.jsp" %>
</body>
</html>

