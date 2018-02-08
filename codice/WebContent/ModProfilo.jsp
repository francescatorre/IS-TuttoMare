<%@page import="model.Account.AddressData"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="model.Account.UserData"%>
<%Collection<?> lista=(Collection<?>)request.getAttribute("list"); %> 
  <%UserData utente=(UserData)request.getAttribute("utente");%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/ModificaProfilo.css">
<title> Modifica </title>
</head>

<body>
<%@include file="barra.jsp"%>
<%if(utente!=null){ %>
<div id="divFormRegistrazione col-11 col-m-11" >
  
 <form  action="ModificaProfilo" method="post" enctype="multipart/form-data" class="FormEl col-11 col-m-11">
 <input type="hidden" name="userEmail" value="<%=utente.getEmail()%>">
 <input type="hidden" name="eliminaAccount" value="on">
 <label for="elimina" class="EliminaA"> Elimina account:</label> <input type="submit" value="Elimina" class="btnElimina">
 
 </form>
 
</div>
 
 <div class="DatiAnagrafici col-11 col-m-11">
 
  <form class="formDatiA col-12 col-m-12" ACTION="ModificaProfilo" method="post" enctype="multipart/form-data">

     <div class=" col-12 col-m-12">
     <img id="imgP" class="avatarRegistrazione" src= "<%=utente.getAvatar()%>">  <br>
	 <input type="file" accept="image/*" onchange="loadFile(event)" name="photo"> 

    </div> 
 

<div class="col-12 col-m-12">
 
 <label for="Email" class="Label"> Email:</label> <br>
 <input type="text" name="emailUtente" class="inputD" value="<%=utente.getEmail()%>" contenteditable="false"> <br>

    <label for="Password" class="Label"> Password:</label> <br>
      <input type="text" name="password" value="<%=utente.getPassword()%>" class="inputD" >
  </div>    
 
 <div class=" anagrafica col-12 col-m-12">

      <label for="nome" class="Label"> Nome:</label> <br>
      <input class="inputD" type="text" name="nome" value="<%=utente.getNome()%>"> <br>

      <label for="cognome" class="Label"> Cognome:</label> <br>
      <input  type="text" class="inputD" value="<%=utente.getCognome()%>" name="cognome"> <br>

 <label for="Sesso" class="Label">Sesso:</label> <br>

<%if(utente.getSesso().equals("M")) {%>
 <input class="radio" type="radio" name="sesso" value="M" checked="checked"> Uomo
 <input class="radio" type="radio" name="sesso" value="F" > Donna
<br>
<%} else if(utente.getSesso().equals("F")){ %>
 <input class="radio" type="radio" name="sesso" value="M" checked="checked"> Donna
<input class="radio" type="radio" name="sesso" value="F" > Uomo
<br>
<%} %>

<br>
<label class="Label" for="dataN">Data di nascita: </label> <br>
<br>
<input class="data"  type="number" min="1" max="31" class="Data" name="giornoNascita" value="<%=utente.getDataNascita().substring(8,10)%>">
<input class="data" type="number" min="1" max="12" class="Data"name="meseNascita" value="<%=utente.getDataNascita().substring(5,7)%>" > 
<input class="data" type="number" min="1940" class="Data" max="" name="annoNascita" value="<%=utente.getDataNascita().substring(0,4)%>">

</div>

<div class="col-12 col-m-12">
<label class="labelR">Stato:</label>
<input class="inputR" type="text" name="stato" value="<%=utente.getStato()%>">

<label class="labelR">Provincia:</label>
<input class="inputR" type="text" name="provincia" value="<%=utente.getProvincia()%>">

<label class="labelR">Citta:</label>
<input class="inputR" type="text" name="citta" value="<%=utente.getCitta()%>"> 

<label class="labelR">Via:</label>
<input class="inputR" type="text" name="via" value="<%=utente.getVia()%>"> <br>

<label class="labelR">Numero civico:</label>
<input class="inputR" type="text" name="numeroCivico" value="<%=utente.getCap()%>"> 


<label class="labelR">Cap:</label>
<input class="inputR" type="text" name="codicePostale" value="<%=utente.getCap()%>">

<label class="labelR">Telefono:</label>
<input class="inputR" type="text" name="telefono" value="<%=utente.getTelefono()%>">

</div>
      <button class="btnModifica" type="submit">Modifica</button>

</form>
<%} %>
</div>

<div class="Indirizzi col-12 col-m-12">

<div class="barraIndirizzi col-12 col-m-12">
<h1 id="intestazioneI">I tuoi indirizzi </h1>
</div>
<form ACTION="GestioneIndirizzi" method="get" >
 <table class="TableInd col-11 col-m-11">
 <tr>
 <th> Nome </th>
 <th> Cognome </th>
 <th> Stato </th>
 <th> Provincia </th>
 <th> Citta' </th>
 <th> Via  </th>
 <th> Telefono </th>
 <th> Elimina Indirzzo </th>
 </tr>

<% if(lista!=null&& lista.size()!=0){
    	 
    	     Iterator<?> it=lista.iterator();
    	      while(it.hasNext()){
    	     AddressData a=(AddressData)it.next();
    	  %>
	
  <tr>
<td> <%=a.getNome()%> </td>

<td><%=a.getCognome()%> </td>

<td><%=a.getStato()%> </td>

<td><%=a.getProvincia()%> </td>

<td><%=a.getCitta()%> </td>

<td><%=a.getVia()%> (<%=a.getCap()%>)</td>
<td><%=a.getTelefono()%> </td>

<td><input type="submit" name="cancella" value="<%=a.getIdIndirizzo()%>" style="background-image:url('icone/false.png');background-position:center;font-size: 0px; height: 20px;"></td>
 
<%} }%>
</tr>
</table>
</form>
</div>


  <div class="AggiungiIndirizzi col-11 col-m-11">   
  <span class="AiSpan"> Aggiungi Indirizzi</span> 
<button type="submit" id="add" class="btnAdd" onclick="aggiungiIndirizzi()">Aggiungi</button> <br>

 <form ACTION="GestioneIndirizzi" method="post" id="addAddressForm" style="display: none;">
<input type="hidden" name="emailU" value="<%=user.getEmail()%>">

<label class="Label"><b>Nome:</b></label>
<input class="inputD" type="text" name="nomeIndirizzo">

<label class="Label"><b>Cognome:</b></label>
<input class="inputD" type="text" name="cognomeIndirizzo" > 

<label class="Label"><b>Stato:</b></label>
<input class="inputD" type="text" name="statoIndirizzo"> <br>

<label class="Label"><b>Provincia:</b></label>
<input class="inputD" type="text" name="provinciaIndirizzo" > 

<label class="Label"><b>Citta:</b></label>
<input class="inputD" type="text" name="cittaIndirizzo" >

<label class="Label"><b>Via:</b></label>
<input class="inputD" type="text" name="viaIndirizzo" >  <BR>

<label class="Label"><b>Numero Civico:</b></label>
<input class="inputNC" type="text" name="numeroCivicoIndirizzo" >

<label class="Label"><b>Telefono:</b></label>
<input class="inputD" type="text" name="telefonoIndirizzo" > 

<input type="submit" class="btnAdd" value="Invia">
</form>
 
</div>  

<script>
function aggiungiIndirizzi(){
	
	 var form = document.getElementById("addAddressForm");
	 form.style.display="inline";
}
</script>
<script>
  var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };
</script>
<%@include file="footerHome.jsp" %>
</body>
</html>