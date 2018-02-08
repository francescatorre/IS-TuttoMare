package model.ServiziUtente;

import java.io.Serializable;

public class StatusOrderData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idStatusOrdine;
	private String stato;
	

	public StatusOrderData() {

	}

	public int getIdStatusOrdine() {
		return idStatusOrdine;
	}

	public void setIdStatusOrdine(int idStatusOrdine) {
		this.idStatusOrdine = idStatusOrdine;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}


}
