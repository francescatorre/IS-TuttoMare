package model.Amministratore;

import java.io.Serializable;

public class PaymentData implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idPagamento;
	private String tipoPagamento;

	public PaymentData() {

	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
