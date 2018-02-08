<%@page import="model.Account.AddressData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IndirizzoFatturazione</title>
<style>

@CHARSET "UTF-8";
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #ABCDEF}

th {
    background-color: #333;
    color: white;
}

</style>

</head>
<body>
<% 
AddressData a = (AddressData) request.getAttribute("address");
%>
<table>
  <tr>
    <th>Nome</th>
    <th>Cognome</th>
    <th>Stato</th>
    <th>Provincia</th>
    <th>Citt&agrave;</th>
    <th>Via</th>
    <th>Numero Civico</th>
    <th>Cap</th>
    <th>Email</th>
    <th>Telefono</th>

  </tr>
  <tr>
    <td><%=a.getNome()%></td>
    <td><%=a.getCognome()%></td>
    <td><%=a.getStato()%></td>
    <td><%=a.getProvincia()%></td>
        <td><%=a.getCitta()%></td>
    <td><%=a.getVia()%></td>
        <td><%=a.getNumeroCivico()%></td>
        <td><%=a.getCap()%></td>
        <td><%=a.getEmail()%></td>
        <td><%=a.getTelefono()%></td>
    

</tr>

</table>





</body>
</html>