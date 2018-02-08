package control.Amministratore;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account.UserData;
import model.Amministratore.ShipmentData;
import model.Amministratore.ShipmentDataDao;

@WebServlet("/VisualizzaSpeseAdmin")
public class VisualizzaSpeseAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ShipmentDataDao databaseSpedizone = new ShipmentDataDao();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String elimina = req.getParameter("elimina");

		HttpSession session = req.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				if (elimina != null) {
					try {
						ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
						ShipmentData spedizione = new ShipmentData();
						spedizione.setIdSpedizione(Integer.parseInt(elimina));
						databaseSpedizione.doDelete(spedizione);

					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/ErroreSpedizione.jsp");
						dispatcher.forward(req, resp);
					}
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp");
				dispatcher.forward(req, resp);

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(req, resp);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				try {

					req.setAttribute("listaSpedizione", databaseSpedizone.getShipment());

				} catch (SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/admin/AggiungiSpedizione.jsp");
				dispatcher.forward(req, resp);

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(req, resp);
			}

		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(req, resp);
		}
	}

}