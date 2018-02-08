<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
<link rel="stylesheet" type="text/css" href="css/registrazioneStyle.css">

</head>
<body>

<div id="divFormRegistrazione" class="classDivFormRegistrazione">
  
  <form class="formLoginRegistrazione" ACTION="SaveUserDatabase" method="post" name="modulo" enctype="multipart/form-data">
  <span onclick="document.getElementById('divFormRegistrazione').style.display='none'" class="closeRegistrazione" title="Close Modal">&times;</span>


<ul id="lista">

<li><p class="tipoDescrizione">Dati nuovo account</p><li>


   <li>  <div class="imgcontainerRegistrazione">
<ul>
<li><p class="label">Inserisci immagine</p></li>
 
<li><img id="output" class="avatarRegistrazione"/></li>
	
<li><input  type="file" accept="image/*" onchange="loadFile(event)" required="required" name="photo"></li>
</ul>
    </div>
 </li>

 <li> <label class="label">E-mail:</label></li>
 <li>  <input type="text" class="inputRegistrazione" onblur="ControllaEmail()" required="required" placeholder="Enter your email" id="emaili" name="email"></li>

<li>      <label class="label">Nuova Password:</label></li>
<li>      <input class="inputRegistrazione" type="password"  id="nPassword" required="required"  placeholder="Enter Password" name="psw"></li>
<li>      <label class="label"> Conferma nuova Password:</label></li>
<li>      <input class="inputRegistrazione" type="password"required="required" id="cPassword" onblur="ControllaPassword()"  placeholder="Repet password" name="password"></li>

<li><p class="tipoDescrizione">Dati Anagrafici</p></li>
<li><label class="label">Nome:</label><li>
<li>   <input class="inputRegistrazione" type="text" required="required" placeholder="Inserisci il tuo nome" name="nome" required></li>

<li> <label class="label">Cognome:</label></li>
<li>  <input class="inputRegistrazione" type="text" required="required"  placeholder="Inserisci il tuo cognome" name="cognome" required></li>
<li> <label class="label">Sesso:</label>
 <input class="radio" type="radio" name="sesso" required="required"  value="F"> F
 <input class="radio" type="radio" name="sesso" required="required" value="M"> M
</li>

<li id="desktop"><label class="label">Data di nascita:</label>
<input  class="data"  type="number" min="1" max="31" required="required"  name="giornoNascita" placeholder="gg">
<input class="data" type="number" min="1" max="12" required="required"  name="meseNascita" placeholder="mm">
<input class="data" type="number" min="1940" max="" required="required"  name="annoNascita" placeholder="aaaa">
<li>

<li id="hidden"><label class="label">Data di nascita:</label></li>
<li id="hidden"><input  class="data"  type="number" required="required"  min="1" max="31" name="giornoNascita" placeholder="gg">
<input class="data" type="number" min="1" max="12" required="required"  name="meseNascita" placeholder="mm">
<input class="data" type="number" min="1940" max="" required="required" name="annoNascita" placeholder="aaaa">
<li>



