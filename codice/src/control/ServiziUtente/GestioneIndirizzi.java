package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account.AddressData;
import model.Account.AddressDataDao;

@WebServlet("/GestioneIndirizzi")
public class GestioneIndirizzi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AddressDataDao database = new AddressDataDao();
		AddressData address = new AddressData();
		int ind = Integer.parseInt(req.getParameter("cancella"));

		address.setIdIndirizzo(ind);

		try {
			database.doDelete(address);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeJSP.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AddressData address = new AddressData();
		address.setNome(FiltraInput(req.getParameter("nomeIndirizzo")));
		address.setCognome(FiltraInput(req.getParameter("cognomeIndirizzo")));
		address.setCitta(FiltraInput(req.getParameter("cittaIndirizzo")));
		address.setStato(FiltraInput(req.getParameter("statoIndirizzo")));
		address.setProvincia(FiltraInput(req.getParameter("provinciaIndirizzo")));
		address.setVia(FiltraInput(req.getParameter("viaIndirizzo")));
		address.setTelefono(FiltraInput(req.getParameter("telefonoIndirizzo")));
		address.setNumeroCivico(FiltraInput(req.getParameter("numeroCivicoIndirizzo")));

		AddressDataDao database = new AddressDataDao();

		String email = req.getParameter("emailU");
		address.setEmail(email);

		try {
			database.doSave(address);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeJSP.jsp");
		dispatcher.forward(req, resp);

	}

	public String FiltraInput(String stringa) {

		StringBuffer buffer = new StringBuffer(stringa.length());
		char c;
		for (int i = 0; i < stringa.length(); i++) {
			c = stringa.charAt(i);
			if (c == '<') {
				buffer.append("&lt;");
			} else if (c == '>') {
				buffer.append("&gt;");
			} else if (c == '"') {
				buffer.append("&quot;");
			} else if (c == '&') {
				buffer.append("&amp;");
			} else {
				buffer.append(c);
			}
		}

		return buffer.toString();
	}

}
