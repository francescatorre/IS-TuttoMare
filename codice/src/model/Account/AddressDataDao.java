package model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.DriverManagerConnectionPool;

public class AddressDataDao implements AddressDataDaoInterface {

	private static final String TABLE_NAME = "GuerrieroSara.Indirizzo";
	private static String doSaveAddress;
	private static String doDeleteAddress;
	private static String verficAddress;
	private static String searchAddressByKey;

	static {
		verficAddress="SELECT idIndirizzo FROM "+TABLE_NAME+" where nome=? and cognome=? and stato=? and provincia=? and citta=? and via=? and NumeroCivico=? and cap=? and email=? and telefono=?;";

		doDeleteAddress = "DELETE FROM " + TABLE_NAME + " WHERE idIndirizzo = ?;";

		doSaveAddress = "INSERT INTO " + TABLE_NAME
				+ " (`Nome`, `Cognome`, `Stato`, `Provincia`, `Citta`, `Via`, `NumeroCivico`, `Cap`, `Email`, `Telefono`) VALUES(?,?,?,?,?,?,?,?,?,?);";

		searchAddressByKey="SELECT * FROM " + TABLE_NAME + " WHERE idindirizzo = ?";
	}

	@Override
	public void doDelete(AddressData address) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;

		PreparedStatement psAddUtente = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			psAddUtente = connection.prepareStatement(doDeleteAddress);
			psAddUtente.setLong(1, address.getIdIndirizzo());
			psAddUtente.executeUpdate();
			connection.commit();
		} finally {
			try {
				if ((psAddUtente != null))
					psAddUtente.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);

			}
		}

	}

	@Override
	public void doSave(AddressData address) throws SQLException {

		Connection connection = null;

		connection = DriverManagerConnectionPool.getConnection();

		PreparedStatement preparedStatementAddress = null;
		preparedStatementAddress = connection.prepareStatement(doSaveAddress);

		preparedStatementAddress.setString(1, address.getNome());
		preparedStatementAddress.setString(2, address.getCognome());
		preparedStatementAddress.setString(3, address.getStato());
		preparedStatementAddress.setString(4, address.getProvincia());
		preparedStatementAddress.setString(5, address.getCitta());
		preparedStatementAddress.setString(6, address.getVia());
		preparedStatementAddress.setString(7, address.getNumeroCivico());
		preparedStatementAddress.setString(8, address.getCap());
		preparedStatementAddress.setString(9, address.getEmail());
		preparedStatementAddress.setString(10, address.getTelefono());

		preparedStatementAddress.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatementAddress != null))
				preparedStatementAddress.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

	@Override
	public Collection<AddressData> searchAddress(UserData user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<AddressData> indirizzi = new LinkedList<AddressData>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email= ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user.getEmail());

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {

				AddressData address = new AddressData();

				address.setIdIndirizzo(Integer.parseInt(rset.getString("idIndirizzo")));

				address.setNome(rset.getString("Nome"));
				address.setCognome(rset.getString("Cognome"));
				address.setStato(rset.getString("Stato"));
				address.setProvincia(rset.getString("Provincia"));
				address.setCitta(rset.getString("Citta"));
				address.setVia(rset.getString("Via"));
				address.setNumeroCivico(rset.getString("NumeroCivico"));
				address.setCap(rset.getString("Cap"));
				address.setEmail(rset.getString("Email"));
				address.setTelefono(rset.getString("Telefono"));
				System.out.println(address.toString());
				indirizzi.add(address);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return indirizzi;
	}


	@Override
	public AddressData searchAddressByKey(AddressData adderess) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		AddressData indirizzo = new AddressData();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(searchAddressByKey);

			preparedStatement.setInt(1,adderess.getIdIndirizzo());
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {

				indirizzo.setIdIndirizzo(rset.getInt("idIndirizzo"));

				indirizzo.setNome(rset.getString("Nome"));
				indirizzo.setCognome(rset.getString("Cognome"));
				indirizzo.setStato(rset.getString("Stato"));
				indirizzo.setProvincia(rset.getString("Provincia"));
				indirizzo.setCitta(rset.getString("Citta"));
				indirizzo.setVia(rset.getString("Via"));
				indirizzo.setNumeroCivico(rset.getString("NumeroCivico"));
				indirizzo.setCap(rset.getString("Cap"));
				indirizzo.setEmail(rset.getString("Email"));
				indirizzo.setTelefono(rset.getString("Telefono"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return indirizzo;

	}

	@Override
	public AddressData doSaveAndReturn(AddressData address) throws SQLException {
		Connection connection = null;
		connection = DriverManagerConnectionPool.getConnection();

		PreparedStatement preparedStatementAddress = null;
		preparedStatementAddress = connection.prepareStatement(verficAddress);

		preparedStatementAddress.setString(1, address.getNome());
		preparedStatementAddress.setString(2, address.getCognome());
		preparedStatementAddress.setString(3, address.getStato());
		preparedStatementAddress.setString(4, address.getProvincia());
		preparedStatementAddress.setString(5, address.getCitta());
		preparedStatementAddress.setString(6, address.getVia());
		preparedStatementAddress.setString(7, address.getNumeroCivico());
		preparedStatementAddress.setString(8, address.getCap());
		preparedStatementAddress.setString(9, address.getEmail());
		preparedStatementAddress.setString(10, address.getTelefono());

		ResultSet rset = preparedStatementAddress.executeQuery();
		connection.commit();

		while (rset.next()) {
			address.setIdIndirizzo(rset.getInt("idIndirizzo"));
			return address;

		}

		connection.commit();

		try {
			if ((preparedStatementAddress != null))
				preparedStatementAddress.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}
		return null;

	}
}