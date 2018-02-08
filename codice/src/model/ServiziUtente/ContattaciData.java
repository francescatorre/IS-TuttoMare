package model.ServiziUtente;

import java.io.Serializable;

public class ContattaciData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int idMessaggio;
	private String Nome;
	private String Cognome;
	private String Email;
	private String Commento;

	public ContattaciData() {

	}

	public int getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(int idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCommento() {
		return Commento;
	}

	public void setCommento(String commento) {
		Commento = commento;
	}

	public String toString() {
		return "ContattaciData [idMessaggio=" + idMessaggio + ", Nome=" + Nome + ", Cognome=" + Cognome + ", Email="
				+ Email + ", Commento=" + Commento + "]";
	}

}
