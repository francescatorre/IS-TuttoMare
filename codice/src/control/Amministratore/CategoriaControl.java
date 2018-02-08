package control.Amministratore;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Account.UserData;
import model.Amministratore.CategoriaData;
import model.Amministratore.CategoriaDataDao;

/* Servlet per l'aggiunta e la modifica di una  categoria */

@WebServlet("/CategoriaControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class CategoriaControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static CategoriaDataDao model = new CategoriaDataDao();
	CategoriaData categoria = new CategoriaData();
	int codice;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {

				try {
					if (action != null) {

						if (action.equalsIgnoreCase("modifica")) {

							codice = Integer.parseInt(request.getParameter("id"));
							request.setAttribute("categoria_modifica", model.doRetrieveByKey(codice));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/AggiungiCategoria.jsp");
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

							// immagine
							String filename = request.getParameter("img");
							String nome = request.getParameter("nome");
							String descrizone = request.getParameter("descrizione");

							String appPath = request.getServletContext().getRealPath("");
							String SAVE_DIR = "";
							String savePath = appPath + File.separator + SAVE_DIR;
							System.out.println(savePath);
							// creates the save directory if it does not exists
							File fileSaveDir = new File(appPath);
							if (!fileSaveDir.exists()) {
								fileSaveDir.mkdir();
							}

							for (Part part : request.getParts()) {
								String fileName = extractFileName(part);
								if (fileName != null && !fileName.equals("")) {
									// refines the fileName in case it is an
									// absolute
									// path
									fileName = new File(fileName).getName();
									part.write(savePath + File.separator + fileName);
									filename = fileName;
								}

							}

							categoria.setPathicona(filename);
							categoria.setDescrizione(FiltraInput(descrizone));
							categoria.setNome(FiltraInput(nome));

							model.doSave(categoria);

						} else if (action.equalsIgnoreCase("modifica")) {

							String filename = request.getParameter("img");
							String nome = request.getParameter("nome");
							String descrizone = request.getParameter("descrizione");

							String appPath = request.getServletContext().getRealPath("");
							String SAVE_DIR = "";
							String savePath = appPath + File.separator + SAVE_DIR;
							System.out.println(savePath);
							// creates the save directory if it does not exists
							File fileSaveDir = new File(appPath);
							if (!fileSaveDir.exists()) {
								fileSaveDir.mkdir();
							}

							for (Part part : request.getParts()) {
								String fileName = extractFileName(part);
								if (fileName != null && !fileName.equals("")) {
									// refines the fileName in case it is an
									// absolute
									// path
									fileName = new File(fileName).getName();
									part.write(savePath + File.separator + fileName);
									filename = fileName;
								}

							}

							categoria.setPathicona(filename);
							categoria.setDescrizione(FiltraInput(descrizone));
							categoria.setNome(FiltraInput(nome));

							model.doUpdate(categoria, codice);
							System.out.println(categoria.toString());

						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/AggiungiCategoria.jsp");
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

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("contentDisp= " + contentDisp);
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
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