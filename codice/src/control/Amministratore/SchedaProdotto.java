package control.Amministratore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amministratore.ProdottiDataDao;
import model.Amministratore.ShipmentDataDao;;

/*Servlet per visualizzare i dettagli di un prodotto*/

@WebServlet("/SchedaProdotto")
public class SchedaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottiDataDao model = new ProdottiDataDao();
	ShipmentDataDao modelSpedizioni = new ShipmentDataDao();
	int codice;
	int codiceSC;

	public SchedaProdotto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {

				if (action.equalsIgnoreCase("dettagli")) {

					codice = Integer.parseInt(request.getParameter("id"));
					codiceSC = Integer.parseInt(request.getParameter("sc"));

					request.setAttribute("prodotto_dettagli", ProdottiDataDao.doRetrieveByKey(codice));
					request.setAttribute("Prodotti_SC", ProdottiDataDao.doRetrieveAllbySC(codiceSC));
					request.setAttribute("listaSpedizione", modelSpedizioni.getShipment());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SchedaProdotto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}