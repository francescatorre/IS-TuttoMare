package control.ServiziUtente;

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

import model.Account.AddressDataDao;
import model.Account.UserData;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.ShipmentData;
import model.Amministratore.ShipmentDataDao;
import model.ServiziUtente.OrderData;
import model.ServiziUtente.OrderDataDao;
import model.ServiziUtente.OridiniEffettuati;

@WebServlet("/AcquistiEffettuati")
public class AcquistiEffettuati extends HttpServlet {

	private static final long serialVersionUID = 1L;
	OrderDataDao modelOrdini = new OrderDataDao();
	AddressDataDao modelIndirizzo = new AddressDataDao();
	ProdottiDataDao modelProdotti = new ProdottiDataDao();
	ShipmentDataDao modelSpedizioni = new ShipmentDataDao();
	Collection<OridiniEffettuati> oridiniEffe = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		UserData user = (UserData) session.getAttribute("user");

		if (user == null) {

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreVisualizzaOrdini.jsp");
			dispatcher.forward(req, resp);

		}

		else {
			try {
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

							if (bean.getEmail().equalsIgnoreCase(user.getEmail())) {

								ProdottiData prodotto = modelProdotti.doRetrieveByKey(bean.getIdProdotto());
								prodotto.setQuantita(bean.getQuantita());
								prodottiAcquistati.add(prodotto);

								totale = totale + bean.getTotale();
								numeroOrdine = bean.getNumeroOrdine();

							}

						}

						ShipmentData metodoSpedizione = new ShipmentData();
						metodoSpedizione.setIdSpedizione(ordine.get(0).getIdSpedizione());

						metodoSpedizione = modelSpedizioni.getShipmentDataById(metodoSpedizione);
						Date DataConsegna = ordine.get(0).getDataOrdine();
						DataConsegna.setDate(DataConsegna.getDate() + metodoSpedizione.getGiorni());

						int Statospesizione = ordine.get(0).getStatusOrdine();
						String statoSpe = "";
						if (Statospesizione == 1) {
							statoSpe = "Pagato";
						} else if (Statospesizione == 2) {
							statoSpe = "Consegnato";
						} else if (Statospesizione == 3) {
							statoSpe = "Spedito";
						}
						OridiniEffettuati ordinee = new OridiniEffettuati(prodottiAcquistati, totale, null, statoSpe,
								DataConsegna, metodoSpedizione, numeroOrdine);

						oridiniEffe.add(ordinee);

						System.out.println(prodottiAcquistati.size() + "Ordini" + oridiniEffe.size());
					}
				}
				req.setAttribute("ordini", oridiniEffe);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/IMieiOrdini.jsp");
				dispatcher.forward(req, resp);

				oridiniEffe.clear();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
