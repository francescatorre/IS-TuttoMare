<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="model.Account.AddressData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prodotto</title>
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
ProdottiData a = (ProdottiData) request.getAttribute("prodotto");
%>
<table>
  <tr>
    <th>Nome</th>
    <th>Descirizione</th>
    <th>Immagine</th>
    <th>Prezzo</th>
    <th>Peso</th>
    <th>Quantit&agrave;</th>
    <th>Marca</th>

  </tr>
  <tr>
    <td><%=a.getNome()%></td>
    <td><%=a.getDescrizione()%></td>
    <td><img src="<%=a.getImmagine()%>" alt="imgProdotto"> </td>
    <td><%=a.getPrezzo()%></td>
        <td><%=a.getPeso()%></td>
    <td><%=a.getQuantita()%></td>
        <td><%=a.getMarca()%></td>
            

</tr>

</table>





</body>
</html>