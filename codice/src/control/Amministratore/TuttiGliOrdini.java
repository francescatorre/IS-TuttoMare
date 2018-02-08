package control.Amministratore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ServiziUtente.OrderData;
import model.ServiziUtente.OrderDataDao;
@WebServlet("/TuttiGliOrdini")
public class TuttiGliOrdini extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	ArrayList<OrderData> tuttiGliOrdini=new ArrayList<>();
	OrderDataDao database=new OrderDataDao();
try {
	tuttiGliOrdini=database.getOrder();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
	req.setAttribute("ordini",tuttiGliOrdini);
	double totale=0;
for(OrderData o:tuttiGliOrdini) {
totale=totale+o.getTotale();	
}

req.setAttribute("totale",totale);
RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/TuttiGliOrdini.jsp");
dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
