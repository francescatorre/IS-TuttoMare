<%@page import="java.util.GregorianCalendar"%>
<%@page import="model.Account.AddressData"%>
<%@page import="model.Account.UserDataDao"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.ShipmentData"%>
<%@page import="model.Amministratore.PaymentData"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="model.Account.UserData"%>
<%@page import="java.util.Calendar"%>
<%@page errorPage="ErroreAcquisto.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
Collection<?> listaProdotti = (Collection<?>)request.getAttribute("prodotti"); 
float totale=0;
%>
         
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/DatiPagamento.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acquisto Prodotti</title>
</head>
<body>
<%@include file="barra.jsp" %>

<div class="DatiAcquisto col-12 col-m-12">
<h1 class="h1">Prodotti che stai acquistando</h1>

<%
	if (listaProdotti != null && listaProdotti.size() != 0) {

		Iterator<?> it = listaProdotti.iterator();
		while (it.hasNext()) {
		ProdottiData prodotto = (ProdottiData) it.next();
		totale =totale+ (prodotto.getQuantita()* prodotto.getPrezzo());
%>


<form action="DatiAcquisto" class="FormAcquisti col-12 col-m-12" method="get">

     <div class="ProdottoAcquistato col-12 col-m-12">
     <div class="imgP col-3 col-m-3">
	<img alt="img" height="20px" class="ImmagineP" src="<%=prodotto.getImmagine()%>" >
	</div>
	<div class="nome_Descrizion_P col-5 col-m-5">
		<p class="NomeP"> <%=prodotto.getNome() %> </p>
		<p class="descrizioneP"><%=prodotto.getDescrizione() %> </p>
	</div>	
	
		<div class="quantita_P col-2 col-m-2">
		<p class="Quantita">Quantita<br><%=prodotto.getQuantita()%> </p>
	</div>
	<div class="Prezzo_P col-2 col-m-2">
		<p class="Prezzo"> <span>Prezzo </span> <br>
		<%=prodotto.getPrezzo()%>€</p>
    </div>
  
    
 </div>
<%
}}
%>


<div class="IndirizzoFatturazione col-12 col-m-12">
<h1 class="h1"> Seleziona Indirizzo di fattutazione</h1>
<table class="TabellaIndirizzo col-12 col-m-12">
<tr>
<th>Nome </th>
<th>Cognome </th>
<th>Telefono </th>
<th>Stato</th>
<th>Provincia</th>
<th>Citt&agrave;</th>
<th>Via </th>
<th>N°civico </th>
<th>Cap </th>
<th></th>
</tr>

<% 
Collection<?> addressList = (Collection<?>) request.getAttribute("indirizzo");

if(addressList!=null&& addressList.size()!=0){
	 
    Iterator<?> ite=addressList.iterator();
     while(ite.hasNext()){
     AddressData data=(AddressData)ite.next();
 %>

<tr>
	<td><%=data.getNome() %></td>
	<td><%=data.getCognome() %> </td>
	<td><%=data.getTelefono() %> </td>
	<td><%=data.getStato() %> </td>
	<td><%=data.getProvincia()%> </td>
	<td><%=data.getCitta() %> </td>
	<td><%=data.getVia() %> </td>
	<td><%=data.getNumeroCivico() %></td>
	<td><%=data.getCap()%></td>
    <td><input type="radio" name="check" required="required" value="<%=data.getIdIndirizzo()%>"> </td>
</tr>

<%} }%>
</table>
</div>

<br>
<div class="NuovoIndirizzo col-12 col-m-12">
<span class="titoloNi">Inserisci un nuovo indirizzo di fatturazione </span>
<input type="radio"  name="check" value="invia a questo indirizzo">
<div class="col-12 col-m-12">

<label for="Nome" class="labelNI"> Nome:</label> <br>
<input type="text" class="inputNI" name="nome" placeholder="nome">
<br>

<label for="Cognome" class="labelNI"> Cognome:</label> <br>
<input type="text" class="inputNI" name="cognome" placeholder="cognome">
<br>

<label for="Telefono" class="labelNI"> Telefono:</label> <br>
<input type="text" class="inputNI" name="telefono" placeholder="telefono">
<br>

<label for="Stato" class="labelNI"> Stato:</label> <br>
<input type="text" class="inputNI" name="stato" placeholder="stato">
<br>

<label for="Provincia" class="labelNI"> Provincia:</label> <br>
<input type="text" class="inputNI" name="provincia" placeholder="provincia">
<br>

<label for="Citta" class="labelNI"> Citta:</label> <br>
<input type="text" class="inputNI" name="citta" placeholder="citta">
<br>

<label for="Via" class="labelNI">Via:</label> <br>
<input type="text" class="inputNI" name="indirizzoFatturazione" placeholder="via">
<br>
<label for="NumeroC" class="labelNI"> Numero Civico:</label> <br>
<input type="text" class="inputNI" name="civico" placeholder="numero civico">
<br>

<label for="Cap" class="labelNI"> Cap:</label> <br>
<input type="text" class="inputNI" name="cap" placeholder="cap">
<br>
</div>
 
</div>



<div class="Spedizone col-12 col-m-12">
<h1 class="h1">Seleziona il metodo di spedizione</h1>
<table class="TabellaSpedizone col-12 col-m-12"> 
<tr> 
<th>Tipo Spedizione </th>
<th> Spese di Spedizoni</th>
<th> Consegna Stimata per il</th>

</tr>


<%

Calendar c = Calendar.getInstance();

Collection<?> listaSpedizione = (Collection<?>)request.getAttribute("listaSpedizione");

if(listaSpedizione!=null&& listaSpedizione.size()!=0){
	 
    Iterator<?> iter=listaSpedizione.iterator();
     while(iter.hasNext()){
	  ShipmentData data=(ShipmentData)iter.next();
	  int giorni=c.get(Calendar.DAY_OF_MONTH)+data.getGiorni();
	  if(giorni>31){giorni=giorni-31;}
 %>

  <tr>
		<td><%=data.getTipoSpedizione()%> </td>
		<td><%=data.getCosto()%> </td>
		<td><%=giorni%>/<%=c.get(Calendar.MONTH)%>/<%=c.get(Calendar.YEAR)%></td>
     <td> <input type="radio" name="spedizione"  required="required"value="<%=data.getIdSpedizione()%>"  style="background-image:url('icone/false.png');background-position:center;font-size: 0px; height: 20px;"></td>
</tr>

<%}} %>
</table>
</div>

<div class="MetodoPagamento col-12 col-m-12">
 <h1 class="h1">Seleziona il metodo di pagamento </h1>

<%
Collection<?> listaMetodi = (Collection<?>)request.getAttribute("listaMetodi");
if(listaMetodi!=null&& listaMetodi.size()!=0){
	 
    Iterator<?> itera=listaMetodi.iterator();
     while(itera.hasNext()){
	  PaymentData data=(PaymentData)itera.next();
	%>


	<span class="MetodoP"> <%=data.getTipoPagamento()%> </span>
    <input type="radio" name="metodoPagamento" value="<%=data.getIdPagamento()%>" required="required" style="background-image:url('icone/false.png');background-position:center;font-size: 0px; height: 20px;"><br> 

<%} }%>

</div>
<span class="Totale">Totale:<%=Math.floor(totale * 100.0) / 100.0%> €</span>
<input type="submit" class="Conferma col-11 col-m-11" name="conferma">
</form>
</div>
<%@include file="footerHome.jsp"%>
</body>
</html>