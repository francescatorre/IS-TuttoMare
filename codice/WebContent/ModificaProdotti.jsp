<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="model.Amministratore.ProdottiDataDao"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<%
	Collection<?> prodotti= (Collection<?>)request.getAttribute("prodotti");
	

%>  




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modifica Prodotti</title>
<link rel="stylesheet" type="text/css" href="css/ModificaCSP.css">
</head>

<body>
<%@include file="barra.jsp" %>
<section class="Sezione_modifica col-12 col-m-12 ">
<div class="TitoloM col-12 col-m-12">

  <h1> MODIFICA PRODOTTI</h1>
 </div>

<table class="tabella col-12 col-m-12">

<tr> 
    <th>NOME</th>
     <th>DESCRIZIONE</th>
     <th>MARCA </th>
     <th> IMMAGINE</th>
     <th>PREZZO</th>
     <th> PESO IN KG </th>
     <th> QUANTITA' </th>
     <th> SOTTO CATEGORIA </th>
     <th> MODIFICA </th>
     
<tr>


<% if(prodotti!=null&& prodotti.size()!=0){
    	 
    	     Iterator<?> it=prodotti.iterator();
    	      while(it.hasNext()){
    		  ProdottiData bean=(ProdottiData)it.next();
    	  %>
    	
    	<tr> 
    	   <td> <%=bean.getNome()%> </td>
    	   <td> <%=bean.getDescrizione()%> </td>
    	   <td> <%=bean.getMarca()%> </td>
    	   <td> <img class="iconaA" src="<%=bean.getImmagine()%>" ></td>
    	   <td> <%=bean.getPrezzo()%> </td>
    	   <td> <%=bean.getPeso()%> </td>
    	   <td>  <%=bean.getQuantita()%> </td>
    	   <td>  <%=bean.getSottoCategoria()%> </td>
    	   <td><a href="ModificaProdottoS?action=cancella&id=<%=bean.getIdProdotto()%>">Rimuovi Prodotto</a> <br> <br>
    	   <a href="ProdottiControl?action=modifica&id=<%=bean.getIdProdotto()%>">Modifica Prodotto</a>
    	   
    	   </td> 
		   
    	
    	<%
				}
			} 
		%>

</table>
</section>
<%@include file="footerHome.jsp" %>

</body>
</html>