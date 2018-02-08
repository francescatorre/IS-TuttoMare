<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<%
	Collection<?> sottoC= (Collection<?>)request.getAttribute("sottoC");
	

%>  




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modifica Sotto Categorie</title>
<link rel="stylesheet" type="text/css" href="css/ModificaCSP.css">
</head>

<body>
<%@include file="barra.jsp" %>

<section class="Sezione_modifica col-12 col-m-12 ">
<div class="TitoloM col-12 col-m-12">

  <h1> MODIFICA SOTTO CATEGORIA</h1>
 </div>

<table class="tabella col-12 col-m-12">

<tr> 
    <th>NOME</th>
     <th>DESCRIZIONE</th>
     <th> ID CATEGORIA</th> 
      <th> MODIFICA </th>
<tr>


<% if(sottoC!=null&& sottoC.size()!=0){
    	 
    	     Iterator<?> it=sottoC.iterator();
    	      while(it.hasNext()){
    		  SottoCatData bean=(SottoCatData)it.next();
    	  %>
    	
    	<tr> 
    	   <td> <%=bean.getNomeSottoCategoria()%> </td>
    	   <td> <%=bean.getDescrizione()%> </td>
    	   <td> <%=bean.getIdCategoria()%></td>
    	   <td><a href="ModificaSottoCS?action=cancella&id=<%=bean.getIdSottoCategoria()%>">Rimuovi Sotto Categoria</a> <br>
    	   <a href="SottoCatControl?action=modifica&id=<%=bean.getIdSottoCategoria()%>">Modifica Sotto Categoria</a>
    	   
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