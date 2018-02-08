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

@WebServlet("/AggiungiSpeseSpedizione")
public class AggiungiSpeseSpedizione extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {
				String tipo = req.getParameter("tipo");
				String nome = req.getParameter("nome");
				String spese = req.getParameter("spese");
				int giorni = Integer.parseInt(req.getParameter("giorni"));

				ShipmentData spedizione = new ShipmentData();
				spedizione.setTipoSpedizione(tipo);
				spedizione.setCosto(Float.parseFloat(spese));
				spedizione.setNomeCorriere(nome);
				spedizione.setGiorni(giorni);

				try {
					ShipmentDataDao databaseSpedizione = new ShipmentDataDao();
					databaseSpedizione.doSave(spedizione);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}