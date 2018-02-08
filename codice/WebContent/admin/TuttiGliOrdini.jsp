<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="model.ServiziUtente.OrderData"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tutti gli ordini</title>
<link rel="stylesheet" type="text/css" href="css/tuttiGliOrdini.css">

</head>
<body>
<%@include file="../barra.jsp" %>
<%double totale=(Double)request.getAttribute("totale"); %>

<p id="incasso">Incasso ordini:<%=totale%></p>


<%
ArrayList<OrderData> ordini = (ArrayList<OrderData>) request.getAttribute("ordini");
for (OrderData data : ordini) {
%>

<%!String s; %>
<%switch(data.getStatusOrdine()){
case 1:s="pagato";
case 2:s="consegnato";
case 3:s="spedito";
	
}
	
	
	%>

<table>
  <tr>
    <th>Email</th>
    <th>Numero Ordine</th>
    <th>Quantità</th>
    <th>Status</th>
    <th>Codice Prodotto</th>
    <th>Codice Indirizzo</th>
    <th>Totale</th>

  </tr>
  <tr>
    <td><%=data.getEmail()%></td>
    <td><%=data.getNumeroOrdine()%></td>
    <td><%=data.getQuantita()%></td>
    <td><%=s%></td>
    
    <td><a href="CercaProdotto?idP=<%=data.getIdProdotto()%>"><%=data.getIdProdotto()%></a></td>
  <td><a href="CercaIndirizzo?id=<%=data.getIdIndirizzo()%>"><%=data.getIdIndirizzo()%></a></td>
	<td><%=data.getTotale()%></td>

</tr>

</table>


<%} %>
</div>
</body>
</html>