<li><label class="label">ProvinciaNascita(Sigla):</label></li>
<li><select class="inputRegistrazione" required="required" contenteditable="true" name="provinciaNascita">
<option  >Seleziona Provincia</option>
     <option value="AG">AG</option>
    <option value="AL">Al </option>
    <option value="AN">AN </option>
    <option value="AO">AO </option>
    <option value="AQ">AQ </option>
    <option value="AR">AR </option>
    <option value="AP">AP</option>
    <option value="AT">AT </option>
    <option value="AV">AV </option>
    <option value="BA">BA </option>
    <option value="BT">BT </option>
    <option value="BL">BL </option>
    <option value="BN">BN </option>
    <option value="BG">BG </option>
    <option value="BI">BI </option>
    <option value="BO">BO </option>
    <option value="BZ">BZ </option>
    <option value="BS">BS </option>
    <option value="BR">BR </option>
    <option value="CA">CA </option>
    <option value="CL">CL</option>
    <option value="CB">CB </option>
    <option value="CI">CI </option>
    <option value="CE">CE </option>
    <option value="CT">CT </option>
    <option value="CZ">CZ </option>
    <option value="CH">CH</option>
    <option value="CO">CO </option>
    <option value="CS">CS </option>
    <option value="CR">CR </option>
    <option value="KR">KR </option>
    <option value="CN">CN </option>
    <option value="EN">EN </option>
    <option value="FM">FM </option>
    <option value="FE">FE </option>
    <option value="FI">FI </option>
    <option value="FG">FG </option>
    <option value="FC">FC </option>
    <option value="FR">FR </option>
    <option value="GE">GE </option>
    <option value="GO">GO </option>
    <option value="GR">GR</option>
    <option value="IM">IM </option>
    <option value="IS">IS </option>
    <option value="SP">SP </option>
    <option value="LT">LT </option>
    <option value="LE">LE </option>
    <option value="LC">LC </option>
    <option value="LI">LI </option>
    <option value="LO">LO</option>
    <option value="LU">LU </option>
    <option value="MC">MC </option>
    <option value="MN">MN </option>
    <option value="MS">MS </option>
    <option value="MT">MT </option>
    <option value="VS">VS </option>
    <option value="ME">ME </option>
    <option value="MI">MI </option>
    <option value="MO">MO </option>
    <option value="MB">MB </option>
    <option value="NA">NA </option>
    <option value="NO">NO </option>
    <option value="NU">NU </option>
    <option value="OG">OG</option>
    <option value="OT">OT </option>
    <option value="OR">OR </option>
    <option value="PD">PD </option>
    <option value="PA">PA </option>
    <option value="PR">PR </option>
    <option value="PV">PV </option>
    <option value="PG">PG </option>
    <option value="PU">PU </option>
    <option value="PE">PE </option>
    <option value="PC">PC </option>
    <option value="PI">PI </option>
    <option value="PT">PT </option>
    <option value="PN">PN </option>
    <option value="PZ">PZ</option>
    <option value="PO">PO </option>
    <option value="RG">RG </option>
    <option value="RA">RA </option>
    <option value="RC">RC </option>
    <option value="RE">RE </option>
    <option value="RI">RI </option>
    <option value="RN">RN</option>
    <option value="RM">RM </option>
    <option value="RO">RO </option>
    <option value="SA">SA </option>
    <option value="SS">SS </option>
    <option value="SV">SV </option>
    <option value="SI">SI </option>
    <option value="SR">SR</option>
    <option value="SO">SO</option>
    <option value="TA">TA </option>
    <option value="TE">TE </option>
    <option value="TR">TR </option>
    <option value="TO">TO </option>
    <option value="TP">TP </option>
    <option value="TN">TN </option>
    <option value="TV">TV </option>
    <option value="TS">TS</option>
    <option value="UD">UD </option>
    <option value="VA">VA </option>
    <option value="VE">VE </option>
    <option value="VB">VB </option>
    <option value="VC">VC </option>
    <option value="VR">VR </option>
    <option value="VV">VV</option>
    <option value="VI">VI </option>
    <option value="VT">VT</option>
   
</select>

</li>

<li><label class="label">Luogo nascita:</label></li>
<li><input class="inputRegistrazione" type="text" name="luogoNascita" placeholder="Inserisci il tuo luogo di nascita"></li>

<li><p class="tipoDescrizione">Residenza</p><li>

