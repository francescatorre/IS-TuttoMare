<%@page import="model.Amministratore.CategoriaData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.CategoriaDataDao"%>
<%@page import="model.Amministratore.CategoriaData"%>
<%@page import="java.util.Collection"%>     
 
 <%
	Collection<?> sottoCategorie= (Collection<?>)request.getAttribute("sottoCategorie");
    CategoriaData categoria_modifica =(CategoriaData) request.getAttribute("categoria_modifica");


%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Aggiungi Categoria</title>
<link rel="stylesheet" type="text/css" href="css/AggiungiC&P.css">


</head>
<body>
<%@include file="../barra.jsp" %>
<section class="Aggiungi col-12 col-m-12"> 
 <div class="TitoloA col-12 col-m-12">

  <h1> AGGIUNGI CATEGORIA</h1>
 </div>
 
<form class="FormA col-6 col-m-12" action="CategoriaControl" method="post" enctype="multipart/form-data">

<% if(categoria_modifica!=null){ %>

<input type="hidden" name="action" value="modifica"> 

		
		<label for="nome">Nome:</label><br> 
		<input name="nome" type="text" maxlength="20" required value="<%=categoria_modifica.getNome()%>" class="input"><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" required class="input"><%=categoria_modifica.getDescrizione()%></textarea><br>
		
		<label for="immagine"> Immagine Categoria:</label><br>
		  <input type="file" accept="image/*" size="30" onchange="preview()" id="upload_immagine" name="img" class="input"> <br>
       
       <div class="col-3 col-m-3 anteprima" >
        <img id="anteprima_immagine" src="<%=categoria_modifica.getPathicona()%>"> <br/><br/>
		</div>
	  <p>
	  <p>
	  <p>
	 
		<input type="submit" value="Send" class="submit"><input type="reset" value="Reset" class="submit">


		<% } 
 
          else {
			
		%>

<input type="hidden" name="action" value="inserisci">
 <label for="nome">Nome:</label><br> 
		<input name="nome" type="text" maxlength="20" required placeholder="nome" class="input"><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" required placeholder="descrizione" class="input"></textarea><br>
		
		<label for="immagine"> Immagine Categoria:</label><br>
		  <input type="file" size="30" onchange="preview()" id="upload_immagine" name="img" class="input"> <br>
       
       <div class="col-3 col-m-3 anteprima" >
        <img id="anteprima_immagine" src="../icone/default.png" ><br/><br/>
		</div>
	  <p>
	  <p>
	  <p>
	 

		<input type="submit" value="Send" class="submit"><input type="reset" value="Reset" class="submit">
		
		<% } %>
		
 </form>		
	<script>
	function preview() 
	{
		// prelevo l'indirizzo locale dell'immagine da caricare
		immagine_da_caricare = document.getElementById('upload_immagine').value;   
		// swappo l'immagine presente con quella da caricare
		// file:/// -> serve in particolare per firefox altrimenti non visualizza immagine
		document.getElementById('anteprima_immagine').src = "file:///"+immagine_da_caricare;
	}
	</script>
		
</section>


<%@include file="../footerHome.jsp" %>

</body>
</html>