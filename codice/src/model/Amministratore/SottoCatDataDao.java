package model.Amministratore;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import model.DriverManagerConnectionPool;
import model.ServiziUtente.SottoCatDataDaoInterface;

public class SottoCatDataDao implements SottoCatDataDaoInterface {
	private static final String TABLE_NAME = "GuerrieroSara.SottoCategoria";
	
	public void doSave(SottoCatData sottoC) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (NOMESOTTOCATEGORIA, IDCATEGORIA,DESCRIZIONE) VALUES (?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, sottoC.getNomeSottoCategoria());
			preparedStatement.setInt(2, sottoC.getIdCategoria());
			preparedStatement.setString(3, sottoC.getDescrizione());
			
			
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


	@Override
	public boolean doDelete(int idS) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " +TABLE_NAME + " WHERE IDSOTTOCATEGORIA = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idS);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	
	}

	@Override
	public SottoCatData doRetrieveByKey(int idS) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SottoCatData bean = new SottoCatData();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE IDSOTTOCATEGORIA = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
				bean.setNomeSottoCategoria(rs.getString("NOMESOTTOCATEGORIA"));
				bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
										
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;

	}



	public SottoCatData doRetrieveByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SottoCatData bean = new SottoCatData();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE NOMESOTTOCATEGORIA = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
				bean.setNomeSottoCategoria(rs.getString("NOMESOTTOCATEGORIA"));
				bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}


	public Collection<SottoCatData> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<SottoCatData> sottoC = new LinkedList<SottoCatData>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SottoCatData bean = new SottoCatData();

				bean.setIdSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
				bean.setNomeSottoCategoria(rs.getString("NOMESOTTOCATEGORIA"));
				bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				
				sottoC.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return sottoC;
	}


	@Override
	public void doUpdate(SottoCatData sottoC, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE  " + TABLE_NAME
				+ " SET  nomesottocategoria=?, descrizione=?,idcategoria=? WHERE idsottocategoria=?";

		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, sottoC.getNomeSottoCategoria());
			preparedStatement.setString(2, sottoC.getDescrizione());
			preparedStatement.setInt(3, sottoC.getIdCategoria());
			preparedStatement.setInt(4, code);

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

	@Override
	public Collection<SottoCatData> doRetrieveAllbyC(int C) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<SottoCatData> sotto_categorie = new LinkedList<SottoCatData>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idcategoria= ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, C);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SottoCatData bean = new SottoCatData();

				bean.setIdSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
				bean.setNomeSottoCategoria(rs.getString("NOMESOTTOCATEGORIA"));
				bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				
				sotto_categorie.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return sotto_categorie;
	}


	@Override
	public ArrayList<SottoCatData> getSottoCategorie() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<SottoCatData> sottoC = new ArrayList<SottoCatData>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SottoCatData bean = new SottoCatData();

				bean.setIdSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
				bean.setNomeSottoCategoria(rs.getString("NOMESOTTOCATEGORIA"));
				bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				
				sottoC.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return sottoC;

	
	
	
	}


}