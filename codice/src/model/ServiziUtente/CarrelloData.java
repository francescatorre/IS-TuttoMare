package model.ServiziUtente;

public class CarrelloData {

	private int idProdotto;
	private String emailUtente;
	private String status;
	private int quantita;
	
	
	public CarrelloData() {
		
	}
  
	public int getIdProdotto() {
		return idProdotto;
	}
   
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
   
	public String getEmailUtente() {
		return emailUtente;
	}
  
	public void setEmailUtente(String idUtente) {
		this.emailUtente = idUtente;
	}
  
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String toString() {
		return "CarrelloData [idProdotto=" + idProdotto + ", emailUtente=" + emailUtente + ", status=" + status
				+ ", quantita=" + quantita + "]";
	}
}
