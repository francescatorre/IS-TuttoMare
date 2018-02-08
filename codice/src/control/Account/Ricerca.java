package control.Account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amministratore.CategoriaDataDao;
import model.Amministratore.ProdottiData;
import model.Amministratore.ProdottiDataDao;
import model.Amministratore.SottoCatData;
import model.Amministratore.SottoCatDataDao;


/**
 * Servlet implementation class Ricerca
 */
@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProdottiDataDao model = new ProdottiDataDao();
	static CategoriaDataDao modelC = new CategoriaDataDao();
	static SottoCatDataDao modelSC = new SottoCatDataDao();
	ProdottiDataDao modelP = new ProdottiDataDao();
	Collection<ProdottiData> prodotti_ricercati;
	ArrayList<String> Marche = new ArrayList<>();
	ArrayList<ProdottiData> prodottiFiltri = new ArrayList<>();

	public Ricerca() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ricerca = request.getParameter("BarraDiRicerca");
		if (ricerca != null) {
         prodotti_ricercati=new ArrayList<>();
			try {
				Collection<ProdottiData> collezioneP = (Collection<ProdottiData>) modelP.doRetrieveAll("nome");
				if (collezioneP != null && collezioneP.size() != 0) {

					Iterator<?> it = collezioneP.iterator();
					while (it.hasNext()) {
						ProdottiData bean = (ProdottiData) it.next();

						String nomeP_min = bean.getNome().toLowerCase();
						int c = levenshteinDistance(ricerca, bean.getNome());
						int c2 = levenshteinDistance(ricerca, nomeP_min);

						if ((c <= 3) || (bean.getNome().equalsIgnoreCase(ricerca))
								|| (bean.getNome().indexOf(ricerca) > 0) || (bean.getNome().contains(ricerca))) {
							prodotti_ricercati.add(bean);

						}

						else if ((c2 <= 3) || (nomeP_min.equalsIgnoreCase(ricerca))
								|| ((nomeP_min).indexOf(ricerca) > 0) || (nomeP_min.contains(ricerca))) {
							{
								prodotti_ricercati.add(bean);
							}

						}

					}

				}

				Collection<SottoCatData> collezioneSC = (Collection<SottoCatData>) modelSC
						.doRetrieveAll("nomesottocategoria");
				if (collezioneSC != null && collezioneSC.size() != 0) {

					Iterator<?> it = collezioneSC.iterator();
					while (it.hasNext()) {
						SottoCatData beanSC = (SottoCatData) it.next();
						String nomeSC_min = beanSC.getNomeSottoCategoria().toLowerCase();
						String descrizioneSC_min = beanSC.getDescrizione().toLowerCase();

						if (beanSC.getDescrizione().contains(ricerca)
								|| (beanSC.getNomeSottoCategoria().equalsIgnoreCase(ricerca))
								|| (beanSC.getNomeSottoCategoria().contains(ricerca))
								|| (beanSC.getDescrizione().equalsIgnoreCase(ricerca))
								|| (nomeSC_min.equalsIgnoreCase(ricerca)) || (nomeSC_min.contains(ricerca))
								|| (descrizioneSC_min.equalsIgnoreCase(ricerca))
								|| (descrizioneSC_min.contains(ricerca))) {

							Collection<ProdottiData> collezionePs = modelP
									.doRetrieveAllbySC(beanSC.getIdSottoCategoria());
							if (collezionePs != null && collezioneP.size() != 0) {

								Iterator<?> ite = collezionePs.iterator();
								while (ite.hasNext()) {
									ProdottiData bean = (ProdottiData) ite.next();
									boolean presenza = false;
									for (ProdottiData t : prodotti_ricercati) {

										if (t.getIdProdotto() == bean.getIdProdotto()) {
											presenza = true;

										}

									}
									if (presenza == false) {
										prodotti_ricercati.add(bean);
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String action = request.getParameter("action");

		try {
			if (action != null) {

				if (action.equalsIgnoreCase("filtra")) {

					String filtra_prezzo = request.getParameter("prezzo");
					String filtra_peso = request.getParameter("peso");
					String filtra_marca = request.getParameter("marca");

					if (filtra_prezzo.equals("true")) {

						Float prezzo_min = Float.parseFloat(request.getParameter("prezzo_min"));
						Float prezzo_max = Float.parseFloat(request.getParameter("prezzo_max"));

						if (prodotti_ricercati != null) {
							Iterator<?> it = prodotti_ricercati.iterator();

							while (it.hasNext()) {
								ProdottiData bean = (ProdottiData) it.next();
								boolean presenza = false;
								for (ProdottiData t : prodottiFiltri) {

									if (t.getIdProdotto() == bean.getIdProdotto()) {
										presenza = true;
									}
								}
								if (presenza == false && (bean.getPrezzo() > prezzo_min)
										&& (bean.getPrezzo() < prezzo_max)) {
									prodottiFiltri.add(bean);
								}

							}

							request.setAttribute("prodotti_filtri", prodottiFiltri);

						}
					}
					if (filtra_peso.equals("true")) {
						Float peso_min = Float.parseFloat(request.getParameter("peso_min"));
						Float peso_max = Float.parseFloat(request.getParameter("peso_max"));

						if (prodotti_ricercati != null) {
							Iterator<?> it = prodotti_ricercati.iterator();
							while (it.hasNext()) {
								ProdottiData bean = (ProdottiData) it.next();
								boolean presenza = false;
								for (ProdottiData t : prodottiFiltri) {

									if (t.getIdProdotto() == bean.getIdProdotto()) {
										presenza = true;
									}
								}
								if (presenza == false && (bean.getPeso() > peso_min) && (bean.getPeso() < peso_max)) {
									prodottiFiltri.add(bean);
								}

							}

							request.setAttribute("prodotti_filtri", prodottiFiltri);
						}

					}
					if (filtra_marca.equals("true")) {
						String Marca = request.getParameter("marche");
						if (prodotti_ricercati != null) {
							Iterator<?> it = prodotti_ricercati.iterator();
							while (it.hasNext()) {
								ProdottiData bean = (ProdottiData) it.next();
								boolean presenza = false;
								for (ProdottiData t : prodottiFiltri) {

									if (t.getIdProdotto() == bean.getIdProdotto()) {
										presenza = true;
									}
								}
								if (presenza == false && bean.getMarca().equalsIgnoreCase(Marca)) {
									prodottiFiltri.add(bean);
								}
							}
							request.setAttribute("prodotti_filtri", prodottiFiltri);
						}

					}
				}
			}

			request.setAttribute("categorie", modelC.doRetrieveAll("nomecategoria"));
			request.setAttribute("sottoCategorie", modelSC.doRetrieveAll("nomesottocategoria"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Collection<ProdottiData> prodotti = model.doRetrieveAll("nome");
			Iterator<?> it = prodotti.iterator();
			while (it.hasNext()) {
				ProdottiData bean = (ProdottiData) it.next();

				boolean presenza = false;
				for (String t : Marche) {

					if (t.equalsIgnoreCase(bean.getMarca())) {
						presenza = true;
					}
				}
				if (presenza == false) {
					Marche.add(bean.getMarca());
				}
				request.setAttribute("Marche", Marche);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("ricercati", prodotti_ricercati);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProdottiRicercati.jsp");
		dispatcher.forward(request, response);
		prodottiFiltri.clear();
		Marche.clear();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public int levenshteinDistance(CharSequence lhs, CharSequence rhs) {
		int len0 = lhs.length() + 1;
		int len1 = rhs.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamically computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {
			// initial cost of skipping prefix in String s1
			newcost[0] = j;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {
				// matching current letters in both strings
				int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				int cost_replace = cost[i - 1] + match;
				int cost_insert = cost[i] + 1;
				int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
			}

			// swap cost/newcost arrays
			int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}

}