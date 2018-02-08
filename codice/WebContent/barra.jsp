<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Account.UserData"%>



<%
	//allow access only if session exists
	UserData user = null;
	user = (UserData) session.getAttribute("user");

	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user"))
				userName = cookie.getValue();

			//jsessionid è cookie generato dal server container di tomcat
			//se il web server usa i cookie per la sessione tomcat invia il cookie
			//jsesionid al client e il client lo rimanda indietro al server nella richiesta

			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();
		}
	} else {
		sessionID = session.getId();
	}
%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/PWTuttoMare/css/barraDinavigazione.css">
 <link rel="stylesheet" type="text/css" href="/PWTuttoMare/css/formLogin.css">
 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<!-- for smartphone -->
 
<header class="headerForSmartphone">
 
<nav class="navBarSmartphone">
<div id="barraS">
 <ul class="topnav">

<li><a href="/PWTuttoMare/homeJSP.jsp" ><img id="logo" alt="logoUfficiale" src="img/logoFinito.png"></a>
</li>
  <li><a href="homeJSP.jsp">Home</a></li>
  <li class="sottoMenu"><a href="#eventi">Eventi</a>
  <li class="sottoMenu"><a href="#info">Informazioni</a>
 
  <div class="contenitore_sottoMenu">
      <a href="#">Chi siamo</a>
      <a href="DoveSiamo.jsp">Dove siamo</a>
       <a href="#">Termini e Condizioni</a>
    </div>
  </li>
  <%if(user!=null){
	if(user.isAdmin()){
	%>
		<li class="sottoMenu"><a href="/PWTuttoMare/WebContent/admin/menuAdmin.jsp">Pannello Admin</a></li>
		

 
 
<%}} %> 
  <%if(user!=null){ %>
<li><a href="ModificaProfilo?user=<%=user.getEmail()%>">Profilo</a></li>
<li ><a href="/PWTuttoMare/AcquistiEffettuati">I miei ordini</a></li>
<%} %>
  
  </ul>
 
 
 
 
 
 <%	if (user!=null) { %>
<form  action="loginServlet" method="post" name="formLogout">

 
 <img alt="img" src="img/avatarIco.png" onclick="submitForm()" class="avatarLogout">
<input id="hidden" type="hidden" name="action" value="logout"/>
 
<a href="mostraCarrello">
 
<img alt="carrello" src="img/icoCarrello.png" id="carrello" >
 
</a>
</form>
<%}else{ %>
 <img alt="img" src="img/avatarIco.png" class="avatarLogout"  onclick="document.getElementById('divForm').style.display='block'">

<a href="mostraCarrello">
<img alt="carrello" src="img/icoCarrello.png"  id="carrello" >
</a>
<%} %>

</div>
 

  <form action="ProdottiSC_Servlet" method="post">
  <input class="barraRicerca" type="text" name="BarraDiRicerca" onchange="Completa()" placeholder="     Che cosa stai cercando?">
  </form>
 
	</nav>
</header>
 
 
<!-- for desktop -->
 
<header class="headerForDesktop">
 
<nav class="navBar">
<div class="contenitoreLogo">
 
<a href="/PWTuttoMare/homeJSP.jsp" ><img id="logo" alt="logoUfficiale" src="img/logoFinito.png"></a>
   <form action="ProdottiSC_Servlet" method="post">
  <input class="barraRicerca" type="text" name="BarraDiRicerca" onchange="Completa()" placeholder="     Che cosa stai cercando?">
  </form>
 
 
 <%	if (user!=null) { %>
<form  action="/PWTuttoMare/loginServlet" method="post">
 
<button type="submit" class="bottoneLogout" >Logout</button>
 
<input id="hidden" type="hidden" name="action" value="logout"/>
 
<a href="mostraCarrello">
 
<img alt="carrello" src="img/icoCarrello.png" class="iconeBarraMenu" >
 
</a>
</form>
<%}else{ %>
<button type="submit" class="bottoneLogin" onclick="document.getElementById('divForm').style.display='block'">Login</button>
<a href="mostraCarrello">
<img alt="carrello" src="img/icoCarrello.png" class="iconeBarraMenu" >
</a>
<%} %>

