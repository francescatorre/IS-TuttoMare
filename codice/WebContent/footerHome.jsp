<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/PWTuttoMare/css/footer.css">

<title>footer</title>
</head>
<body>
<footer class="col-12 col-m-12">
   
    <div class="divContattaci col-6 col-m-6">
       <h2> CONTATTACI:</h2>
       <form action="Contattaci" method="get" name="Form" onsubmit="return Modulo(this)">
           <p>Nome:*</p> 
           <input type="text" name="Nome" maxlength="11" > 
           <p>Cognome:*</p>
           <input type="text" name="Cognome" maxlength="11" >
           <p>Email:*</p>  
           <p id="Errore" style="display:none; color:red; border:1px solid red;"> Inserisci un Email valida </p>
           <input type="text" name="Email" maxlength="30" >
           <p> Commento:*</p>
           <textarea rows="7" cols="32" name="Commento"></textarea><br>
           <input type="submit" value="Invia">
       </form>
     </div>
    
     <div class="link_utili col-6 col-m-6" > 
        
        <div class="seguici col-12 col-m-12">
          <h2 class="col-12 col-m12"> SEGUICI ANCHE SU FACEBOOK</h2>
    
         <!-- Plug-in di facebook per incorporare una pagina facebook -->
         <div class="fb-page " data-href="https://www.facebook.com/Tuttomare-766810430012683/?pnref=story" 
             data-tabs="timeline" data-width="250" data-height="80" 
             data-small-header="false" data-adapt-container-width="true" 
             data-hide-cover="false" data-show-facepile="true">
             <blockquote cite="https://www.facebook.com/Tuttomare-766810430012683/?pnref=story" 
             class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/Tuttomare-766810430012683/?pnref=story">Tuttomare</a></blockquote>
         </div>
        </div>
      
        <div class="categorieTop col-6 col-m-6">
         <h2> CATEGORIE TOP</h2>
         <ul>
           <li> <a href="" >Articoli Traina</a></li>
           <li><a href="">  Ancorette</a> </li>
           <li><a href="">Canne </a></li>
           <li><a href="">Esche e Pasture</a></li>
         </ul>
        </div>
      
     
     
       <div class="informative col-6 col-m-6">
        <h2> INFORMATIVE</h2>
        <ul>
          <li><a href="privacy.html"> Privacy</a></li><!-- si puo generare online tramite iubenda -->
          <li> <a href="cookie.html"> Cookie</a></li>
          <li> <a href="spedizione.html"> Spedizione</a></li>
          <li> <a href="pagamenti.html"> Pagamenti</a></li>
          <li> <a href="garanzia.html"> Garanzia</a></li>
        </ul> 
       </div>
  
      <button onclick="topFunction()" id="BottoneTop" title="Vai all'inizio della pagina">
      </button>
                          
 </div>
   
 <div class="copyright col-12">
   &copy; <span> 2017, Tutto Mare - Sara Guerriero . Tutti i diritti riservati.</span>
   <br> 
 </div> 
<script type="text/javascript">


function Modulo() {
    // Variabili associate ai campi del modulo
    var Nome = document.Form.Nome.value;
    var Cognome = document.Form.Cognome.value;
    var Email = document.Form.Email.value;
    var Commento= document.Form.Commento.value;
   
 
    // Espressione regolare dell'email
    var email_reg_exp = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-]{2,})+\.)+([a-zA-Z0-9]{2,})+$/;
      
    if (!email_reg_exp.test(Email) || (Email == "") || (Email == "undefined")) {
    	alert("email non valida.");
        document.Form.Email.select();
        return false;
    }
    
    //Effettua il controllo sul campo NOME
    else if ((Nome == "") || (Nome == "undefined")) {
    	alert("Il campo nome e' obbligatorio.");
        document.Form.Nome.focus();
      
        return false;
    }
    
    //Effettua il controllo sul campo COGNOME
    else if ((Cognome == "") || (Cognome == "undefined")) {
        alert("Il campo Cognome e' obbligatorio.");
        document.Form.Cognome.focus();
        return false;
    }
   
    //Effettua il controllo sul campo Messaggio
    else if ((Commento == "") || (Commento == "undefined")) {
        alert("Il campo Commento e' obbligatorio.");
        document.Form.Commento.focus();
        return false;
    }
    
    //INVIA IL MODULO
    else {

        document.Form.action = "Contattaci";
        document.Form.submit();
    }
}


</script>
<script>

// Funzione per far tornare la pagina su quando l'utente clicca il bottone
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
</script>

<!--  plug-in di facebook SDK Java script-->
 <div id="fb-root"></div>
  <script>(function(d, s, id) {
   var js, fjs = d.getElementsByTagName(s)[0];
   if (d.getElementById(id)) return;
   js = d.createElement(s); js.id = id;
   js.src = "//connect.facebook.net/it_IT/sdk.js#xfbml=1&version=v2.8";
   fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));</script>

</footer>
</body>
</html>