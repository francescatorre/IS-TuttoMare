package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account.AddressData;
import model.Account.AddressDataDao;
import model.Account.UserData;
import model.Amministratore.PaymentData;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.ShipmentData;
import model.Amministratore.ShipmentDataDao;
import model.ServiziUtente.CarrelloData;
import model.ServiziUtente.CarrelloDataDao;
import model.ServiziUtente.OrderData;
import model.ServiziUtente.OrderDataDao;
import model.ServiziUtente.StatusOrderData;

@WebServlet("/DatiAcquisto")
public class DatiAcquisto extends HttpServlet {

	private static final long serialVersionUID = 1L;
	AddressData i=new AddressData();
	AddressDataDao databaseIndirizzi = new AddressDataDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		UserData user = (UserData) session.getAttribute("user");

		String ceck = req.getParameter("check");

		if (ceck.equalsIgnoreCase("invia a questo indirizzo")) {
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String telefono = req.getParameter("telefono");
			String stato = req.getParameter("stato");

			String indirizzoFatturazione = req.getParameter("indirizzoFatturazione");
			String citta = req.getParameter("citta");
			String provincia = req.getParameter("provincia");
			String cap = req.getParameter("cap");
			String civico = req.getParameter("civico");

		
			i.setNome(FiltraInput(nome));
			i.setCognome(FiltraInput(cognome));
			i.setCitta(FiltraInput(citta));
			i.setTelefono(FiltraInput(telefono));
			i.setVia(FiltraInput(indirizzoFatturazione));
			i.setNumeroCivico(FiltraInput(civico));
			i.setProvincia(FiltraInput(provincia));
			i.setEmail(user.getEmail());
			i.setCap(FiltraInput(cap));
			i.setStato(FiltraInput(stato));
			try {
				System.out.println("ciao");
				AddressData ver = databaseIndirizzi.doSaveAndReturn(i);
				System.out.println("ciao");
				if (ver == null) {
					System.out.println("hai aggiunto un nuovo indirizzo");
					databaseIndirizzi.doSave(i);
					i = databaseIndirizzi.doSaveAndReturn(i);
				} else {

					System.out.println("l'indirizzo esisteva gia" + i.toString());
					i.setIdIndirizzo(ver.getIdIndirizzo());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				AddressDataDao databaseAddress = new AddressDataDao();
				AddressData address = new AddressData();
				address.setIdIndirizzo(Integer.parseInt(ceck));

				i = databaseAddress.searchAddressByKey(address);

				System.out.println("indirizzo di spedizione" + i.toString());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		String idSpedizione = req.getParameter("spedizione");
		String conferma = req.getParameter("conferma");

		if (conferma != null) {

			System.out.println("Id spedizione" + idSpedizione);


			ShipmentData spedizione = new ShipmentData();
			spedizione.setIdSpedizione(Integer.parseInt(idSpedizione));
			ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
			try {
				spedizione = databaseSpedizione.getShipmentDataById(spedizione);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String metodoP = req.getParameter("metodoPagamento");
			PaymentData pagamento = new PaymentData();
			pagamento.setIdPagamento(Integer.parseInt(metodoP));

			ProdottiDataDao databaseProdotti = new ProdottiDataDao();

			try {
				boolean acquisto = false;

				double totale = 0;

				Collection<ProdottiData> prodottiAcquistati = new ArrayList<>();
				CarrelloDataDao databaseCarrello = new CarrelloDataDao();
				ArrayList<CarrelloData> carrello = databaseCarrello.getCarrello(user);
				Collection<OrderData> ordini = new ArrayList<>();
				OrderDataDao databaseOrdine = new OrderDataDao();
				int numero = databaseOrdine.getUltimoNumeroO();

				for (CarrelloData c : carrello) {

					ProdottiData prodotto = databaseProdotti.doRetrieveByKey(c.getIdProdotto());
					prodottiAcquistati.add(prodotto);
					OrderData ordine = new OrderData();
					ordine.setEmail(user.getEmail());

					StatusOrderData status = new StatusOrderData();
					status.setIdStatusOrdine(1);
					status.setStato("Pagato");
					ordine.setStatusOrdine(status.getIdStatusOrdine());
					ordine.setIdSpedizione(spedizione.getIdSpedizione());
					ordine.setIdPagamento(pagamento.getIdPagamento());
					ordine.setIdProdotto(prodotto.getIdProdotto());
					ordine.setIdIndirizzo(i.getIdIndirizzo());

					ordine.setQuantita((c.getQuantita()));

					ordine.setDataOrdine(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
					ordine.setNumeroOrdine(numero + 1);
					totale = totale + prodotto.getPrezzo() * ordine.getQuantita();
					ordine.setTotale(prodotto.getPrezzo() * ordine.getQuantita());
					ordini.add(ordine);

					int quantitaRimaste = prodotto.getQuantita() - ordine.getQuantita();
System.out.println("qu"+quantitaRimaste);
					if (quantitaRimaste >= 0) {
						prodotto.setQuantita(quantitaRimaste);

						databaseOrdine.doSave(ordine);
						databaseProdotti.doUpdate(prodotto, prodotto.getIdProdotto());

						System.out.println("Pagamento avvenuto" + ordine.toString());
						databaseCarrello.doDelete(c);

						System.out.println(prodotto.getQuantita());
						acquisto = true;
					}
				}
				if (acquisto == false) {
					totale = 0;
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAcquisto.jsp");
					dispatcher.forward(req, resp);
				} else {
					totale = totale + spedizione.getCosto();

					req.setAttribute("prodotti", prodottiAcquistati);
					req.setAttribute("totale", totale);
					req.setAttribute("spedizione", spedizione);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AcquistoCompletato.jsp");
					dispatcher.forward(req, resp);

				}
				System.out.println(totale);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();

		UserData user = (UserData) session.getAttribute("user");

		String ceck = req.getParameter("check");

		System.out.println("ceck" + ceck);
		if (ceck.equalsIgnoreCase("invia a questo indirizzo")) {
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String telefono = req.getParameter("telefono");
			String stato = req.getParameter("stato");

			String indirizzoFatturazione = req.getParameter("indirizzoFatturazione");
			String citta = req.getParameter("citta");
			String provincia = req.getParameter("provincia");
			String cap = req.getParameter("cap");
			String civico = req.getParameter("civico");

			i = new AddressData();
			i.setNome(FiltraInput(nome));
			i.setCognome(FiltraInput(cognome));
			i.setCitta(FiltraInput(citta));
			i.setTelefono(FiltraInput(telefono));
			i.setVia(FiltraInput(indirizzoFatturazione));
			i.setNumeroCivico(FiltraInput(civico));
			i.setProvincia(FiltraInput(provincia));
			i.setEmail(user.getEmail());
			i.setCap(FiltraInput(cap));
			i.setStato(FiltraInput(stato));
			try {
				AddressDataDao databaseIndirizzi = new AddressDataDao();
				AddressData ver = databaseIndirizzi.doSaveAndReturn(i);
				if (ver == null) {
					System.out.println("hai aggiunto un nuovo indirizzo");;
					databaseIndirizzi.doSave(i);
					i = databaseIndirizzi.doSaveAndReturn(i);
				} else {

					System.out.println("l'indirizzo esisteva gia" + i.toString());
					i.setIdIndirizzo(ver.getIdIndirizzo());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				AddressDataDao databaseAddress = new AddressDataDao();
				AddressData address = new AddressData();
				address.setIdIndirizzo(Integer.parseInt(ceck));

				i = databaseAddress.searchAddressByKey(address);

				System.out.println("indirizzo di spedizione" + i.toString());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		String idSpedizione = req.getParameter("spedizione");
		String conferma = req.getParameter("conferma");

		if (conferma != null) {

			System.out.println("Id spedizione" + idSpedizione);
			System.out.println("indirizzo" + i.getIdIndirizzo());

			ShipmentData spedizione = new ShipmentData();
			spedizione.setIdSpedizione(Integer.parseInt(idSpedizione));
			ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
			try {
				spedizione = databaseSpedizione.getShipmentDataById(spedizione);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String metodoP = req.getParameter("metodoPagamento");

			PaymentData pagamento = new PaymentData();
			pagamento.setIdPagamento(Integer.parseInt(metodoP));

			ProdottiDataDao databaseProdotti = new ProdottiDataDao();

			try {
				boolean acquisto = false;

				double totale = 0;

				OrderDataDao databaseOrdine = new OrderDataDao();
				int numero = databaseOrdine.getUltimoNumeroO();

				int id = Integer.parseInt(req.getParameter("id"));
				Collection<ProdottiData> prodotti = new ArrayList<>();
				ProdottiData prodotto = databaseProdotti.doRetrieveByKey(id);
				prodotti.add(prodotto);
				OrderData ordine = new OrderData();
				ordine.setEmail(user.getEmail());

				StatusOrderData status = new StatusOrderData();
				status.setIdStatusOrdine(1);
				status.setStato("Pagato");
				ordine.setStatusOrdine(status.getIdStatusOrdine());
				ordine.setIdSpedizione(spedizione.getIdSpedizione());
				ordine.setIdPagamento(pagamento.getIdPagamento());
				ordine.setIdProdotto(prodotto.getIdProdotto());
				ordine.setIdIndirizzo(i.getIdIndirizzo());

				ordine.setQuantita(1);

				ordine.setDataOrdine(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				ordine.setNumeroOrdine(numero + 1);
				totale = totale + prodotto.getPrezzo() * ordine.getQuantita();
				ordine.setTotale(totale);

				int quantitaRimaste = prodotto.getQuantita() - ordine.getQuantita();

				if (quantitaRimaste >= 0) {
					prodotto.setQuantita(quantitaRimaste);

					databaseOrdine.doSave(ordine);
					databaseProdotti.doUpdate(prodotto, prodotto.getIdProdotto());

					System.out.println("Pagamento avvenuto" + ordine.toString());

					System.out.println(prodotto.getQuantita());
					acquisto = true;
				}

				if (acquisto == false) {
					totale = 0;
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAcquisto.jsp");
					dispatcher.forward(req, resp);

				} else {
					totale = totale + spedizione.getCosto();
					req.setAttribute("prodotti", prodotti);
					req.setAttribute("spedizione", spedizione);
					req.setAttribute("totale", totale);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AcquistoCompletato.jsp");
					dispatcher.forward(req, resp);

				}
				System.out.println(totale);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public String FiltraInput(String stringa) {

		StringBuffer buffer = new StringBuffer(stringa.length());
		char c;
		for (int i = 0; i < stringa.length(); i++) {
			c = stringa.charAt(i);
			if (c == '<') {
				buffer.append("&lt;");
			} else if (c == '>') {
				buffer.append("&gt;");
			} else if (c == '"') {
				buffer.append("&quot;");
			} else if (c == '&') {
				buffer.append("&amp;");
			} else {
				buffer.append(c);
			}
		}

		return buffer.toString();
	}
}