<li><label class="label">Stato di residezna:</label><li>
<li><select class="inputRegistrazione" required="required" contenteditable="true" name="statoResidenza" id="stato">
<option value="Afghanistan">Afghanistan </option>
    <option value="Albania">Albania </option>
    <option value="Algeria">Algeria </option>
    <option value="Andorra">Andorra </option>
    <option value="Angola">Angola </option>
    <option value="Anguilla">Anguilla </option>
    <option value="Antartide">Antartide </option>
    <option value="AntiguaBarbuda">Antigua e Barbuda </option>
    <option value="Antille Olandesi">Antille Olandesi </option>
    <option value="Antille Olandesi">Antille Olandesi </option>
    <option value="Argentina">Argentina </option>
    <option value="Armenia">Armenia </option>
    <option value="Aruba">Aruba </option>
    <option value="Australia">Australia </option>
    <option value="Austria">Austria </option>
    <option value="Azerbaigian">Azerbaigian </option>
    <option value="Bahamas">Bahamas </option>
    <option value="Bahrein">Bahrein </option>
    <option value="Bangladesh">Bangladesh </option>
    <option value="Barbados">Barbados </option>
    <option value="Belgio">Belgio </option>
    <option value="Belize">Belize </option>
    <option value="Benin">Benin </option>
    <option value="Bermuda">Bermuda </option>
    <option value="Bhutan">Bhutan </option>
    <option value="Bielorussia">Bielorussia </option>
    <option value="Bolivia">Bolivia </option>
    <option value="Bosnia-Erzegovina ">Bosnia-Erzegovina </option>
    <option value="Botswana">Botswana </option>
    <option value="Brasile">Brasile </option>
    <option value="Brunei">Brunei </option>
    <option value="Bulgaria">Bulgaria </option>
    <option value="Burkina Faso">Burkina Faso </option>
    <option value="Burundi">Burundi </option>
    <option value="Cambogia">Cambogia </option>
    <option value="Camerun">Camerun </option>
    <option value="Canada">Canada </option>
    <option value="Capo Verde">Capo Verde </option>
    <option value="Ciad">Ciad </option>
    <option value="Cile">Cile </option>
    <option value="Cina">Cina </option>
    <option value="Cipro">Cipro </option>
    <option value="Città del Vaticano">Città del Vaticano </option>
    <option value="Colombia">Colombia </option>
    <option value="Comore">Comore </option>
    <option value="Congo">Congo </option>
    <option value="Corea">Corea </option>
    <option value="Corea del Nord">Corea del Nord </option>
    <option value="Costa d'Avorio">Costa d'Avorio </option>
    <option value="Costa Rica">Costa Rica </option>
    <option value="Croazia">Croazia </option>
    <option value="Cuba">Cuba </option>
    <option value="Danimarca">Danimarca </option>
    <option value="Dominica">Dominica </option>
    <option value="Ecuador">Ecuador </option>
    <option value="Egitto">Egitto </option>
    <option value="El Salvador ">El Salvador </option>
    <option value="Emirati Arabi">Emirati Arabi </option>
    <option value="Eritrea">Eritrea </option>
    <option value="Estonia">Estonia </option>
    <option value="Etiopia">Etiopia </option>
    <option value="Fiji">Fiji </option>
    <option value="Filippine">Filippine </option>
    <option value="Finlandia">Finlandia </option>
    <option value="Francia">Francia </option>
    <option value="Gabon">Gabon </option>
    <option value="Gambia">Gambia </option>
    <option value="Georgia">Georgia </option>
    <option value="Germania">Germania </option>
    <option value="Ghana">Ghana </option>
    <option value="Giamaica">Giamaica </option>
    <option value="Giappone">Giappone </option>
    <option value="Gibilterra">Gibilterra </option>
    <option value="Gibuti">Gibuti </option>
    <option value="Giordania">Giordania </option>
    <option value="Gran Bretagna">Gran Bretagna </option>
    <option value="Grecia">Grecia </option>
    <option value="Grenada">Grenada </option>
    <option value="Groenlandia">Groenlandia </option>
    <option value="Guadalupe">Guadalupe </option>
    <option value="Guam">Guam </option>
    <option value="Guatemala">Guatemala </option>
    <option value="Guiana francese">Guiana francese </option>
    <option value="Guinea">Guinea </option>
    <option value="Guinea equatoriale">Guinea equatoriale </option>
    <option value="Guinea-Bissau">Guinea-Bissau </option>
    <option value="Guyana">Guyana </option>
    <option value="Haiti">Haiti </option>
    <option value="Honduras">Honduras </option>
    <option value="Hong Kong SAR ">Hong Kong SAR </option>
    <option value="India">India </option>
    <option value="Indonesia">Indonesia </option>
    <option value="Iran">Iran </option>
    <option value="Iraq">Iraq </option>
    <option value="Irlanda">Irlanda </option>
    <option value="Islanda">Islanda </option>
    <option selected="selected" value="Italia">Italia </option>
    <option value="Iugoslavia">Iugoslavia </option>
    <option value="Kazakistan">Kazakistan </option>
    <option value="Kenya">Kenya </option>
    <option value="Kirghizistan">Kirghizistan </option>
    <option value="Kiribati">Kiribati </option>
    <option value="Kuwait">Kuwait </option>
    <option value="Laos">Laos </option>
    <option value="Lesotho">Lesotho </option>
    <option value="Lettonia">Lettonia </option>
    <option value="Libano">Libano </option>
    <option value="Liberia">Liberia </option>
    <option value="Libia">Libia </option>
    <option value="Liechtenstein">Liechtenstein </option>
    <option value="Lituania">Lituania </option>
    <option value="Lussemburgo">Lussemburgo </option>
    <option value="Macao">Macao </option>
    <option value="Macedonia">Macedonia </option>
    <option value="Madagascar">Madagascar </option>
    <option value="Malawi">Malawi </option>
    <option value="Maldive">Maldive </option>
    <option value="Malesia">Malesia </option>
    <option value="Mali">Mali </option>
    <option value="Malta">Malta </option>
    <option value="Marocco">Marocco </option>
    <option value="Martinica">Martinica </option>
    <option value="Mauritania">Mauritania </option>
    <option value="Mauritius">Mauritius </option>
    <option value="Mayotte">Mayotte </option>
    <option value="Messico">Messico </option>
    <option value="Micronesia">Micronesia </option>
    <option value="Moldavia">Moldavia </option>
    <option value="Monaco">Monaco </option>
    <option value="Mongolia">Mongolia </option>
    <option value="Montserrat">Montserrat </option>
    <option value="Mozambico">Mozambico </option>
    <option value="Myanmar">Myanmar </option>
    <option value="Namibia">Namibia </option>
    <option value="Nauru">Nauru </option>
    <option value="Nepal">Nepal </option>
    <option value="Nicaragua">Nicaragua </option>
    <option value="Niger">Niger </option>
    <option value="Nigeria">Nigeria </option>
    <option value="Niue">Niue </option>
    <option value="Norvegia">Norvegia </option>
    <option value="Nuova Caledonia">Nuova Caledonia </option>
    <option value="Nuova Zelanda">Nuova Zelanda </option>
    <option value="Oman">Oman </option>
    <option value="Paesi Bassi">Paesi Bassi </option>
    <option value="PPakistan">Pakistan </option>
    <option value="Palau">Palau </option>
    <option value="Panama">Panama </option>
    <option value="Paraguay">Paraguay </option>
    <option value="Perù">Perù </option>
    <option value="Pitcairn">Pitcairn </option>
    <option value="Polinesia francese">Polinesia francese </option>
    <option value="Polonia">Polonia </option>
    <option value="Portogallo">Portogallo </option>
    <option value="Puerto Rico">Puerto Rico </option>
    <option value="Qatar">Qatar </option>
    <option value="Repubblica Ceca">Repubblica Ceca </option>
    <option value="Repubblica Centrafricana">Repubblica Centrafricana </option>
    <option value="Repubblica Dominicana">Repubblica Dominicana </option>
    <option value="Reunion">Reunion </option>
    <option value="Romania">Romania </option>
    <option value="Ruanda">Ruanda </option>
    <option value="Russia">Russia </option>
    <option value="Saint Kitts e Nevis">Saint Kitts e Nevis </option>
    <option value="Saint Lucia">Saint Lucia </option>
    <option value="Samoa">Samoa </option>
    <option value="Samoa Americane">Samoa Americane </option>
    <option value="San Marino ">San Marino </option>
    <option value="Sant'Elena">Sant'Elena </option>
    <option value="Senegal">Senegal </option>
    <option value="Seychelles">Seychelles </option>
    <option value="Sierra Leone">Sierra Leone </option>
    <option value="Singapore">Singapore </option>
    <option value="Siria">Siria </option>
    <option value="Slovacchia">Slovacchia </option>
    <option value="Slovenia">Slovenia </option>
    <option value="Somalia">Somalia </option>
    <option value="Spagna">Spagna </option>
    <option value="Sri Lanka">Sri Lanka </option>
    <option value="Stati Uniti">Stati Uniti </option>
    <option value="Sudafrica">Sudafrica </option>
    <option value="Sudan">Sudan </option>
    <option value="Suriname">Suriname </option>
    <option value="Svezia">Svezia </option>
    <option value="Svizzera">Svizzera </option>
    <option value="Swaziland">Swaziland </option>
    <option value="Tagikistan">Tagikistan </option>
    <option value="Tailandia">Tailandia </option>
    <option value="Taiwan">Taiwan </option>
    <option value="Tanzania">Tanzania </option>
    <option value="Timor Est">Timor Est </option>
    <option value="Togo">Togo </option>
    <option value="Tokelau">Tokelau </option>
    <option value="Tonga">Tonga </option>
    <option value="Trinidad e Tobago">Trinidad e Tobago </option>
    <option value="Tunisia">Tunisia </option>
    <option value="Turchia">Turchia </option>
    <option value="Turkmenistan">Turkmenistan </option>
    <option value="Tuvalu">Tuvalu </option>
    <option value="Ucraina">Ucraina </option>
    <option value="Uganda">Uganda </option>
    <option value="Ungheria">Ungheria </option>
    <option value="Uruguay">Uruguay </option>
    <option value="Uzbekistan">Uzbekistan </option>
    <option value="Vanuatu">Vanuatu </option>
    <option value="Venezuela">Venezuela </option>
    <option value="Vietnam">Vietnam </option>
    <option value="Yemen">Yemen </option>
    <option value="Zambia">Zambia </option>
    <option value="Zimbabwe">Zimbabwe </option>
