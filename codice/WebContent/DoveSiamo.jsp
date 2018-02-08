<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/DoveSiamo.css">
<title>Dove Siamo</title>
</head>
<body>
<%@include file="barra.jsp" %>

<section class="infoNegozio col-12">
  <div class="Descrizione col-7">
    <p> La grande esperienza e professionalit&agrave;  dei negozi <strong>Tutto Mare </strong> &egrave; finalmente 
        sbarcata su Internet!</p>
    <p> I punti vendita di <strong> San Marco Di Castellabate (Sa)</strong> e di <strong> Santa Maria Di Castellabate (Sa)</strong>si sono sviluppati e sono diventati 
        specialisti nelle vendite online e per corrispondenza.</p>
    <p> Ora anche su Internet la consueta competenza di uno staff specializzato 
        ed un vasto assortimento di tutte le migliori marche del settore a prezzi mai visti.
    </p> 
 
    <p id="Tel">PER INFO SUL SITO E SUI PRODOTTI TEL. 0974961862 - 09741848628 </p>
  </div>

  <div class="imgNegozio col-5 col-m-12">
    <img id="imgNegozio" src=img/negozio.jpg alt="Immaggine Negozio">
  </div>
</section>
 
<div class="barraSep col-12 col-m-12">
   <h3>PUNTI VENDITA</h3>
</div>

<section class="contenitoreMappa col-12 col-m-12">

  <div class="descrizioneMappa col-7 col-m-7 "> 
    <p> Corso Umberto I,San Marco, Campania (Sa)</p>
    <p> <strong> Orario di Apertura:</strong> dal Lunedi al Venerdi, 9:00-13:00/15:00-20:00  </p>
    <hr> 
   
    <p>Via Lungomare Pepi,99, Santa Maria, Campania (Sa)</p>
    <p> <strong> Orario di Apertura:</strong> dal Lunedi al Venerdi, 9:00-13:00/15:00-20:00  </p>
    <hr> 
  </div> 

  <div class="Mappa col-5 col-m-5">
   <div id="map" class="col-12 col-m-12"></div> 
  </div>

</section>

<!-- Funzione per la visualizzazione della mappa -->
<script>
  function myMap() {
    var mapCanvas = document.getElementById("map");
    var coordinate1 = new google.maps.LatLng(40.267295, 14.940776); 
    var coordinate = new google.maps.LatLng(40.291455, 14.946331); 
    var OpzioniMappa = {center: coordinate1, zoom: 12};
    var map = new google.maps.Map(mapCanvas,OpzioniMappa);
  
    var marker = new google.maps.Marker({
     position: coordinate1,
     animation: google.maps.Animation.BOUNCE
    });
    marker.setMap(map);
    
    var infowindow = new google.maps.InfoWindow({
      content: "Corso Umberto I,San Marco, Campania"
    });
    infowindow.open(map,marker);
		  
    var marker2 = new google.maps.Marker({
      position: coordinate,
      animation: google.maps.Animation.BOUNCE
    });
    marker2.setMap(map);
			    
    var infowindow = new google.maps.InfoWindow({
      content: "Via Lungomare Pepi,99, Santa Maria, Campania"
    });
	infowindow.open(map,marker2);	  
}
</script>
<!-- API fornito da google  -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDF-ugcP-Nj7GE2yVDF9UZu8XhLZfhmXMI&callback=myMap"></script>




<%@include file="footerHome.jsp" %>

</body>
</html>