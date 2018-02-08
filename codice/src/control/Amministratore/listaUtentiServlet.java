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
import model.Account.UserDataDao;

@WebServlet("/ListaUtenti")
public class listaUtentiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");

		if (c.isAdmin() == true) {
			UserDataDao gestioneUtente = new UserDataDao();
			try {
				request.setAttribute("userList", gestioneUtente.returnUser());

			} catch (SQLException e) {
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/admin/listaUtentiRegistrati.jsp").forward(request, response);
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
