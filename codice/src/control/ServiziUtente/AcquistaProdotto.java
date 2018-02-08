package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Amministratore.PaymentDataDao;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.ShipmentData;
import model.Amministratore.ShipmentDataDao;
import model.ServiziUtente.CarrelloData;
import model.ServiziUtente.CarrelloDataDao;


@WebServlet("/acquistaProdotto")

public class AcquistaProdotto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String metodo = request.getParameter("acquista");
		String cancella = request.getParameter("cancella");

		HttpSession session = request.getSession();

		UserData user = (UserData) session.getAttribute("user");

		AddressDataDao databaseIndirizzo = new AddressDataDao();
		ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
		PaymentDataDao databasePayment = new PaymentDataDao();
		CarrelloDataDao databaseCarrello = new CarrelloDataDao();
		ProdottiDataDao db = new ProdottiDataDao();

		if (cancella != null) {
			System.out.println("Stai cancellando" + cancella);

			try {
				CarrelloData carrello = new CarrelloData();
				carrello.setEmailUtente(user.getEmail());
				carrello.setIdProdotto(Integer.parseInt(cancella));
				databaseCarrello.doDelete(carrello);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostraCarrello");
			dispatcher.forward(request, response);
		}

		if (metodo != null) {
			System.out.println("Stai pagando");
			try {

				
				ArrayList<CarrelloData> carrelloUtente = new ArrayList<>();

				carrelloUtente = databaseCarrello.getCarrello(user);
				Collection<ProdottiData> prodotti = new ArrayList<>();

				for (CarrelloData c : carrelloUtente) {

					ProdottiData p = db.doRetrieveByKey(c.getIdProdotto());

					p.setQuantita(c.getQuantita());
					prodotti.add(p);

				}

				Collection<AddressData> indirizzo =databaseIndirizzo.searchAddress(user);

				request.setAttribute("prodotti", prodotti);

				request.setAttribute("indirizzo", indirizzo);

				Collection<ShipmentData> lista =databaseSpedizione.getShipment();
				for (ShipmentData s : lista) {
					System.out.println(s.toString());
				}

				request.setAttribute("listaSpedizione", lista);

				Collection<PaymentData> listaMetodi =databasePayment.getPayment();
				request.setAttribute("listaMetodi", listaMetodi);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DatiPagamento.jsp");
			dispatcher.forward(request, response);

		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}