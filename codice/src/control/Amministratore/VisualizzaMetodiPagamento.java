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
import model.Amministratore.PaymentDataDao;

@WebServlet("/VisualizzaMetodiPagamento")

public class VisualizzaMetodiPagamento extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PaymentDataDao modelPagamento = new PaymentDataDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		UserData c = (UserData) session.getAttribute("user");

		if (c != null) {
			if (c.isAdmin() == true) {

				try {
					if (action != null) {

						if (action.equalsIgnoreCase("cancella")) {
							int id = Integer.parseInt(req.getParameter("id"));

							modelPagamento.doDelete(id);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					req.setAttribute("listaMetodiPagamento", modelPagamento.getPayment());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/admin/AggiungiMetodoPagamento.jsp");
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