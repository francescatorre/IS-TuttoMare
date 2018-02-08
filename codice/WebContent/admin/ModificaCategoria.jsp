<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.CategoriaDataDao"%>
<%@page import="model.Amministratore.CategoriaData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<%
	Collection<?> categorie= (Collection<?>)request.getAttribute("categorie");
	

%>  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modifica Categorie</title>
<link rel="stylesheet" type="text/css" href="css/ModificaCSP.css">
</head>

<body>
<%@include file="../barra.jsp" %>

<section class="Sezione_modifica col-12 col-m-12 ">
<div class="TitoloM col-12 col-m-12">

  <h1> MODIFICA CATEGORIA</h1>
 </div>

<table class="tabella col-12 col-m-12">

<tr> 
    <th>NOME</th>
     <th>DESCRIZIONE</th>
     <th> IMMAGINE</th> 
     <th> MODIFICA</th> 
<tr>


<% if(categorie!=null&& categorie.size()!=0){
    	 
    	     Iterator<?> it=categorie.iterator();
    	      while(it.hasNext()){
    		  CategoriaData bean=(CategoriaData)it.next();
    	  %>
    	
    	<tr> 
    	   <td> <%=bean.getNome()%> </td>
    	   <td> <%=bean.getDescrizione()%> </td>
    	   <td> <img class="iconaA" src="<%=bean.getPathicona()%>" ></td>
    	   <td><a href="ModificaCategoriaS?action=cancella&id=<%=bean.getIdCategoria()%>" class="a">Rimuovi Categoria</a> <br>
    	   <a href="CategoriaControl?action=modifica&id=<%=bean.getIdCategoria()%> " class="a">Modifica Categoria</a>
    	   
    	   </td> 
		   
    	
    	<%
				}
			} 
		%>

</table>
</section>
<%@include file="../footerHome.jsp" %>

</body>
</html>