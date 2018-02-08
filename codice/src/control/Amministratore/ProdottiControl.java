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
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.SottoCatData;
import model.Amministratore.SottoCatDataDao;

/* Servlet per aggiungere e modificare un prodotto */

@WebServlet("/ProdottiControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class ProdottiControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static ProdottiDataDao model = new ProdottiDataDao();
	static SottoCatDataDao modelSc = new SottoCatDataDao();

	ProdottiData prodotto = new ProdottiData();
	SottoCatData sottoC = new SottoCatData();
	int codiceSc;
	int codice;

	public ProdottiControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserData c = (UserData) session.getAttribute("user");
		if (c != null) {
			if (c.isAdmin() == true) {
				try {
					request.setAttribute("sottoCategorie", modelSc.doRetrieveAll("nomesottocategoria"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String action = request.getParameter("action");

				try {
					if (action != null) {

						if (action.equalsIgnoreCase("modifica")) {

							codice = Integer.parseInt(request.getParameter("id"));

							request.setAttribute("prodotto_modifica", model.doRetrieveByKey(codice));

						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/AggiungiProdotto.jsp");
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
							String marca = (String) request.getParameter("marca");
							Float prezzo = Float.parseFloat(request.getParameter("prezzo"));
							Float peso = Float.parseFloat(request.getParameter("peso"));
							int quantita = Integer.parseInt(request.getParameter("quantita"));
							String evidenza = request.getParameter("evidenza");
							String s = request.getParameter("sottoCat");
							String filename = request.getParameter("img");

							System.out.println(s.toString());
							try {
								sottoC = modelSc.doRetrieveByName(s);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							int codiceSC = sottoC.getIdSottoCategoria();

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

							if (evidenza.equalsIgnoreCase("si")) {
								prodotto.setInEvidenza(true);
							} else
								prodotto.setInEvidenza(false);

							prodotto.setNome(FiltraInput(nome));
							prodotto.setDescrizione(FiltraInput(descrizione));
							prodotto.setMarca(FiltraInput(marca));
							prodotto.setPeso(peso);
							prodotto.setPrezzo(prezzo);
							prodotto.setImmagine(filename);
							prodotto.setQuantita(quantita);
							prodotto.setSottoCategoria(codiceSC);
							model.doSave(prodotto);

						}

						if (action.equalsIgnoreCase("modifica")) {

							String nome = (String) request.getParameter("nome");
							String descrizione = (String) request.getParameter("descrizione");
							String marca = (String) request.getParameter("marca");
							Float prezzo = Float.parseFloat(request.getParameter("prezzo"));
							Float peso = Float.parseFloat(request.getParameter("peso"));
							int quantita = Integer.parseInt(request.getParameter("quantita"));
							String s = request.getParameter("sottoCat");
							String evidenza = request.getParameter("evidenza");
							String filename = request.getParameter("img");

							try {
								sottoC = modelSc.doRetrieveByName(s);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							int codiceSC = sottoC.getIdSottoCategoria();

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
							if (evidenza.equalsIgnoreCase("si")) {
								prodotto.setInEvidenza(true);
							} else
								prodotto.setInEvidenza(false);

							prodotto.setNome(FiltraInput(nome));
							prodotto.setDescrizione(FiltraInput(descrizione));
							prodotto.setMarca(FiltraInput(marca));
							prodotto.setPeso(peso);
							prodotto.setPrezzo(prezzo);
							prodotto.setImmagine(filename);
							prodotto.setQuantita(quantita);
							prodotto.setSottoCategoria(codiceSC);
							model.doUpdate(prodotto, codice);
							System.out.println(prodotto.toString());
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					request.setAttribute("sottoCategorie", modelSc.doRetrieveAll("nomesottocategoria"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/AggiungiProdotto.jsp");
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