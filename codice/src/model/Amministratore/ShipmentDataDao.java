package model.Amministratore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.DriverManagerConnectionPool;

public class ShipmentDataDao implements ShipmentDataDaoInterface {

	private static final String TABLE_NAME = "GuerrieroSara.Spedizione";
	private static String doSave;
	private static String doDelete;
	private static String getShipmentDataById;

	static {
		doSave = "INSERT INTO " + TABLE_NAME + " (TipoSpedizione,NomeCorriere , costoSpedizione) VALUES (?,?,?)";
		doDelete = "Delete from " + TABLE_NAME + " WHERE idSpedizione=?";
		getShipmentDataById = "SELECT * FROM " + TABLE_NAME + " WHERE idspedizione = ?";
	}

	public void doDelete(ShipmentData spedizione) throws SQLException {

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(doDelete);

		preparedStatement.setInt(1, spedizione.getIdSpedizione());

		preparedStatement.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatement != null))
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

	public void doSave(ShipmentData spedizione) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(doSave);

		preparedStatement.setString(1, spedizione.getTipoSpedizione());
		preparedStatement.setString(2, spedizione.getNomeCorriere());
		preparedStatement.setDouble(3, spedizione.getCosto());
		preparedStatement.setInt(3, spedizione.getGiorni());

		preparedStatement.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatement != null))
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

	public Collection<ShipmentData> getShipment() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ShipmentData> spedizioni = new LinkedList<ShipmentData>();

		String selectSQL = "SELECT * FROM "+TABLE_NAME;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				ShipmentData s = new ShipmentData();
				s.setIdSpedizione(rs.getInt("idSpedizione"));
				s.setTipoSpedizione(rs.getString("TipoSpedizione"));
				s.setNomeCorriere(rs.getString("NomeCorriere"));
				s.setCosto(rs.getDouble("costoSpedizione"));
				s.setGiorni(rs.getInt("giorni"));
				spedizioni.add(s);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return spedizioni;

	}

	@Override
	public ShipmentData getShipmentDataById(ShipmentData spedizione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ShipmentData s = new ShipmentData();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
		    preparedStatement = connection.prepareStatement(getShipmentDataById);
		    preparedStatement.setInt(1,spedizione.getIdSpedizione());

		    ResultSet rset = preparedStatement.executeQuery();

	
		while (rset.next()) {

			s.setIdSpedizione(rset.getInt("idSpedizione"));
			s.setTipoSpedizione(rset.getString("TipoSpedizione"));
			s.setNomeCorriere(rset.getString("NomeCorriere"));
			s.setCosto(rset.getDouble("costoSpedizione"));
			s.setGiorni(rset.getInt("giorni"));

		} 
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return s;

	}

}
