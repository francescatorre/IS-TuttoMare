package control.Amministratore;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account.UserData;
import model.Account.UserDataDao;

@WebServlet("/AbilitaAmministratore")
public class AbilitaAmministratore extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UserData user = new UserData();
	UserDataDao modelUser = new UserDataDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("pulsante");

		System.out.println("Email da modificare" + email);

		try {
			user = modelUser.searchUser(email);
			if (user.isAdmin()) {
				user.setAdmin(false);
				modelUser.doUpdate(user);
				System.out.println(user.toString());
			} else {
				user.setAdmin(true);
				modelUser.doUpdate(user);
				System.out.println(user.toString());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp");
		dispatcher.forward(request, response);

	}

}