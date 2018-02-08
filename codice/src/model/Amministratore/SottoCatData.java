package model.Amministratore;

import java.io.Serializable;

public class SottoCatData  implements Serializable {

		private static final long serialVersionUID = 1L;

		private int idSottoCategoria;
		private String nomeSottoCategoria;
		private String descrizione;
		private int idCategoria;
		

		public SottoCatData() {

		}


		public int getIdSottoCategoria() {
			return this.idSottoCategoria;
		}

		public void setIdSottoCategoria(int idSottoCategoria) {
			this.idSottoCategoria = idSottoCategoria;
		}

		public String getNomeSottoCategoria() {
			return nomeSottoCategoria;
		}

		public void setNomeSottoCategoria(String nomeSottoCategoria) {
			this.nomeSottoCategoria = nomeSottoCategoria;
		}

		public int getIdCategoria() {
			return idCategoria;
		}

		public void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}


		public String getDescrizione() {
			return descrizione;
		}


		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}


		@Override
		public String toString() {
			return "SottoCatBean [idSottoCategoria=" + idSottoCategoria + ", nomeSottoCategoria=" + nomeSottoCategoria
					+ ", descrizione=" + descrizione + ", idCategoria=" + idCategoria + "]";
		}


	}