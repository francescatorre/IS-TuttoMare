package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account.UserData;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.ServiziUtente.CarrelloData;
import model.ServiziUtente.CarrelloDataDao;

@WebServlet("/mostraCarrello")
public class MostraCarrello extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserData cliente = (UserData) session.getAttribute("user");
		if (cliente != null) {

			try {

				CarrelloDataDao databaseCarrello = new CarrelloDataDao();
				ArrayList<CarrelloData> array = new ArrayList<>();
				array = databaseCarrello.getCarrello(cliente);

				request.setAttribute("showCarrello", array);

				ArrayList<ProdottiData> listaProdotti = new ArrayList<>();

				ProdottiDataDao databaseProdotti = new ProdottiDataDao();

				for (CarrelloData carrello : array) {

					ProdottiData p = databaseProdotti.doRetrieveByKey(carrello.getIdProdotto());
					p.setQuantita(carrello.getQuantita());
					listaProdotti.add(p);

				}

				request.setAttribute("showProduct", listaProdotti);

				getServletContext().getRequestDispatcher("/Carrello.jsp").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreCarrello.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
