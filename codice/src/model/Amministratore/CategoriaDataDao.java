	package model.Amministratore;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
	import java.util.LinkedList;

import model.DriverManagerConnectionPool;
import model.ServiziUtente.CategoriaDataDaoInterface;


public class CategoriaDataDao implements CategoriaDataDaoInterface{

		private static final String TABLE_NAME = "GuerrieroSara.Categoria";

		@Override
		public void doSave(CategoriaData categoria) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + TABLE_NAME
					+ " (NOMECATEGORIA, DESCRIZIONE,IMMAGINE) VALUES (?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, categoria.getNome());
				preparedStatement.setString(2, categoria.getDescrizione());
				preparedStatement.setString(3, categoria.getPathicona());
				
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
		public boolean doDelete(int codiceC) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IDCATEGORIA = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, codiceC);
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
		public CategoriaData doRetrieveByKey(int codiceC) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			CategoriaData bean = new CategoriaData();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE IDCATEGORIA = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, codiceC);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOMECATEGORIA"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setPathicona(rs.getString("IMMAGINE"));
							
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


		@Override
		public Collection<CategoriaData> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<CategoriaData> categorie = new LinkedList<CategoriaData>();

			String selectSQL = "SELECT * FROM " + TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CategoriaData bean = new CategoriaData();
					
					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOMECATEGORIA"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setPathicona( rs.getString("IMMAGINE"));
					
					categorie.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return categorie;
		}

		@Override
		public CategoriaData doRetrieveByName(String name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
System.out.println(name);
			CategoriaData bean = new CategoriaData();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE NOMECATEGORIA = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, name);
				
				ResultSet rs = preparedStatement.executeQuery();

				connection.commit();

				while (rs.next()) {
					int i=rs.getInt("IDCATEGORIA");
System.out.println(i);
					bean.setIdCategoria(i);
					bean.setNome(rs.getString("NOMECATEGORIA"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setPathicona( rs.getString("IMMAGINE"));
						
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			System.out.println(bean.toString());
			return bean;
		}

		public void doUpdate(CategoriaData categoria, int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String updateSQL = "UPDATE  " + TABLE_NAME
					+ " SET  nomecategoria=?, descrizione=?, immagine=? WHERE IDCATEGORIA=?";

			
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, categoria.getNome());
				preparedStatement.setString(2, categoria.getDescrizione());
				preparedStatement.setString(3,categoria.getPathicona());
				preparedStatement.setInt(4, id );

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
		public ArrayList<CategoriaData> getCategorie() throws SQLException {
			// TODO Auto-generated method stub
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<CategoriaData> categorie = new ArrayList<CategoriaData>();

			String selectSQL = "SELECT * FROM " + TABLE_NAME;


			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CategoriaData bean = new CategoriaData();
					
					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOMECATEGORIA"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setPathicona( rs.getString("IMMAGINE"));
					
					categorie.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return categorie;
		}

	}
		


