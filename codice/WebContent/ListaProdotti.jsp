<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.ProdottiDataDao"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.Collection"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>	
<%@page import="model.Amministratore.CategoriaData"%>     

<%
	Collection<?> prodottiSC= (Collection<?>)request.getAttribute("Prodotti_SC");
    Collection<?> prodottiFiltri= (Collection<?>)request.getAttribute("prodotti_filtri");
    Collection<?> categorie= (Collection<?>)request.getAttribute("categorie");
    ArrayList<?> Marche= (ArrayList<?>)request.getAttribute("Marche");
    Collection<?> sottoCategorie= (Collection<?>)request.getAttribute("sottoCategorie");
    Collection<?> ricercati= (Collection<?>)request.getAttribute("ricercati");
	
%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista Prodotti</title>
<link rel="stylesheet" type="text/css" href="css/ListaProdotti.css">
<link rel="stylesheet" type="text/css" href="css/HomePagee.css">
</head>
<body>
<%@include file="barra.jsp" %>


<form class="form col-12 col-m-12" name="Filtri">
<input type="hidden" name="action" value="filtra">

 Prezzo da euro <span id="p_min"></span><input type="range" min="1" max="4000" id="prezzo_min" name="prezzo_min" class="range"  onchange="prezzoRange()">
  <input id="prezzo_max" name="prezzo_max" type="range" max="10000" class="range" onchange="filtraPrezzo()"> a euro <span id="p_max"></span> <br class="br">
<input type="hidden" name="prezzo" value="false" id="prezzo">

<label for="Marca" class="Marca_filtri"> Marca</label>
<select name="marche" id="Marca" class="select_marca" onchange="filtraMarca()">
 <% if(Marche!=null && Marche.size()!=0){
	 for(int i=0; i< Marche.size(); i++){
		 String m= Marche.get(i).toString();
	 
 %>
 
 <option  value="<%=m %>"> <%=m %> </option>
 <% }%>
 </select> <br class="br">
<% } %>
<input type="hidden" name="marca" value="false" id="marca">

 Peso da kg<span id="pe_min"></span><input id="peso_min" name="peso_min" type="range" min="1" class="range"  onchange="pesoRange()">
 <input type="range" min="1" name="peso_max" class="range" id="peso_max"  onchange="filtraPeso()"> a kg <span id="pe_max"></span> <br class="br">
 <input type="hidden" name="peso" value="false" id="peso">
</form>

<script >
function prezzoRange(){
	var p_min = document.getElementById("p_min");
	 p_min.innerHTML =document.getElementById("prezzo_min").value;
	
	var e=document.getElementById("prezzo_max");
	e.setAttribute("min", document.getElementById("prezzo_min").value)
   
	 
}

function filtraPrezzo(){
	var p_min = document.getElementById("p_min");
	var p_max=document.getElementById("p_max");
	 p_max.innerHTML =document.getElementById("prezzo_max").value;
	 
	 var x = document.getElementById("prezzo");
	 x.setAttribute("value","true");
	if(p_min!=0 && p_max!=0 && p_min!=p_max){
		document.Filtri.action = "ProdottiSC_Servlet";
        document.Filtri.submit();
	}
}	 

function filtraPeso(){
	var pe_min = document.getElementById("pe_min");
	var pe_max=document.getElementById("pe_max");
	 pe_max.innerHTML =document.getElementById("peso_max").value;
	 
	 var x = document.getElementById("peso");
	 x.setAttribute("value","true");
	if(pe_min!=0 && pe_max!=0 && pe_min!=pe_max){
		document.Filtri.action = "ProdottiSC_Servlet";
        document.Filtri.submit();
	}
}	 
function filtraMarca(){
	
	var input=document.getElementById("marca");

	 input.setAttribute("value","true");
		document.Filtri.action = "ProdottiSC_Servlet";
        document.Filtri.submit();

	}

function pesoRange(){
	
	var pe_min = document.getElementById("pe_min");
	 pe_min.innerHTML =document.getElementById("peso_min").value;
	
	var e=document.getElementById("peso_max");
	e.setAttribute("min", document.getElementById("peso_min").value)

}

</script>

