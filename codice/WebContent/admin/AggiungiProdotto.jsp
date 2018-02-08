<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.ProdottiDataDao"%>
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="java.util.Collection"%>    
    
<%
	Collection<?> sottoCategorie= (Collection<?>)request.getAttribute("sottoCategorie");
	ProdottiData prodotto_modifica =(ProdottiData) request.getAttribute("prodotto_modifica");


%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Aggiungi Prodotti</title>
<link rel="stylesheet" type="text/css" href="css/AggiungiC&P.css">
</head>
<body>
<%@include file="../barra.jsp" %>
<section class="Aggiungi col-12 col-m-12"> 
 <div class="TitoloA col-12 col-m-12">

  <h1> AGGIUNGI PRODOTTI</h1>
 </div>
 
<form class="FormA col-6 col-m-6" action="ProdottiControl" method="post" enctype="multipart/form-data">


 <% if(prodotto_modifica!=null){
	 
 %> <input type="hidden" name="action" value="modifica"> 
    <label for="nome">Nome:</label><br> 
		<input name="nome" type="text" maxlength="40" required value="<%=prodotto_modifica.getNome()%>" class="input"><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="500" rows="3" required  class="input"><%=prodotto_modifica.getDescrizione()%></textarea><br>
		
		<label for="marca">Marca:</label><br> 
		<input name="marca" type="text" maxlength="40" required value="<%=prodotto_modifica.getMarca()%>"class="input"><br> 
		
		
		<label for="prezzo">Prezzo:</label><br> 
		<input name="prezzo" maxlength="3" type="number" step="0.01" value="<%=prodotto_modifica.getPrezzo()%>"required class="input" ><br>
		
		<label for="peso">Peso in kg:</label><br> 
		<input name="peso"  maxlength="3" type="number" step="0.01" required value="<%=prodotto_modifica.getPeso()%>"class="input"><br> 
       
       
		<label for="quantità">Quantità:</label><br> 
		<input name="quantita" type="number" min="1" value= "<%=prodotto_modifica.getQuantita()%>"required class="input"><br>
		
		<label for="sottoC">Sotto Categoria:</label><br><br>
		<select name="sottoCat" class="sottoCat">
		
		  <% if(sottoCategorie!=null&& sottoCategorie.size()!=0){
    	 
    	     Iterator<?> it=sottoCategorie.iterator();
    	      while(it.hasNext()){
    		  SottoCatData bean=(SottoCatData)it.next();
    	  %>
    	  <%  if(prodotto_modifica.getSottoCategoria()==bean.getIdSottoCategoria()){%>

		<option   value="<%=bean.getNomeSottoCategoria()%>" selected="selected"> <%=bean.getNomeSottoCategoria()%></option>
		
		<%} else { %>
		<option  value="<%=bean.getNomeSottoCategoria()%>"> <%=bean.getNomeSottoCategoria()%> </option>
		
			<%}
    	      }
			} 
		%>
		</select>
		
		<p>
		
		<label for="evidenza">Prodotti in Evidenza:</label><br><br>
		
		<% boolean evidenza=prodotto_modifica.isInEvidenza();
		   if(evidenza==true){
		 %>
		<select name="evidenza" class="evidenza">
		<option  value="si" class="radio" selected="selected" > SI</option>
		<option  value="no" class="radio" > NO </option>
		</select>
		<%} else{ %>
		<select name="evidenza" class="evidenza">
		<option   value="si" > SI</option>
		<option  value="no"  selected="selected"> NO </option>
		<%} %>
		</select>
		
		<label for="immagine"> Immagine Prodotto:</label><br>
		  <input type="file" accept="image/*" size="30" onchange="preview()" id="upload_immagine" name="img" class="input" > <br>
       
       <div class="col-3 col-m-3 anteprima" >
        <img id="anteprima_immagine" src= "<%=prodotto_modifica.getImmagine()%>"><br/><br/>
		</div>
	  <p>
	  <p>
	  <p>
	

		<input type="submit" value="Send" class="submit"><input type="reset" value="Reset" class="submit" >
		
		
		<% } 
 
          else {
			
		%>
		
		
		<input type="hidden" name="action" value="inserisci"> 
		<label for="nome">Nome:</label><br> 
		<input name="nome" type="text" maxlength="40" required placeholder="nome" class="input"><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="500" rows="3" required placeholder="descrizione" class="descrizione"></textarea><br>
		
		<label for="marca">Marca:</label><br> 
		<input name="marca" type="text" maxlength="20" required placeholder="marca" class="input"><br> 
		
		
		<label for="prezzo">Prezzo:</label><br> 
		<input name="prezzo" type="number" min="1" value="0" step="0.01" required class="input"><br>
		
		<label for="peso">Peso in kg:</label><br> 
		<input name="peso" type="number" min="0"  step="0.01" required placeholder="peso" class="input"><br> 
       
       
		<label for="quantità">Quantità:</label><br> 
		<input name="quantita" type="number" min="1" value="1" required class="input"><br>
		
	
		
		<label for="sottoC">Sotto Categoria:</label><br><br>
		<select name="sottoCat" class="sottoCat">
		  <% if(sottoCategorie!=null&& sottoCategorie.size()!=0){
    	 
    	     Iterator<?> it=sottoCategorie.iterator();
    	      while(it.hasNext()){
    		  SottoCatData bean=(SottoCatData)it.next();
    	  %>

		<option value="<%=bean.getNomeSottoCategoria()%>"> <%=bean.getNomeSottoCategoria()%></option>
		
			<%
				}
			} 
		%>
		</select>
		<p>
		
		<label for="evidenza">Prodotti in Evidenza:</label><br><br>
		<select name="evidenza" class="evidenza"> 
		<option  value="si"> SI </option>
		<option  value="no"> NO </option>
		</select> <br>
		
		<label for="immagine"> Immagine Prodotto:</label><br>
		  <input type="file" accept="image/*" size="30" onchange="preview()" id="upload_immagine" name="img" class="input"> <br>
       
       <div class="col-3 col-m-3 anteprima" >
        <img id="anteprima_immagine" src="icone/default.png" ><br/><br/>
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