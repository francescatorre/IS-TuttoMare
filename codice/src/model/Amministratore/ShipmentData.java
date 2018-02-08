package model.Amministratore;

import java.io.Serializable;

public class ShipmentData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idSpedizione;
	private String tipoSpedizione;
	private String nomeCorriere;
	private double costo;
	private int giorni;
	

	public ShipmentData() {

	}

	public double getCosto() {
		return costo;
	}
	
	public int getGiorni() {
		return giorni;
	}
	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getTipoSpedizione() {
		return tipoSpedizione;
	}

	public void setTipoSpedizione(String tipoSpedizione) {
		this.tipoSpedizione = tipoSpedizione;
	}

	public String getNomeCorriere() {
		return nomeCorriere;
	}

	public void setNomeCorriere(String nomeCorriere) {
		this.nomeCorriere = nomeCorriere;
	}

}
