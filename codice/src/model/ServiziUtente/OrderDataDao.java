package model.ServiziUtente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.DriverManagerConnectionPool;
import model.Account.UserData;

public class OrderDataDao implements OrderDataDaoInterface {

	private static final String TABLE_NAME = "GuerrieroSara.Ordine";

	private static String doSave;
	private static String selectNumOrder;
	private static String getOrder;
	private static String getOrderByUser;
	
	
	static {
		doSave="INSERT INTO "+TABLE_NAME+"( Quantita,Totale,DataOrdine,idSpedizione,idStatusOrdine,EmailUtente,idPagamento, idIndirizzo,idProduct,nOrdine) VALUES (?,?, ?,?,?,?,?,?,?,?)" + 
				"";
		selectNumOrder="Select nOrdine from "+TABLE_NAME;
        
		getOrderByUser="Select * from "+ TABLE_NAME+ " where EmailUtente=?";
		
		getOrder="Select * from "+TABLE_NAME;

	}


	public void doSave(OrderData ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(doSave);

			preparedStatement.setInt(1, ordine.getQuantita());
			preparedStatement.setDouble(2, ordine.getTotale());
			preparedStatement.setDate(3, ordine.getDataOrdine());
			preparedStatement.setInt(4, ordine.getIdSpedizione());
			preparedStatement.setInt(5, ordine.getStatusOrdine());
			preparedStatement.setString(6, ordine.getEmail());
			preparedStatement.setInt(7, ordine.getIdPagamento());
			preparedStatement.setInt(8, ordine.getIdIndirizzo());
			preparedStatement.setInt(9, ordine.getIdProdotto());
			preparedStatement.setInt(10, ordine.getNumeroOrdine());

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


	public static ArrayList<OrderData> getOrder() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrderData> list = new ArrayList<>();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(getOrder);

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				OrderData o = new OrderData();
				o.setDataOrdine(rset.getDate("DataOrdine"));
				o.setEmail(rset.getString("EmailUtente"));
				o.setIdIndirizzo(rset.getInt("idIndirizzo"));
				o.setIdOrdine(rset.getInt("idOrdine"));
				o.setIdPagamento(rset.getInt("idPagamento"));
				o.setIdProdotto(rset.getInt("idProduct"));
				o.setQuantita(rset.getInt("quantita"));
				o.setTotale(rset.getInt("totale"));
				o.setIdSpedizione(rset.getInt("idSpedizione"));
				o.setStatusOrdine(rset.getInt("idStatusOrdine"));
				o.setNumeroOrdine(rset.getInt("nOrdine"));
				list.add(o);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return list;
	}
	

	public static ArrayList<OrderData> getOrderByUser(UserData user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrderData> list = new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(getOrderByUser);

			preparedStatement.setString(1, user.getEmail());
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				OrderData o = new OrderData();
				o.setDataOrdine(rset.getDate("DataOrdine"));
				o.setEmail(rset.getString("EmailUtente"));
				o.setIdIndirizzo(rset.getInt("idIndirizzo"));
				o.setIdOrdine(rset.getInt("idOrdine"));
				o.setIdPagamento(rset.getInt("idPagamento"));
				o.setIdProdotto(rset.getInt("idProduct"));
				o.setQuantita(rset.getInt("quantita"));
				o.setTotale(rset.getInt("totale"));
				o.setIdSpedizione(rset.getInt("idSpedizione"));
				o.setStatusOrdine(rset.getInt("idStatusOrdine"));
				o.setNumeroOrdine(rset.getInt("nOrdine"));
				list.add(o);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return list;
	}


	public int getUltimoNumeroO() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int o = 0;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectNumOrder);

			ResultSet rset = preparedStatement.executeQuery();
			connection.commit();

			while (rset.next()) {
				o = rset.getInt("nOrdine");
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return o;
	}

	public static Collection<OrderData> getOrdiniByNumeroOrdine(int ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrderData> ordini = new ArrayList<OrderData>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nOrdine = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, ordine);

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				OrderData o= new OrderData();
				o.setDataOrdine(rset.getDate("DataOrdine"));
				o.setEmail(rset.getString("EmailUtente"));
				o.setIdIndirizzo(rset.getInt("idIndirizzo"));
				o.setIdOrdine(rset.getInt("idOrdine"));
				o.setIdPagamento(rset.getInt("idPagamento"));
				o.setIdProdotto(rset.getInt("idProduct"));
				o.setQuantita(rset.getInt("quantita"));
				o.setTotale(rset.getInt("totale"));
				o.setIdSpedizione(rset.getInt("idSpedizione"));
				o.setStatusOrdine(rset.getInt("idStatusOrdine"));
				o.setNumeroOrdine(rset.getInt("nOrdine"));
				ordini.add(o);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;

	}

	@Override
	public void doUpdate(OrderData ordine, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE  " +TABLE_NAME
				+ " SET  Quantita=?,Totale=?,DataOrdine=?,idSpedizione=?,idStatusOrdine=?,EmailUtente=?,idPagamento=?, idIndirizzo=?,idProduct=?,nOrdine=? WHERE idordine=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, ordine.getQuantita());
			preparedStatement.setDouble(2, ordine.getTotale());
			preparedStatement.setDate(3, ordine.getDataOrdine());
			preparedStatement.setInt(4,ordine.getIdSpedizione());
			preparedStatement.setInt(5, ordine.getStatusOrdine());
			preparedStatement.setString(6, ordine.getEmail());
			preparedStatement.setInt(7, ordine.getIdPagamento());
			preparedStatement.setInt(8, ordine.getIdIndirizzo());
			preparedStatement.setInt(9, ordine.getIdProdotto());
			preparedStatement.setInt(10, ordine.getNumeroOrdine());
			preparedStatement.setInt(11, code);

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