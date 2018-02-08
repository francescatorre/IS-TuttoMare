package model.ServiziUtente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DriverManagerConnectionPool;
import model.Account.UserData;

public class CarrelloDataDao implements CarrelloDataDaoInterface {
	private static final String TABLE_NAME = "GuerrieroSara.Carrello";
	private static String doSave;
	private static String doDelete;
	private static String selectCarrello;
	private static String selectP;
	private static String update;

	static {
		update = "UPDATE  " +TABLE_NAME + " SET  quantita=? WHERE idprodotto=? and emailUtente=?";
		doSave = "INSERT INTO " + TABLE_NAME + "(emailUtente,idProdotto,quantita) VALUES (?,?,?)";
		doDelete = "DELETE FROM " + TABLE_NAME + " WHERE EmailUtente=? and idProdotto=?;";
		selectCarrello = "SELECT * FROM " + TABLE_NAME + " where EmailUtente=?;";
		selectP = "SELECT * FROM " + TABLE_NAME + " where EmailUtente=? and idProdotto=?;";

	}

	@Override
	public void doDelete(CarrelloData carrello) throws SQLException {

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(doDelete);

		preparedStatement.setString(1, carrello.getEmailUtente());
		preparedStatement.setInt(2, carrello.getIdProdotto());

		preparedStatement.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatement != null))
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

	public void doSave(CarrelloData carrello) throws SQLException {

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(doSave);

		preparedStatement.setString(1, carrello.getEmailUtente());
		preparedStatement.setInt(2, carrello.getIdProdotto());
		preparedStatement.setInt(3, carrello.getQuantita());

		preparedStatement.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatement != null))
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

	@Override
	public ArrayList<CarrelloData> getCarrello(UserData user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectCarrello);

		preparedStatement.setString(1, user.getEmail());

		ResultSet rset = preparedStatement.executeQuery();
		connection.commit();

		ArrayList<CarrelloData> list = new ArrayList<>();

		while (rset.next()) {
			CarrelloData carrello = new CarrelloData();
			carrello.setEmailUtente(rset.getString("EmailUtente"));
			carrello.setIdProdotto(rset.getInt("idProdotto"));

			carrello.setQuantita(rset.getInt("quantita"));
			list.add(carrello);

		}
		return list;
	}

	public CarrelloData selectCarrello(String email, int idProdotto) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectP);

		preparedStatement.setString(1, email);
		preparedStatement.setInt(2, idProdotto);
		ResultSet rset = preparedStatement.executeQuery();
		connection.commit();

		CarrelloData carrello = new CarrelloData();

		while (rset.next()) {
			carrello.setEmailUtente(rset.getString("EmailUtente"));
			carrello.setIdProdotto(rset.getInt("idProdotto"));

			carrello.setQuantita(rset.getInt("quantita"));

		}
		return carrello;
	}

	public void doUpdate(CarrelloData carrello) throws SQLException {
		Connection connection = null;

		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(update);
		preparedStatement.setInt(1,carrello.getQuantita());
		preparedStatement.setInt(2,carrello.getIdProdotto());
		preparedStatement.setString(3,carrello.getEmailUtente());
		preparedStatement.executeUpdate();
		connection.commit();

		try {
			if ((preparedStatement != null))
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		}

	}

}