</select>

<li>

<li><label class="label">Provincia di residenza:</label><li>
<li><select class="inputRegistrazione"  required="required" contenteditable="true" id="provincia" name="provincia" > 
    <option value="Seleziona Provincia">Seleziona Provincia</option>
    <option value="Agrigento">Agrigento</option>
    <option value="Alessandria">Alessandria </option>
    <option value="Ancona">Ancona </option>
    <option value="Aosta">Aosta </option>
    <option value="Aquila">Aquila </option>
    <option value="Arezzo">Arezzo </option>
    <option value="Ascoli-Piceno">Ascoli-Piceno</option>
    <option value="Asti">Asti </option>
    <option value="Avellino">Avellino </option>
    <option value="Bari">Bari </option>
    <option value="Barletta-Andria-Trani">Barletta-Andria-Trani </option>
    <option value="Belluno">Belluno </option>
    <option value="Benevento">Benevento </option>
    <option value="Bergamo">Bergamo </option>
    <option value="Biella">Biella </option>
    <option value="Bologna">Bologna </option>
    <option value="Bolzano">Bolzano </option>
    <option value="Brescia">Brescia </option>
    <option value="Brindisi">Brindisi </option>
    <option value="Cagliari">Cagliari </option>
    <option value="Caltanissetta">Caltanissetta</option>
    <option value="Campobasso">Campobasso </option>
    <option value="Carbonia Iglesias">Carbonia Iglesias </option>
    <option value="Caserta">Caserta </option>
    <option value="Catania">Catania </option>
    <option value="Catanzaro">Catanzaro </option>
    <option value="Chieti">Chieti</option>
    <option value="Como">Como </option>
    <option value="Cosenza">Cosenza </option>
    <option value="Cremona">Cremona </option>
    <option value="Crotone">Crotone </option>
    <option value="Cuneo">Cuneo </option>
    <option value="Enna">Enna </option>
    <option value="Fermo">Fermo </option>
    <option value="Ferrara">Ferrara </option>
    <option value="Firenze">Firenze </option>
    <option value="Foggia">Foggia </option>
    <option value="Forli-Cesena">Forli-Cesena </option>
    <option value="Frosinone">Frosinone </option>
    <option value="Genova">Genova </option>
    <option value="Gorizia">Gorizia </option>
    <option value="Grosseto">Grosseto</option>
    <option value="Imperia">Imperia </option>
    <option value="Isernia">Isernia </option>
    <option value="La-Spezia">La-Spezia </option>
    <option value="Latina">Latina </option>
    <option value="Lecce">Lecce </option>
    <option value="Lecco">Lecco </option>
    <option value="Livorno">Livorno </option>
    <option value="Lodi">Lodi</option>
    <option value="Lucca">Lucca </option>
    <option value="Macerata">Macerata </option>
    <option value="Mantova">Mantova </option>
    <option value="Massa-Carrara">Massa-Carrara </option>
    <option value="Matera">Matera </option>
    <option value="Medio Campidano">Medio Campidano </option>
    <option value="Messina">Messina </option>
    <option value="Milano">Milano </option>
    <option value="Modena">Modena </option>
    <option value="Monza-Brianza">Monza-Brianza </option>
    <option value="Napoli">Napoli </option>
    <option value="Novara">Novara </option>
    <option value="Nuoro">Nuoro </option>
    <option value="Ogliastra">Ogliastra</option>
    <option value="Olbia Tempio">Olbia Tempio </option>
    <option value="Oristano">Oristano </option>
    <option value="Padova">Padova </option>
    <option value="Palermo">Palermo </option>
    <option value="Parma">Parma </option>
    <option value="Pavia">Pavia </option>
    <option value="Perugia">Perugia </option>
    <option value="Pesaro-Urbino">Pesaro-Urbino </option>
    <option value="Pescara">Pescara </option>
    <option value="Piacenza">Piacenza </option>
    <option value="Pisa">Pisa </option>
    <option value="Pistoia">Pistoia </option>
    <option value="Pordenone">Pordenone </option>
    <option value="Potenza">Potenza</option>
    <option value="Prato">Prato </option>
    <option value="Ragusa">Ragusa </option>
    <option value="Ravenna">Ravenna </option>
    <option value="Reggio-Calabria">Reggio-Calabria </option>
    <option value="Reggio-Emilia">Reggio-Emilia </option>
    <option value="Rieti">Rieti </option>
    <option value="Rimini">Rimini</option>
    <option value="Roma">Roma </option>
    <option value="Rovigo">Rovigo </option>
    <option value="Salerno">Salerno </option>
    <option value="Sassari">Sassari </option>
    <option value="Savona">Savona </option>
    <option value="Siena">Siena </option>
    <option value="Siracusa">Siracusa </option>
    <option value="Sondrio">Sondrio</option>
    <option value="Taranto">Taranto </option>
    <option value="Teramo">Teramo </option>
    <option value="Terni">Terni </option>
    <option value="Torino">Torino </option>
    <option value="Trapani">Trapani </option>
    <option value="Trento">Trento </option>
    <option value="Treviso">Treviso </option>
    <option value="Trieste">Trieste</option>
    <option value="Udine">Udine </option>
    <option value="Varese">Varese </option>
    <option value="Venezia">Venezia </option>
    <option value="Verbania">Verbania </option>
    <option value="Vercelli">Vercelli </option>
    <option value="Verona">Verona </option>
    <option value="Vibo-Valentia">Vibo-Valentia</option>
    <option value="Vicenza">Vicenza </option>
    <option value="Viterbo">Viterbo</option>
   
