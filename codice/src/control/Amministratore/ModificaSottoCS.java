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
import model.Amministratore.SottoCatDataDao;;

/* Servlet per visualizzare tutte le sotto categorie e per eliminare una sotto categoria */

@WebServlet("/ModificaSottoCS")
public class ModificaSottoCS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SottoCatDataDao model = new SottoCatDataDao();
	int id;

	public ModificaSottoCS() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");

		if (c.isAdmin() == true) {

			try {
				if (action != null) {
					if (action.equalsIgnoreCase("cancella")) {
						id = Integer.parseInt(request.getParameter("id"));
						System.out.println(id);
						model.doDelete(id);
					}
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			try {
				request.setAttribute("sottoC", model.doRetrieveAll("nomesottocategoria"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/ModificaSottoCat.jsp");
			dispatcher.forward(request, response);

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