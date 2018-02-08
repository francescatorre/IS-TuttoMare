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
import model.ServiziUtente.CarrelloData;
import model.ServiziUtente.CarrelloDataDao;

@WebServlet("/ModificaQuantita")
public class ModificaQuantita extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String quantita = req.getParameter("quantita");
		String meno = req.getParameter("meno");

		CarrelloDataDao databaseCarrello = new CarrelloDataDao();
		HttpSession session = req.getSession();
		UserData user = (UserData) session.getAttribute("user");

		if (meno != null && meno.equalsIgnoreCase("true")) {
			try {
				for (CarrelloData c : databaseCarrello.getCarrello(user)) {
					if (c.getIdProdotto() == Integer.parseInt(id)) {
						if (c.getQuantita() > 1) {
							c.setQuantita(Integer.parseInt(quantita) - 1);
							databaseCarrello.doUpdate(c);

						} else {
							databaseCarrello.doDelete(c);
						}

					}

				}

				req.getRequestDispatcher("/acquistaProdotto");

			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostraCarrello");
			dispatcher.forward(req, resp);

		}

		else {

			try {
				for (CarrelloData c : databaseCarrello.getCarrello(user)) {
					if (c.getIdProdotto() == Integer.parseInt(id)) {
						c.setQuantita(Integer.parseInt(quantita) + 1);
						databaseCarrello.doUpdate(c);

					}

				}

				req.getRequestDispatcher("/acquistaProdotto");

			} catch (SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostraCarrello");
			dispatcher.forward(req, resp);
		}
	}

}