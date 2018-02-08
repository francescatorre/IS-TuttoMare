<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
 <link rel="stylesheet" type="text/css" href="/PWTuttoMare/css/pannelloControllo.css">
 
</head>
<body>
<%@include file="../barra.jsp" %>


<ul id="principale">



<li>
<p>Gestisci gli utenti</p>
<ul id="Sezione">
<li><a href="/PWTuttoMare/ListaUtenti">Visualizza lista utenti registrati</a>  </li>
<li><a href="/PWTuttoMare/VisualizzaMessaggiCliente">Visualizza messaggi degli utenti</a>  </li>
</ul>
</li>

<li>
<p>Gestione negozio</p>
<ul id="Sezione">
<li>  <a href="/PWTuttoMare/InviaOrdini">Visualizza gli ordini da inviare</a></li>
<li>  <a href="/PWTuttoMare/TuttiGliOrdini">Visualizza tutti gli ordini</a></li>

</ul>
</li>

<li>
<p>Mofidica metodi di pagamento e spedizioni</p>
<ul id="Sezione">
<li> <a href="/PWTuttoMare/VisualizzaSpeseAdmin"> Aggiungi spese spedizione</a> </li>

<li> <a href="/PWTuttoMare/VisualizzaMetodiPagamento"> Aggiungi metodo pagamento</a> </li>
</ul>
</li>



<li>
<p>Modifica prodotti</p>
<ul id="Sezione">
<li><a href="/PWTuttoMare/ProdottiControl"> Aggiungi Prodotto </a></li>

<li> <a href="/PWTuttoMare/CategoriaControl"> Aggiungi Categoria</a> </li>

<li> <a href="/PWTuttoMare/SottoCatControl"> Aggiungi SottoCategoria</a> </li>

<li> <a href="/PWTuttoMare/ModificaProdottoS"> Modifica Prodotto</a> </li>

<li> <a href="/PWTuttoMare/ModificaCategoriaS"> Modifica Categoria</a> </li>

<li> <a href="/PWTuttoMare//ModificaSottoCS"> Modifica Sotto Categoria</a> </li>


</ul>
</li>


</ul>

<%@include file="../footerHome.jsp" %>

</body>




</html>