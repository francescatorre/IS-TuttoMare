package control.ServiziUtente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import jdk.nashorn.internal.parser.JSONParser;
import model.Amministratore.CategoriaData;
import model.Amministratore.CategoriaDataDao;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.SottoCatData;
import model.Amministratore.SottoCatDataDao;
@WebServlet("/homeAjax")
public class homeAjax extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		 CategoriaDataDao modelc = new CategoriaDataDao();
			try {
				Collection<?> categorie = (Collection<?>) modelc.doRetrieveAll("nomeCategoria");
				SottoCatDataDao modelSC = new SottoCatDataDao();

				Collection<?> sottoCategorie = (Collection<?>) modelSC.doRetrieveAll("nomeSottoCategoria");

				Collection<ProdottiData> inEvidenza = new ArrayList<>();
				ProdottiDataDao modelP = new ProdottiDataDao();
				Collection<ProdottiData> collezioneP = (Collection<ProdottiData>) modelP.doRetrieveAll("nome");

				
				req.setAttribute("inEvidenza", inEvidenza);
				req.setAttribute("collezioneP",collezioneP);
				req.setAttribute("categorie",categorie);
				req.setAttribute("sottocategorie",sottoCategorie);
				
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homeLogin.jsp");
			dispatcher.forward(req, resp);

			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
}

	
}
