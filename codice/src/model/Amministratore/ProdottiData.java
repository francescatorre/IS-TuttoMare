package model.Amministratore;

import java.io.Serializable;

public class ProdottiData implements Serializable {

		private static final long serialVersionUID = 1L;

		private int idProdotto;
		private String nome;
		private String descrizione;
		private String immagine;
		private String marca;
		private float prezzo;
		private float peso;
		private int quantita;
		private boolean inEvidenza;
		private int sottoCategoria;

		
		
		public ProdottiData(){
			
		}
		
		public ProdottiData(int idProdotto, String nome, String descrizione,String marca, String immagine, float prezzo, float peso,int quantita, boolean inEvidenza, int sottoCategoria) 
	{
			super ();
			this.idProdotto= idProdotto;
			this.nome= nome;
			this.descrizione= descrizione;
			this.immagine= immagine;
			this.marca= marca;
			this.prezzo= prezzo;
			this.peso= peso;
			this.quantita= quantita;
			this.inEvidenza= inEvidenza;
			this.sottoCategoria= sottoCategoria;
		}

		
		public int getIdProdotto() {
			return idProdotto;
		}

		public void setIdProdotto(int idProdotto) {
			this.idProdotto = idProdotto;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescrizione() {
			return descrizione;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public String getImmagine() {
			return immagine;
		}

		public void setImmagine(String immagine) {
			this.immagine = immagine;
		}



		public float getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(float prezzo) {
			this.prezzo = prezzo;
		}

		public float getPeso() {
			return peso;
		}

		public void setPeso(float peso) {
			this.peso = peso;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}

		public boolean isInEvidenza() {
			return inEvidenza;
		}

		public void setInEvidenza(boolean inEvidenza) {
			this.inEvidenza = inEvidenza;
		}

		public int getSottoCategoria() {
			return sottoCategoria;
		}

		public void setSottoCategoria(int sottoCategoria) {
			this.sottoCategoria = sottoCategoria;
		}
	    
		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}
		@Override
		public String toString() {
			return "ProdottiBean [idProdotto=" + idProdotto + ", nome=" + nome + ", descrizione=" + descrizione
					+ ", immagine=" + immagine + ", prezzo=" + prezzo + ", peso=" + peso + ", quantita=" + quantita
					+ ", inEvidenza=" + inEvidenza + ", sottoCategoria=" + sottoCategoria + "]";
		}
	}

