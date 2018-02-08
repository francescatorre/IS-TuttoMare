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
import model.Amministratore.PaymentData;
import model.Amministratore.PaymentDataDao;

@WebServlet("/AggiungiMetodoPagamento")

public class AggiungiMetodoPagamento extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PaymentDataDao modelPagamento = new PaymentDataDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {
				String metodo = req.getParameter("metodo");
				if (metodo != null) {
					PaymentData pagamento = new PaymentData();
					pagamento.setTipoPagamento(metodo);

					try {
						modelPagamento.doSave(pagamento);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/menuAdmin.jsp");
				dispatcher.forward(req, resp);
			}

			else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(req, resp);
		}
	}
}