</div>
 
<ul class="topnav">
  <li><a href="/PWTuttoMare/homeJSP.jsp">Home</a></li>
  <li class="sottoMenu"><a href="#eventi">Eventi</a>
  <li class="sottoMenu"><a href="#info">Informazioni</a>
 
  <div class="contenitore_sottoMenu">
      <a href="#">Chi siamo</a>
      <a href="/PWTuttoMare/DoveSiamo.jsp">Dove siamo</a>
      <a href="#">Termini e Condizioni</a>
    </div>
  </li>
 
 <%if(user!=null){ %>
<li><a href="/PWTuttoMare/ModificaProfilo?user=<%=user.getEmail()%>">Profilo</a></li>
<li ><a href="/PWTuttoMare/AcquistiEffettuati">I miei ordini</a></li>
<%} %>
  
  <%if(user!=null){
	if(user.isAdmin()){
	%>
		<li class="sottoMenu"><a href="/PWTuttoMare/admin/menuAdmin.jsp">Pannello Admin</a>
		
		  <div class="contenitore_sottoMenu">
					<a href="/PWTuttoMare/CategoriaControl">Aggiungi Categoria</a>
                    <a href="/PWTuttoMare/SottoCatControl">Aggiungi Sotto Categoria</a>
					<a href="/PWTuttoMare/ProdottiControl">Aggiungi Prodotto</a>
					<a href="/PWTuttoMare/InviaOrdini">Invia Ordini</a>
					<a href="/PWTuttoMare/VisualizzaMetodiPagamento"> Aggiungi metodi di pagamento </a>
					<a href="/PWTuttoMare/VisualizzaSpeseAdmin">Aggiungi tipi di spedizione</a> 
					<a href="/PWTuttoMare/ListaUtenti">Visualizza utenti registrati</a> 
					<a href="/PWTuttoMare/InviaOrdini">Visualizza gli ordini da inviare</a>
					<a href="/PWTuttoMare/ModificaCategoriaS">Modifica categotia</a> 
					<a href="/PWTuttoMare/ModificaProdottoS">Modifica prodotti</a>
					<a href="/PWTuttoMare/ModificaSottoCS">Modifica sotto categorie</a>

				</div>
		</li>


<%}} %> 
 
  
  </ul>
 
	</nav>
</header>
 <%	if (user==null) { %>
 
<div id="divForm" class="classDivForm">
<div id="inputForm">
  <form action="loginServlet" method="post">
  <div id="contenuto">
  <div id="chiudiFinestra" onclick="document.getElementById('divForm').style.display='none'" >&times;</div>
  
       <p align="center" class="visualizza">Username</p>
      <input class="logPws" type="text" placeholder="Enter Username" name="usernameLogin" required>
 
      <p align="center" class="visualizza">Password</p>
      <input class="logPws" type="password" placeholder="Enter Password" name="pswLogin" required>
     <br>
      <div id="divisore">
        <button  type="submit" class="buttonLoginForm"></button>
 
      <input class="inputCheckbox" type="checkbox" checked="checked" onclick="document.getElementById('divForm').style.display='none'"> Ricordami
 
      
 
        </div>
	  <input type="hidden" name="action" value="login"/>
</div>
  </form>
 
	<div id="lastDiv">
		<p>Non possiedi un account?
      <a class="aReg" onclick="avvioRegistrazione()">Registrati</a>
</p>
      <span class="psw">Hai dimenticato la <a href="#">password?</a></span>
</div>
</div>
</div>
 
  <%@include file="formRegistrazione.jsp"%>
   <% } %> 
 

 <script>
function submitForm() {
document.formLogout.action="loginServlet";
document.formLogout.submit();

} 

</script>
 
<script>
function avvioRegistrazione() {
	document.getElementById('divForm').style.display='none';
	document.getElementById('divFormRegistrazione').style.display='block';
}
</script>
 
</body>
</html>
 
