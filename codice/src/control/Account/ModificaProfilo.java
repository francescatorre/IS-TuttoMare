package control.Account;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Account.AddressData;
import model.Account.AddressDataDao;
import model.Account.UserData;
import model.Account.UserDataDao;

@WebServlet("/ModificaProfilo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class ModificaProfilo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "";
	Collection<AddressData> arrayList = new LinkedList<>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserData user = new UserData();
		AddressDataDao databaseAddress = new AddressDataDao();

		String email = request.getParameter("user");

		UserDataDao database = new UserDataDao();

		try {
			user = database.searchUser(email);

			arrayList = databaseAddress.searchAddress(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("list", arrayList);
		request.setAttribute("utente", user);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ModProfilo.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserData user = new UserData();
		UserDataDao database = new UserDataDao();

		// eliminazione account
		String eliminaAccount = request.getParameter("eliminaAccount");
		UserData utente = new UserData();

		if (eliminaAccount != null) {
			try {
				String email = request.getParameter("userEmail");
				utente.setEmail(email);
				System.out.println(email);

				database.doDelete(utente);
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect("homeJSP.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			// Recupero i dati da jsp

			user.setEmail(FiltraInput(request.getParameter("emailUtente")));

			String password = FiltraInput(request.getParameter("password"));
			user.setPassword(password);

			user.setNome(FiltraInput(request.getParameter("nome")));
			user.setCognome(FiltraInput(request.getParameter("cognome")));
			user.setSesso(FiltraInput(request.getParameter("sesso")));

			String giorno = request.getParameter("giornoNascita");
			String mese = request.getParameter("meseNascita");
			String anno = request.getParameter("annoNascita");

			user.setDataNascita(anno + "-" + mese + "-" + giorno);

			user.setStato(FiltraInput(request.getParameter("stato")));
			user.setProvincia(FiltraInput(request.getParameter("provincia")));
			user.setCitta(FiltraInput(request.getParameter("citta")));
			user.setVia(FiltraInput(request.getParameter("via")));
			user.setNumeroCivico(FiltraInput(request.getParameter("numeroCivico")));

			user.setCap(FiltraInput(request.getParameter("codicePostale")));
			user.setTelefono(FiltraInput(request.getParameter("telefono")));
			// immagine
			String filename = request.getParameter("photo");

			String appPath = request.getServletContext().getRealPath("");

			String savePath = appPath + File.separator + SAVE_DIR;

			// creates the save directory if it does not exists
			File fileSaveDir = new File(appPath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && !fileName.equals("")) {
					// refines the fileName in case it is an absolute path
					fileName = new File(fileName).getName();
					part.write(savePath + File.separator + fileName);
					filename = fileName;
				}
			}
			user.setAvatar(filename);
			// user.setAdmin(user.isAdmin());
			System.out.println("Request:" + user.toString());

			try {

				database.doUpdate(user);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeJSP.jsp");
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

}