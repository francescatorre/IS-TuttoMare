package control.Account;

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
import javax.servlet.http.Part;

import model.Account.UserData;
import model.Account.UserDataDao;

@WebServlet("/SaveUserDatabase")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB

public class SaveUserDatabase extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		// Recupero i parametri inseriti nel form di registrazione

		String email = request.getParameter("email");

		String password = request.getParameter("password");

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

		// ANAGRAFICA
		String nome = request.getParameter("nome");

		String cognome = request.getParameter("cognome");

		String sesso = request.getParameter("sesso");

		String giornoNascita = request.getParameter("giornoNascita");

		String meseNascita = request.getParameter("meseNascita");

		String annoNascita = request.getParameter("annoNascita");


		String statoResidenza = request.getParameter("statoResidenza");

		String numeroCivico = request.getParameter("numeroCivico");

		// RESIDENZA
		String indirizzoResidenza = request.getParameter("indirizzoResidenza");

		String provinciaResidenza = request.getParameter("provincia");
		System.out.println(provinciaResidenza);
		String cittaResidenza = request.getParameter("cittaResidenza");

		String capResidenza = request.getParameter("capResidenza");

		String telefono = request.getParameter("telefonoResidenza");

		UserDataDao database = new UserDataDao();
		UserData user = new UserData();

		user.setEmail(FiltraInput(email));

		user.setNome(FiltraInput(nome));

		user.setCognome(FiltraInput(cognome));

		user.setSesso(FiltraInput(sesso));

		String date = annoNascita + "-" + meseNascita + "-" + giornoNascita;

		user.setDataNascita(FiltraInput(date));

		user.setVia(FiltraInput(indirizzoResidenza));

		user.setNumeroCivico(FiltraInput(numeroCivico));

		user.setCap(FiltraInput(capResidenza));

		user.setCitta(FiltraInput(cittaResidenza));

		user.setProvincia(FiltraInput(provinciaResidenza));

		user.setStato(FiltraInput(statoResidenza));

		user.setTelefono(FiltraInput(telefono));

		user.setPassword(FiltraInput(password));

		user.setAvatar(filename);

		user.setAdmin(false);

		try {
			database.doSave(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeJSP.jsp");
		dispatcher.forward(request, response);

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
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}