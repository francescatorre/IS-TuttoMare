package control.Amministratore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
@WebServlet("/AjaxCategorie")
public class AjaxCategorie extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("ci sono");
		
		
		CategoriaDataDao modelc = new CategoriaDataDao();
		SottoCatDataDao databaseSC=new SottoCatDataDao();
		ArrayList<CategoriaData> categorie=new ArrayList<>();
		ArrayList<SottoCatData> sottoCategorie=new ArrayList<>();

try {
		 categorie=(ArrayList<CategoriaData>) modelc.getCategorie();

		sottoCategorie=(ArrayList<SottoCatData>) databaseSC.getSottoCategorie();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resp.setContentType("text/plain");

			resp.getWriter().write(createJSONObject(categorie,sottoCategorie).toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}

	private Object createJSONObject(ArrayList<CategoriaData> categorie,ArrayList<SottoCatData> sottoCategorie) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray ja = new JSONArray();
		JSONArray uff = new JSONArray();

		for(CategoriaData c:categorie){
			int idC=c.getIdCategoria();

for(SottoCatData sc:sottoCategorie) {
			if(sc.getIdCategoria()==idC) {
						JSONObject jo=new JSONObject();
						jo.put("nomeC", c.getNome());
						jo.put("nomeSC", sc.getNomeSottoCategoria());
						jo.put("idSC", sc.getIdSottoCategoria());


				ja.put(jo);
	
				
				
					}
			
		
			
		
}
					
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("categorie", ja);

		return mainObj;
	
	}

}
