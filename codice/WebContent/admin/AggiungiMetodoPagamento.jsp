<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="model.Amministratore.PaymentData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/AggiungiSpedizioni.css">
<title>Aggiungi metodo di pagamento</title>
</head>
<body>
<%@include file="../barra.jsp" %>


<div class="barraVisualizza col-12 col-m-12">

  <h1> METODI DI PAGAMENTO </h1>
 </div>

<span class="Visualizza col-12 col-m-12">
Questi sono i metodi si pagamento già aggiunti e visibili 
al cliente. 
</span>
<section class="VisualizzaSpedizioni col-12 col-m-12">
<% Collection<?> lista = (Collection<?>)request.getAttribute("listaMetodiPagamento");%>
  <table class="col-8 col-m-8" style="margin-left:18%;">
<tr> 
    <th>TIPO PAGAMENTO</th> 
    <th>ELIMINA</th>
 </tr>   
 
  <%  if(lista!=null&& lista.size()!=0){
	 
    Iterator<?> it=lista.iterator();
     while(it.hasNext()){
    	 PaymentData data=(PaymentData)it.next();
    %>
<tr>
<td><%=data.getTipoPagamento()%></td>
<td> <a href="VisualizzaMetodiPagamento?action=cancella&id=<%=data.getIdPagamento()%>" class="a"> <img src="icone/false.png" style="font-size: 0px; height: 20px;"> </a>

<%}}%>
</table>
</section>


<section class="AggiungiSpedizioni col-12 col-m-12">

<div class="barraVisualizza col-12 col-m-12">
  <h1> AGGIUNGI SPEDIZIONI </h1>
 </div>

<span class="Visualizza col-12 col-m-12">
Qui puoi inserire i metodi di pagamento </span>

<form action="AggiungiMetodoPagamento" method="get">
<input type="text" name="metodo" class="inputTE" placeholder="metodo" >

<input type="submit" value="conferma" class="Conferma">
</form>
</section>
<%@include file="../footerHome.jsp" %>
</body>
</html>