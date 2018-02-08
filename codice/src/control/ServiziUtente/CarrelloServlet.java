package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String quantitaS = request.getParameter("quantita");
		HttpSession session = request.getSession();
		UserData cliente = (UserData) session.getAttribute("user");
		String prodotto = (String) request.getParameter("id");

		if (cliente != null) {

			try {
				CarrelloDataDao databaseCarrello = new CarrelloDataDao();
				CarrelloData carrello = new CarrelloData();

				carrello.setEmailUtente(cliente.getEmail());
				carrello.setIdProdotto(Integer.parseInt(prodotto));
				carrello.setQuantita(Integer.parseInt(quantitaS));
				databaseCarrello.doSave(carrello);

				System.out.println(carrello.toString());

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {

				if (e instanceof SQLIntegrityConstraintViolationException) {
					System.out.println("Elemento gia� aggiunto puoi modificare la quantita�");
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostraCarrello");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreCarrello.jsp");
			dispatcher.forward(request, response);
		}

	}

}
