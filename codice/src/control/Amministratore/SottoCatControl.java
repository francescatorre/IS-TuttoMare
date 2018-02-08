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
import model.Amministratore.CategoriaData;
import model.Amministratore.CategoriaDataDao;
import model.Amministratore.SottoCatData;
import model.Amministratore.SottoCatDataDao;

/*Servlet per aggiungere e modificare una Sotto Categoria*/

@WebServlet("/SottoCatControl")
public class SottoCatControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static SottoCatDataDao model = new SottoCatDataDao();
	static CategoriaDataDao modelC = new CategoriaDataDao();
	SottoCatData sottoC = new SottoCatData();
	CategoriaData categoria = new CategoriaData();
	int codice;

	public SottoCatControl() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("categorie", modelC.doRetrieveAll("nomecategoria"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				try {
					if (action != null) {

						if (action.equalsIgnoreCase("modifica")) {

							codice = Integer.parseInt(request.getParameter("id"));
							request.setAttribute("sottoC_modifica", model.doRetrieveByKey(codice));

						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/admin/AggiungiSottoCategoria.jsp");
				dispatcher.forward(request, response);

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {
				try {
					if (action != null) {

						if (action.equalsIgnoreCase("inserisci")) {
							String nome = (String) request.getParameter("nome");
							String descrizione = (String) request.getParameter("descrizione");
							String categori = request.getParameter("categoria");

							try {
								categoria = modelC.doRetrieveByName(categori);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							int codiceC = categoria.getIdCategoria();

							sottoC.setNomeSottoCategoria(FiltraInput(nome));
							sottoC.setDescrizione(FiltraInput(descrizione));
							sottoC.setIdCategoria(codiceC);

							model.doSave(sottoC);
							System.out.println(sottoC.toString());
						}
						if (action.equalsIgnoreCase("modifica")) {

							String nome = (String) request.getParameter("nome");
							String descrizione = (String) request.getParameter("descrizione");
							String categori = request.getParameter("categoria");

							try {
								categoria = modelC.doRetrieveByName(categori);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							int codiceC = categoria.getIdCategoria();

							sottoC.setNomeSottoCategoria(FiltraInput(nome));
							sottoC.setDescrizione(FiltraInput(descrizione));
							sottoC.setIdCategoria(codiceC);

							model.doUpdate(sottoC, codice);
							System.out.println(sottoC.toString());
						}

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					request.setAttribute("categorie", modelC.doRetrieveAll("nomecategoria"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/admin/AggiungiSottoCategoria.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreAccessoNegato.jsp");
			dispatcher.forward(request, response);
		}
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