<% if(prodottiSC!=null&& prodottiSC.size()!=0){
    	 
    	     Iterator<?> it=prodottiSC.iterator();
    	      while(it.hasNext()){
    		  ProdottiData bean=(ProdottiData)it.next();
    	  %>
 
	<div class="divProdotto col-11 col-m-9">
	<div class="imgP col-3 col-m-2">
		<img class="imgProdotto" src="<%=bean.getImmagine()%>"> 
		</div>
		<div class="i col-6 col-m-4">
		<span class="nomeP"> <a
			href="SchedaProdotto?action=dettagli&id=<%=bean.getIdProdotto()%>&sc=<%=bean.getSottoCategoria()%>">
				<%=bean.getNome()%>(<%=bean.getMarca()%>)</a> <br>
				
		</span><br> <span class="descrizioneP"><%=bean.getDescrizione()%></span><br>
		<p>
		<p>
		<span class="prova"> Consegnato e spedito da TuttoMare</span>
		</div>
		<div class="it col-3 col-m-3">
		<span class="prezzoP">EUR <%=bean.getPrezzo()%></span><p>
		<span class="Consegna"> Consegna gratuita per acquisti superiori a 50 €</span><br>
		<%if(bean.getQuantita()>0){ %>
		<a href="CarrelloServlet?quantita=1&id=<%=bean.getIdProdotto()%>">
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > </a>
		<%} else { %>
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > <br>
        <span class="ProdottoEsaurito"> Prodotto non disponibili!</span>
        <%} %>
        </div>
	</div>

<%}} %>
	
<% if(prodottiFiltri!=null&& prodottiFiltri.size()!=0){
    	 
    	     Iterator<?> it=prodottiFiltri.iterator();
    	      while(it.hasNext()){
    		  ProdottiData bean=(ProdottiData)it.next();
    	  %>
    	  
<div class="divProdotto col-11 col-m-9">
	<div class="imgP col-3 col-m-2">
		<img class="imgProdotto" src="<%=bean.getImmagine()%>"> 
		</div>
		<div class="i col-6 col-m-4">
		<span class="nomeP"> <a
			href="SchedaProdotto?action=dettagli&id=<%=bean.getIdProdotto()%>&sc=<%=bean.getSottoCategoria()%>">
				<%=bean.getNome()%>(<%=bean.getMarca()%>)</a> <br>
				
		</span><br> <span class="descrizioneP"><%=bean.getDescrizione()%></span><br>
		<p>
		<p>
		<span class="prova"> Consegnato e spedito da TuttoMare</span>
		</div>
		<div class="it col-3 col-m-3">
		<span class="prezzoP">EUR <%=bean.getPrezzo()%></span><p>
		<span class="Consegna"> Consegna gratuita per acquisti superiori a 50 €</span><br>
		<%if(bean.getQuantita()>0){ %>
		<a href="CarrelloServlet?quantita=1&id=<%=bean.getIdProdotto()%>">
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > </a>
		<%} else { %>
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > <br>
        <span class="ProdottoEsaurito"> Prodotto non disponibili!</span>
        <%} %>
        </div>
	</div>
<%}} %>
	
<% if(ricercati!=null&& ricercati.size()!=0){
    	 
    	     Iterator<?> it=ricercati.iterator();
    	      while(it.hasNext()){
    		  ProdottiData bean=(ProdottiData)it.next();
 %>
	<div class="divProdotto col-11 col-m-9">
	<div class="imgP col-3 col-m-2">
		<img class="imgProdotto" src="<%=bean.getImmagine()%>"> 
		</div>
		<div class="i col-5 col-m-4">
		<span class="nomeP"> <a
			href="SchedaProdotto?action=dettagli&id=<%=bean.getIdProdotto()%>&sc=<%=bean.getSottoCategoria()%>">
				<%=bean.getNome()%>(<%=bean.getMarca()%>)</a> <br>
				
		</span><br> <span class="descrizioneP"><%=bean.getDescrizione()%></span><br>
		<p>
		<p>
		<span class="prova"> Consegnato e spedito da TuttoMare</span>
		</div>
		<div class="it col-3 col-m-3">
		<span class="prezzoP">€ <%=bean.getPrezzo()%></span><p>
		<%if(bean.getQuantita()>0){ %>
		<a href="CarrelloServlet?quantita=1&id=<%=bean.getIdProdotto()%>">
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > </a>
		<%} else { %>
        <img alt="carrello" class="ACarrello" src="img/icoCarrello.png" id="carrello" > <br>
        <span class="ProdottoEsaurito"> Prodotto non disponibili!</span>
        <%} %>
       
     
        </div>
	</div>
	<%
				}
			} 
else if(prodottiSC==null && prodottiFiltri==null){
		%>
		
<p> Nessun prodotto trovato. </p>
<%} %>

<%@include file="footerHome.jsp"%>
</body>
</html>