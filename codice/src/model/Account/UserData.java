package model.Account;

import java.io.Serializable;

public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sesso;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String via;
	private String numeroCivico;
	private String provincia;
	private String telefono;
	private String citta;
	private String cap;
	private boolean admin;
	private String avatar;
	private String stato;
	private String email;
	private String password;

	public UserData() {

	}

	public UserData(String nome, String cognome, String sesso, String dataNascita, String via, String numeroCivico, String cap, String citta, String provincia, String stato, String telefono, String email, String password, String avatar, boolean admin) {  
	    super();
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		this.dataNascita=dataNascita;
		this.via=via;
		this.numeroCivico=numeroCivico;
		this.cap=cap;
		this.citta=citta;
		this.provincia=provincia;
		this.stato=stato;
		this.telefono=telefono;
		this.email=email;
		this.password=password;
		this.avatar=avatar;
		this.admin=admin;

	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
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

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String toString() {
		return "UserData [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", dataNascita=" + dataNascita
				+ ", via=" + via + ", numeroCivico=" + numeroCivico + ", provincia=" + provincia + ", telefono="
				+ telefono + ", citta=" + citta + ", cap=" + cap + ", admin=" + admin + ", avatar=" + avatar
				+ ", stato=" + stato + ", email=" + email + ", password=" + password + "]";
	}

}