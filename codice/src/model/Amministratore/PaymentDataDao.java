package model.Amministratore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.DriverManagerConnectionPool;

public class PaymentDataDao implements PaymentDataDaoInterface {

	private static final String TABLE_NAME = "GuerrieroSara.Pagamento";


	public void doSave(PaymentData pagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (TIPOPAGAMENTO) VALUES (?);";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, pagamento.getTipoPagamento());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}


	public Collection<PaymentData> getPayment() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PaymentData>  pagamenti = new LinkedList<PaymentData>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rset = preparedStatement.executeQuery();

		while (rset.next()) {

			PaymentData p = new PaymentData();
			p.setIdPagamento(rset.getInt("idPagamento"));
			p.setTipoPagamento(rset.getString("tipoPagamento"));
			pagamenti.add(p);
		}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return pagamenti;
	}

	public void doDelete(int IdPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE idpagamento = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IdPagamento);
            preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
	}


}
