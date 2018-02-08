package model.Account;

import java.io.Serializable;

public class AddressData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idIndirizzo;
	private String nome;
	private String cognome;
	private String stato;
	private String provincia;
	private String citta;
	private String via;
	private String numeroCivico;
	private String cap;
	private String email;
	private String telefono;

	public AddressData() {

	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String toString() {
		return "AddressData [idIndirizzo=" + idIndirizzo + ", nome=" + nome + ", cognome=" + cognome + ", stato="
				+ stato + ", provincia=" + provincia + ", citta=" + citta + ", via=" + via + ", numeroCivico="
				+ numeroCivico + ", cap=" + cap + ", email=" + email + ", telefono=" + telefono + "]";
	}

}
