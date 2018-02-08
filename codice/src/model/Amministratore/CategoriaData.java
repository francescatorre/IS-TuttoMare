package model.Amministratore;

import java.io.Serializable;

public class CategoriaData implements Serializable {

	private static final long serialVersionUID = 1L;

	public int idCategoria;
	public String pathicona;
	public String nome;
	public String descrizione;

	public CategoriaData() {

	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getPathicona() {
		return pathicona;
	}

	public void setPathicona(String pathicona) {
		this.pathicona = pathicona;
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

	@Override
	public String toString() {
		return "CategoriaBean [idCategoria=" + idCategoria + ", pathicona=" + pathicona + ", nome=" + nome
				+ ", descrizione=" + descrizione + "]";
	}

}
