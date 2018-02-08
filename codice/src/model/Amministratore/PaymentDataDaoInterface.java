package model.Amministratore;

import java.sql.SQLException;
import java.util.Collection;

public interface PaymentDataDaoInterface {
	
	public void doDelete(int IdPagamento) throws SQLException;

	public void doSave(PaymentData pagamento) throws SQLException;

	public Collection<PaymentData> getPayment() throws SQLException;

}
