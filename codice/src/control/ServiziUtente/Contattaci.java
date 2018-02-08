package control.ServiziUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ServiziUtente.ContattaciData;
import model.ServiziUtente.ContattaciDataDao;


/* Servlet per implementare il form contattaci nel footer*/
@WebServlet("/Contattaci")
public class Contattaci extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContattaciDataDao modelContattaci = new ContattaciDataDao();
	ContattaciData messaggio= new ContattaciData();

    public Contattaci() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("Nome");
		String cognome=request.getParameter("Cognome");
		String email=request.getParameter("Email");
		String Commento=request.getParameter("Commento");
		
		messaggio.setNome(FiltraInput(nome));
		messaggio.setCognome(FiltraInput(cognome));
		messaggio.setEmail(FiltraInput(email));
		messaggio.setCommento(FiltraInput(Commento));
		
		try {
			modelContattaci.doSave(messaggio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeJSP.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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