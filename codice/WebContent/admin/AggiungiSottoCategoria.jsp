<%@page import="model.Amministratore.SottoCatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.CategoriaData"%>
<%@page import="model.Amministratore.SottoCatData"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.CategoriaDataDao"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>
<%@page import="java.util.Collection"%>    
    
<%
	Collection<?> categorie= (Collection<?>)request.getAttribute("categorie");
    SottoCatData sottoC_modifica =(SottoCatData) request.getAttribute("sottoC_modifica");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Aggiungi Sotto Categoria</title>
<link rel="stylesheet" type="text/css" href="css/AggiungiSottoC.css">
</head>
<body>
<%@include file="../barra.jsp" %>
	<section class="Aggiungi col-12 col-m-12">
		<div class="TitoloA col-12 col-m-12">

			<h1>AGGIUNGI SOTTO CATEGORIA</h1>
		</div>

		<form class="FormA col-6 col-m-6" action="SottoCatControl" method="post">

  <% if(sottoC_modifica!=null){
	 
 %>
     <input type="hidden" name="action" value="modifica"> <label
					for="nome">Nome:</label><br> <input name="nome" type="text"
					maxlength="20" required value="<%=sottoC_modifica.getNomeSottoCategoria()%>"class="input"><br>

				<label for="descrizione">Descrizione:</label><br>
				<textarea name="descrizione" maxlength="100" rows="3" required
				 class="input"><%=sottoC_modifica.getNomeSottoCategoria()%></textarea>
				 
				<br> <label for="categoria">Categoria:</label><br>
				<br>
                 <select name="categoria" class="categoria">
				<%
					if (categorie != null && categorie.size() != 0) {

							Iterator<?> it = categorie.iterator();
							while (it.hasNext()) {
								CategoriaData bean = (CategoriaData) it.next();
				%>
                 
                <%  if(sottoC_modifica.getIdCategoria()==bean.getIdCategoria()){%>
				<option  selected="selected" value="<%=bean.getNome()%>"> <%=bean.getNome()%> </option>
			    <%} else{ %>
               
               <option  value="<%=bean.getNome()%>"> <%=bean.getNome()%></option>
				
				<%}} }%>
				</select>
			
			
			<p>
			<p>

				<input type="submit" value="Send" class="submit"><input
					type="reset" value="Reset" class="submit">
			
 
 <% } 
 
   else {
			
		%>
 


				<input type="hidden" name="action" value="inserisci"> <label
					for="nome">Nome:</label><br> <input name="nome" type="text"
					maxlength="20" required placeholder="nome" class="input"><br>

				<label for="descrizione">Descrizione:</label><br>
				<textarea name="descrizione" maxlength="100" rows="3" required
					placeholder="descrizione" class="input"></textarea>
				<br> <label for="categoria">Categoria:</label><br>
				<br>
                   <select name="categoria" class="categoria">
				<%
					if (categorie != null && categorie.size() != 0) {

							Iterator<?> it = categorie.iterator();
							while (it.hasNext()) {
								CategoriaData bean = (CategoriaData) it.next();
				%>
             
				<option  value="<%=bean.getNome()%>"> <%=bean.getNome()%></option>
				
              
				<%
					}
						}
				%>
			    </select>
			<p>
			<p>

				<input type="submit" value="Send" class="submit"><input
					type="reset" value="Reset" class="submit">
			
			<%} %>
		</form>

	</section>


	<%@include file="../footerHome.jsp" %>

</body>
</html>