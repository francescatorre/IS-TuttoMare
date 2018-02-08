package control.Account;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account.UserData;
import model.Account.UserDataDao;

@WebServlet("/loginServlet")

public class SessionUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserData user;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equals("login")) {

			String email = request.getParameter("usernameLogin");
			String pwd = request.getParameter("pswLogin");

			UserData user = new UserData();
			user.setEmail(email);
			try {

				UserDataDao database = new UserDataDao();
				user = UserDataDao.searchUser(user.getEmail());
				System.out.println(user.toString());

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if ((user != null) && (user.getPassword().equals(pwd))) {

				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				// setto la sessione per 30 min
				session.setMaxInactiveInterval(30 * 60);

				// setto il cookie
				Cookie userName = new Cookie("user", user.getEmail());
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);

				String encodedURL = response.encodeRedirectURL("/PWTuttoMare/homeJSP.jsp");
				response.sendRedirect(encodedURL);

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreLogin.jsp");
				dispatcher.forward(request, response);

			}
		} else if (action.equals("logout")) {
			response.setContentType("text/html");
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("JSESSIONID")) {
						System.out.println("JSESSIONID=" + cookie.getValue());
					}
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "no-store");
					response.setDateHeader("Expires", 0);
					response.setHeader("Pragma", "no-cache");

				}
			}
			// invalidate the session if exists
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("user", null);
				session.invalidate();
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma", "no-cache");
				

			} 
			response.sendRedirect(request.getContextPath() + "/homeJSP.jsp");
			

		}

	}

}