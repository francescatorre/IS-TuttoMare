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

@WebServlet("/CercaIndirizzo")
public class CercaIndirizzo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int codice = Integer.parseInt(req.getParameter("id"));
		AddressDataDao database=new AddressDataDao();
		AddressData a=new AddressData();
		a.setIdIndirizzo(codice);
		try {
			req.setAttribute("address",database.searchAddressByKey(a));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Indirizzo.jsp");
		dispatcher.forward(req, resp);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