</select>
</li>

<li><label class="label">Citta:</label><li>
<li><select class="inputRegistrazione" required="required" contenteditable="true" id="citta" name="cittaResidenza"> 
<option>Seleziona Citt&agrave;</option>
</select></li>
<li><label class="label">Indirizzo:</label></li>
<li><input id="indirizzo" type="text" required="required"  name="indirizzoResidenza" placeholder="Inserisci la via dove risiedi">

<input id="civico" type="text" required="required"  name="numeroCivico" placeholder="civico"></li>

<li><label class="label">Cap residenza:</label></li>
<li><input class="inputRegistrazione" required="required" onblur="ControllaCap()" id="cap" type="text" name="capResidenza"></li>


<li><label class="label">Telefono:</label></li>
<li><input class="inputRegistrazione" type="text"required="required" id="telefono" onblur="ControllaTelefono()" name="telefonoResidenza"></li>


</ul>
 
        
      <button class="buttonFormRegistrazione" type="submit">Registrati</button>

  </form>
</div>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(document).ready(function() {

$('#provincia').change(function(event) {
	var provincia = $("select#provincia").val();
	$.get('JsonServlet', {
		provincia : provincia
	}, function(jsonResponse) {

	var select = $('#citta');
	select.find('option').remove();
 	  $.each(jsonResponse, function(index, value) {
	  $('<option>').val(value).text(value).appendTo(select);
      });
	});
	});
});

