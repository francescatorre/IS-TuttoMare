package model.ServiziUtente;

import java.io.Serializable;
import java.sql.Date;

public class OrderData implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private Date consegna;
	private int idOrdine;
	private int idProdotto;
	private int quantita;
	private double totale;
	private Date dataOrdine;
	private int idSpedizione;
	private int statusOrdine;
	private String email;
	private int idPagamento;
	private int idIndirizzo;
	private int numeroOrdine;
	
	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date date) {
		this.dataOrdine = date;
	}

	public int getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public int getStatusOrdine() {
		return statusOrdine;
	}

	public void setStatusOrdine(int statusOrdine) {
		this.statusOrdine = statusOrdine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public Date getConsegna() {
		return consegna;
	}

	public void setConsegna(Date consegna) {
		this.consegna = consegna;
	}

	public String toString() {
		return "OrderData [idOrdine=" + idOrdine + ", idProdotto=" + idProdotto + ", quantita=" + quantita + ", totale="
				+ totale + ", dataOrdine=" + dataOrdine + ", idSpedizione=" + idSpedizione + ", statusOrdine="
				+ statusOrdine + ", email=" + email + ", idPagamento=" + idPagamento + ", idIndirizzo=" + idIndirizzo
				+ "]";
	}
}
