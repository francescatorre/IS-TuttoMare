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
import model.Amministratore.CategoriaDataDao;

/* Servlet per visualizzare tutte le categorie e per eliminare una categoria */

@WebServlet("/ModificaCategoriaS")
public class ModificaCategoriaS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static CategoriaDataDao model = new CategoriaDataDao();

	public ModificaCategoriaS() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				try {
					if (action != null) {
						if (action.equalsIgnoreCase("cancella")) {
							int id = Integer.parseInt(request.getParameter("id"));
							model.doDelete(id);
						}
					}
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}

				try {
					request.setAttribute("categorie", model.doRetrieveAll("nomecategoria"));
				} catch (SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/ModificaCategoria.jsp");
				dispatcher.forward(request, response);
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

	}

}