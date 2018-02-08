package control.Amministratore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amministratore.CategoriaData;
import model.Amministratore.CategoriaDataDao;
import model.Amministratore.SottoCatDataDao;;

/* Servlet per mostrare tutte le sotto categorie di una categoria selezionata  */

@WebServlet("/MostraSCperCategoria")
public class MostraSCperCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nome;
	SottoCatDataDao databseSottoCategorie = new SottoCatDataDao();
CategoriaDataDao databaseCategorie=new CategoriaDataDao();
	public MostraSCperCategoria() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if (action != null) {

				if (action.equalsIgnoreCase("visualizza")) {

					nome = request.getParameter("nome");
CategoriaData categoria=databaseCategorie.doRetrieveByName(nome);
System.out.println(categoria.getIdCategoria());			
request.setAttribute("SottoCategorie_C", databseSottoCategorie.doRetrieveAllbyC(categoria.getIdCategoria()));

					request.setAttribute("nomeCategoria", nome);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SottoCategorie_C.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}