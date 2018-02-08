package control.Amministratore;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.ShipmentData;
import model.Amministratore.ShipmentDataDao;
import model.ServiziUtente.OrderData;
import model.ServiziUtente.OrderDataDao;
import model.ServiziUtente.OridiniEffettuati;

/* Servlet per mostrare all'amministratore gli ordini da spedire  */

@WebServlet("/InviaOrdini")
public class InviaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDataDao modelOrdini = new OrderDataDao();
	AddressDataDao modelIndirizzo = new AddressDataDao();
	ProdottiDataDao modelProdotti = new ProdottiDataDao();
	ShipmentDataDao modelSpedizioni = new ShipmentDataDao();
	Collection<OridiniEffettuati> oridiniEffe = new ArrayList<>();

	public InviaOrdini() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			UserData c = (UserData) session.getAttribute("user");
			if (c != null) {
				if (c.isAdmin() == true) {
					int numeroOrdini = modelOrdini.getUltimoNumeroO();

					for (int i = 1; i <= numeroOrdini; i++) {
						ArrayList<OrderData> ordine = new ArrayList<>();
						ArrayList<ProdottiData> prodottiAcquistati = new ArrayList<>();

						ordine = (ArrayList<OrderData>) modelOrdini.getOrdiniByNumeroOrdine(i);
						double totale = 0.0;
						int numeroOrdine = 0;

						if (ordine != null && ordine.size() != 0) {

							Iterator<?> it = ordine.iterator();
							while (it.hasNext()) {
								OrderData bean = (OrderData) it.next();

								ProdottiData prodotto = modelProdotti.doRetrieveByKey(bean.getIdProdotto());
								prodotto.setQuantita(bean.getQuantita());
								prodottiAcquistati.add(prodotto);

								totale = totale + bean.getTotale();
								numeroOrdine = bean.getNumeroOrdine();

							}

							Date DataOrdine = ordine.get(0).getDataOrdine();
							AddressData indirizzo = new AddressData();
							indirizzo.setIdIndirizzo(ordine.get(0).getIdIndirizzo());
							AddressData indirizzoCliente = modelIndirizzo.searchAddressByKey(indirizzo);

							ShipmentData metodoSpedizione = new ShipmentData();
							metodoSpedizione.setIdSpedizione(ordine.get(0).getIdSpedizione());

							metodoSpedizione = modelSpedizioni.getShipmentDataById(metodoSpedizione);

							int Statospesizione = ordine.get(0).getStatusOrdine();
							String statoSpe = "";
							if (Statospesizione == 1) {
								statoSpe = "Pagato";
							} else if (Statospesizione == 2) {
								statoSpe = "Consegnato";
							} else if (Statospesizione == 3) {
								statoSpe = "Spedito";
							}
							OridiniEffettuati ordinee = new OridiniEffettuati(prodottiAcquistati, totale,
									indirizzoCliente, statoSpe, DataOrdine, metodoSpedizione, numeroOrdine);

							oridiniEffe.add(ordinee);
						}
					}
					request.setAttribute("ordini", oridiniEffe);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/admin/VisualizzaOrdini.jsp");
					dispatcher.forward(request, response);

					oridiniEffe.clear();

				} else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(request, response);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numeroOrine = Integer.parseInt(request.getParameter("nOrdine"));

		if (numeroOrine != 0) {

			try {
				ArrayList<OrderData> ordini = (ArrayList<OrderData>) modelOrdini.getOrdiniByNumeroOrdine(numeroOrine);

				for (int i = 0; i < ordini.size(); i++) {

					OrderData o = ordini.get(i);
					o.setStatusOrdine(3);
					modelOrdini.doUpdate(o, o.getIdOrdine());

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp");
			dispatcher.forward(request, response);

		}
	}

}