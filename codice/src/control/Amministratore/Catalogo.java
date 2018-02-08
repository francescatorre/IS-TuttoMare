package control.Amministratore;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amministratore.CategoriaDataDao;
import model.Amministratore.SottoCatDataDao;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static CategoriaDataDao modelC = new CategoriaDataDao();
	static SottoCatDataDao modelSc = new SottoCatDataDao();

	public Catalogo() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("sottoC", modelSc.doRetrieveAll("nomesottocategoria"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			request.setAttribute("categorie", modelC.doRetrieveAll("nomecategoria"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}