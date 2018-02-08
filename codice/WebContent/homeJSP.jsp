<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Amministratore.CategoriaDataDao"%>
<%@page import="model.Amministratore.ProdottiDataDao"%>
<%@page import="model.Amministratore.SottoCatDataDao"%>
<%@page import="model.Amministratore.SottoCatData"%>	
<%@page import="model.Amministratore.ProdottiData"%>
<%@page import="model.Amministratore.CategoriaData"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Amministratore.CategoriaData"%>	
<%@page import="java.util.Collection"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.sql.SQLException"%>

<% 
	 CategoriaDataDao modelc = new CategoriaDataDao();
	Collection<?> categorie = (Collection<?>) modelc.doRetrieveAll("nomeCategoria");
	SottoCatDataDao modelSC = new SottoCatDataDao();

	Collection<?> sottoCategorie = (Collection<?>) modelSC.doRetrieveAll("nomeSottoCategoria");

	Collection<ProdottiData> inEvidenza = new ArrayList<>();
	ProdottiDataDao modelP = new ProdottiDataDao();
	Collection<ProdottiData> collezioneP = (Collection<ProdottiData>) modelP.doRetrieveAll("nome");
	if (collezioneP != null && collezioneP.size() != 0) {

		Iterator<?> it = collezioneP.iterator();
		while (it.hasNext()) {
			ProdottiData bean = (ProdottiData) it.next();
			boolean presenza = false;
			for (ProdottiData t : inEvidenza) {

				if (t.getIdProdotto() == bean.getIdProdotto()) {
					presenza = true;
				}
			}
			if (presenza == false && bean.isInEvidenza() == true) {
				inEvidenza.add(bean);
			}
		}
	}

%>    

<!DOCTYPE html>
<html>
<head>
<title>Tutto mare,Vendita articoli per la pesca</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>

<%@include file="barra.jsp"%>

	<section class="menuLaterale col-12 col-m-12">
		
		<div class="mySlides ">
			<img alt="image" class="immSlides" src="img/pesca.jpg">
		</div>

		<div class="mySlides ">

			<img alt="image" class="immSlides" src="img/pesca1.jpg">

		</div>

		<div class="mySlides">

			<img alt="image" class="immSlides" src="img/pesca3.jpg">

		</div>
		<div class="mySlides">

			<img alt="image" class="immSlides" src="img/pesca4.jpeg">

			<div style="text-align: center">
				<span class="dot" onclick="currentSlide(1)"></span> <span
					class="dot" onclick="currentSlide(2)"></span> <span class="dot"
					onclick="currentSlide(3)"></span>
			</div>

		</div>
		
		
		<div class="navi"  >
         <ul>
			<li><a href="Catalogo">Tutte le categorie</a></li>
			<%
				if (categorie != null && categorie.size() != 0) {

				Iterator<?> it = categorie.iterator();
				while (it.hasNext()) {
				CategoriaData bean = (CategoriaData) it.next();
	    	%>
			
			
			 <li><a href="MostraSCperCategoria?action=visualizza&id=<%=bean.getIdCategoria()%>&nome=<%=bean.getNome()%>"> <%=bean.getNome()%></a>
			   <ul>
			
			   <% if(sottoCategorie!=null&& sottoCategorie.size()!=0){
    	 
    	          Iterator<?> ite=sottoCategorie.iterator();
    	          while(ite.hasNext()){
    		      SottoCatData beanS=(SottoCatData)ite.next();
    		      %>
    		     
    		      
    		    <%   if(beanS.getIdCategoria()==bean.getIdCategoria()){%>
    	            
			         <li>  <a href="ProdottiSC_Servlet?action=visualizza&id=<%=beanS.getIdSottoCategoria()%>"> <%=beanS.getNomeSottoCategoria()%> </a>
			            </li>
			            
			            
			<%} }%>
			</ul> </li>
			<% } }} %>
		</ul>
		</div>
	</section>

	<script>
var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
       slides[i].style.display = "none";  
    }
    slideIndex++;
    if (slideIndex> slides.length) {slideIndex = 1}    
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";  
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 2000); // Change image every 2 seconds
}
</script>


	<section class="offerte col-12">
		<div class=" divH1 col-12">
			<h1 class="PiuVenduti col-12">IN EVIDENZA</h1>
		</div>

		<table class="prodottiPiuVenduti col-12 col-m-12 ">
			<tr class="col-12 col-m-12">
			
			<% if(inEvidenza!=null&& inEvidenza.size()!=0){
    	          Iterator<?> iter=inEvidenza.iterator();
    	          while(iter.hasNext()){
    		      ProdottiData beanP=(ProdottiData)iter.next();
    		      
    		     
    		      %>
    		     
			
				<td class="col-3 col-m-3 ">
				    
				    <span class="Marca"><a href="SchedaProdotto?action=dettagli&id=<%=beanP.getIdProdotto()%>&sc=<%=beanP.getSottoCategoria()%>" > <%=beanP.getNome()%>(<%=beanP.getMarca()%>)</a> </span><br>
				 <br> <img class="fotoProdotto" src="<%=beanP.getImmagine()%>"
					alt="fotoProdotto"><br> <span class="Prezzo">
						<%=beanP.getPrezzo()%> €</span> 

			
			
		
<%}} %>
           </td> 
		</tr> 
          

		</table>
	</section>


	<%@include file="footerHome.jsp"%>

	<script>

<!-- Funzione per far tornare la pagina su quando l'utente clicca il bottone-->

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("BottoneTopSmarthpone").style.display = "block";
    } else {
        document.getElementById("BottoneTopSmarthpone").style.display = "none";
    }
}

</script>
<!-- 


	  var xhttp = new XMLHttpRequest();
	  xhttp.onload = function() {
		  
	    if (this.readyState == 4 && this.status == 200) {
	   
	    
	    	
	    	var obj = JSON.parse(this.responseText);

	      var j=0;

	      for (var i=0; i<obj.categorie.length; i++){
			  var lista = $('#categorie');


		
		
				if((obj.categorie[i].nomeC.localeCompare(obj.categorie[j].nomeC)==1)||j==0){

					var li=$('<li>').appendTo(lista);
					j=i+1;
					var aC= $('<a href=MostraSCperCategoria?action=visualizza&nome='+obj.categorie[j].nomeC+'>').val(obj.categorie[j].nomeC).text(obj.categorie[j].nomeC).appendTo(li);
					var listaSC=$('<ul>').appendTo(li);
					
				}
					if(obj.categorie[i].nomeC.localeCompare(obj.categorie[j].nomeC)==0){
						
						var liU=$('<li>').appendTo(listaSC);

						var liSC=$('<a href=ProdottiSC_Servlet?action=visualizza&id='+obj.categorie[i].idSC+'>').val(obj.categorie[i].nomeSC).text(obj.categorie[i].nomeSC).appendTo(liU);

					}

					}

					}
					
					
	
	    
	    };
	  xhttp.open("GET", "AjaxCategorie", true);

	  xhttp.send();








 -->

</body>


</html>
