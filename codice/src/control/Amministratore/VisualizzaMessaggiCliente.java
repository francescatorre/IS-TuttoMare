package control.Amministratore;

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

import model.Account.UserData;
import model.ServiziUtente.ContattaciData;
import model.ServiziUtente.ContattaciDataDao;

@WebServlet("/VisualizzaMessaggiCliente")
public class VisualizzaMessaggiCliente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ContattaciDataDao modelContattaci = new ContattaciDataDao();
	Collection<ContattaciData> messaggi;

	public VisualizzaMessaggiCliente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				if (action != null && action.equalsIgnoreCase("elimina")) {
					int idMessaggio = Integer.parseInt(request.getParameter("idMessaggio"));
					try {
						modelContattaci.doDelete(idMessaggio);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/admin/VisualizzaMessaggi.jsp");
					dispatcher.forward(request, response);
				} else {
					try {
						messaggi = modelContattaci.doRetrieveAll("Email");
						request.setAttribute("messaggi", messaggi);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/admin/VisualizzaMessaggi.jsp");
					dispatcher.forward(request, response);

				}

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}