</script>


<script>

function ControllaEmail() {
    // Variabili associate ai campi del modulo
    var email = document.getElementById("emaili").value;
  
 
    // Espressione regolare dell'email
    var email_reg_exp =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      
    if ((!email.match(email_reg_exp)) || (email == "") || (email == "undefined")) {
        alert("Inserire un indirizzo email corretto.");
        email.value="";
    }

}
function ControllaPassword() {
    // Variabili associate ai campi del modulo
    var psw = document.getElementById("nPassword").value;
    var password = document.getElementById("cPassword").value;
   
 
    //Effettua il controllo sul campo PASSWORD
     if ((psw == "") || (psw == "undefined")) {
        alert("Il campo Password e' obbligatorio.");
        psw.value="";
        
    }
    //Effettua il controllo sul campo CONFERMA PASSWORD
    else if ((password == "") || (password == "undefined")) {
        alert("Il campo Conferma password &egrave; obbligatorio.");
        password.value="";
       
    }
  //Verifica l'uguaglianza tra i campi PASSWORD e CONFERMA PASSWORD
    else if (password != psw) {
        alert("La password confermata e' diversa da quella scelta, controllare.");
        password.value = "";
        
    }
}
function ControllaCap() {
   
    var capResidenza = document.getElementById("cap").value;

  //Effettua il controllo sul campo NUMERO CIVICO
    if ((isNaN(capResidenza)) || (capResidenza == "") || (capResidenza == "undefined")) {
        alert("Il campo Cap e' numerico ed obbligatorio.");
        capResidenza.value = "";
     
    }
}
function ControllaTelefono() {
	   
    var telefono = document.getElementById("telefono").value;

  //Effettua il controllo sul campo NUMERO CIVICO
    if ((isNaN(telefono)) || (telefono == "") || (telefono == "undefined")) {
        alert("Il campo telefono e' numerico ed obbligatorio.");
       telefono.value = "";
        
    }
    else{
    	//INVIA IL MODULO
      
            document.modulo.action = "SaveUserDatabase";
            document.modulo.method="post";
            document.modulo.submit();
    }
}

</script>


</body>
</html>