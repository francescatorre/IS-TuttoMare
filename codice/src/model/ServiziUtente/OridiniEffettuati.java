package model.ServiziUtente;

import java.sql.Date;
import java.util.ArrayList;

import model.Account.AddressData;
import model.Amministratore.ProdottiData;
import model.Amministratore.ShipmentData;

public class OridiniEffettuati {
	
	
	private ArrayList<ProdottiData> prodottiComprati;
	private double totateoridne;
	private  AddressData indirizzoFatturazione;
	private String statoOrdine;
	private Date DataOridne;
	private ShipmentData metodoSpedizone;
	private int numeroOrdine;
	
	public OridiniEffettuati(ArrayList<ProdottiData> prodottiComprati, double totateoridne,
			AddressData indirizzoFatturazione, String statoOrdine, Date dataOridne, ShipmentData metodoSpedizone,
			int numeroOrdine) {

		this.prodottiComprati = prodottiComprati;
		this.totateoridne = totateoridne;
		this.metodoSpedizone = metodoSpedizone;
		this.indirizzoFatturazione = indirizzoFatturazione;
		this.statoOrdine = statoOrdine;
		this.DataOridne = dataOridne;
		this.numeroOrdine = numeroOrdine;

	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public ShipmentData getMetodoSpedizone() {
		return metodoSpedizone;
	}

	public void setMetodoSpedizone(ShipmentData metodoSpedizone) {
		this.metodoSpedizone = metodoSpedizone;
	}

	public Date getDataOridne() {
		return DataOridne;
	}

	public void setDataOridne(Date dataOridne) {
		DataOridne = dataOridne;
	}

	public ArrayList<ProdottiData> getProdottiComprati() {
		return prodottiComprati;
	}

	public void setProdottiComprati(ArrayList<ProdottiData> prodottiComprati) {
		this.prodottiComprati = prodottiComprati;
	}

	public double getTotateoridne() {
		return totateoridne;
	}

	public void setTotateoridne(double totateoridne) {
		this.totateoridne = totateoridne;
	}

	public AddressData getIndirizzoFatturazione() {
		return indirizzoFatturazione;
	}

	public void setIndirizzoFatturazione(AddressData indirizzoFatturazione) {
		this.indirizzoFatturazione = indirizzoFatturazione;
	}

	public String getStatoOrdine() {
		return statoOrdine;
	}

	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}

	@Override
	public String toString() {
		return "OridiniEffettuati [prodottiComprati=" + prodottiComprati + ", totateoridne=" + totateoridne
				+ ", indirizzoFatturazione=" + indirizzoFatturazione + ", statoOrdine=" + statoOrdine + ", DataOridne="
				+ DataOridne + "]";
	}

}
