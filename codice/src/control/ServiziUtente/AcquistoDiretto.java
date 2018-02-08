package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/AcquistoDiretto")
public class AcquistoDiretto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AcquistoDiretto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String compra = request.getParameter("compra");

		if (compra != null) {

			int idProdotto = Integer.parseInt(request.getParameter("id"));

			HttpSession session = request.getSession();

			UserData user = (UserData) session.getAttribute("user");
			
			if(user==null){
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreCarrello.jsp");
				dispatcher.forward(request, response);
				
			}else{
			

			AddressDataDao databaseIndirizzo = new AddressDataDao();
			ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
			PaymentDataDao databasePayment = new PaymentDataDao();
			ProdottiDataDao db = new ProdottiDataDao();

			try {
				ProdottiData prodottoScelto;
				prodottoScelto = db.doRetrieveByKey(idProdotto);

				Collection<AddressData> indirizzo = databaseIndirizzo.searchAddress(user);

				request.setAttribute("prodotto", prodottoScelto);

				request.setAttribute("indirizzo", indirizzo);

				Collection<ShipmentData> lista = databaseSpedizione.getShipment();
				request.setAttribute("listaSpedizione", lista);

				Collection<PaymentData> listaMetodi = databasePayment.getPayment();
				request.setAttribute("listaMetodi", listaMetodi);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AcquistoDiretto.jsp");
			dispatcher.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
