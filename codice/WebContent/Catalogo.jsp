<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.CategoriaDataDao"%>
<%@page import="model.Amministratore.CategoriaData"%>

<%
	Collection<?> sottoC= (Collection<?>)request.getAttribute("sottoC");
    Collection<?> categorie= (Collection<?>)request.getAttribute("categorie");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/Catalogo.css">
<title>Il nostro catalogo</title>
</head>
<body>
<%@include file="barra.jsp" %>

<div class=" BarraCatalogo col-12 col-m-12 ">
			<h1 class="divCatal">Il NOSTRO CATALOGO</h1>
		</div>
<section class="Catalogo col-12 col-m-12">

<% if(categorie!=null&& categorie.size()!=0){
    	 
    	     Iterator<?> it=categorie.iterator();
    	      while(it.hasNext()){
    		  CategoriaData bean=(CategoriaData)it.next();
    	  %>

 <div class="col-3 col-m-3" >
 <h1 class="titoloCategoria" style="text-transform: uppercase;"> <%=bean.getNome() %> </h1>
<ul>
    <% if(sottoC!=null&& sottoC.size()!=0){
    	 
    	     Iterator<?> ite=sottoC.iterator();
    	      while(ite.hasNext()){
    		  SottoCatData beanS=(SottoCatData)ite.next();
    		  
    		  if(beanS.getIdCategoria()==bean.getIdCategoria()){
    	  %>
  
    <li class="sc"> <a href="ProdottiSC_Servlet?action=visualizza&id=<%=beanS.getIdSottoCategoria()%>"> <%= beanS.getNomeSottoCategoria()%> </a> </li> 
   
<%} }
    }%>
  </ul>
  
   <img src="<%=bean.getPathicona() %>" alt="<%=bean.getNome() %>" class="immCategoria">
 </div>

 <% }}%>
</section>




<%@include file="footerHome.jsp" %>

</body>

</html>