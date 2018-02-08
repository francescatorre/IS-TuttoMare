package model.Amministratore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import model.DriverManagerConnectionPool;


public class ProdottiDataDao implements ProdottiDataDaoInterface {
		
		private static final String TABLE_NAME = "GuerrieroSara.Prodotto";

		public static ProdottiData doSave(ProdottiData prodotti) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + TABLE_NAME
					+ " (NOME, DESCRIZIONE,MARCA, IMMAGINE,PREZZO,PESO,QUANTITA,INEVIDENZA, IDSOTTOCATEGORIA) VALUES (?,?,?,?,?,?,?,?,?);";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, prodotti.getNome());
				preparedStatement.setString(2, prodotti.getDescrizione());
				preparedStatement.setString(3, prodotti.getMarca());
				preparedStatement.setString(4,prodotti.getImmagine());
				preparedStatement.setFloat(5, prodotti.getPrezzo());
				preparedStatement.setFloat(6, prodotti.getPeso());
				preparedStatement.setInt(7, prodotti.getQuantita());
				preparedStatement.setBoolean(8, prodotti.isInEvidenza());
				preparedStatement.setInt(9, prodotti.getSottoCategoria());

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
			return prodotti;
		}


		public synchronized static boolean doDelete(int code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE idprodotto = ?";


			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, code);

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
		public Collection<ProdottiData> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<ProdottiData> products = new LinkedList<ProdottiData>();

			String selectSQL = "SELECT * FROM " + TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProdottiData bean = new ProdottiData();

					bean.setIdProdotto(rs.getInt("IDPRODOTTO"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setMarca(rs.getString("MARCA"));
					bean.setImmagine( rs.getString("IMMAGINE"));
					bean.setPrezzo(rs.getFloat("PREZZO"));
					bean.setPeso(rs.getFloat("PESO"));
					bean.setQuantita(rs.getInt("QUANTITA"));
					bean.setInEvidenza(rs.getBoolean("INEVIDENZA"));
					bean.setSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
		
					
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}


		
		public static ProdottiData doRetrieveByKey(int code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ProdottiData bean = new ProdottiData();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idprodotto = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, code);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdProdotto(rs.getInt("IDPRODOTTO"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setMarca(rs.getString("MARCA"));
					bean.setImmagine( rs.getString("IMMAGINE"));
					bean.setPrezzo(rs.getFloat("PREZZO"));
					bean.setPeso(rs.getFloat("PESO"));
					bean.setQuantita(rs.getInt("QUANTITA"));
					bean.setInEvidenza(rs.getBoolean("INEVIDENZA"));
					bean.setSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
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


		
		public static ProdottiData doRetrieveByName(String name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ProdottiData bean = new ProdottiData();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE NOME = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, name);
				

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdProdotto(rs.getInt("IDPRODOTTO"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setMarca(rs.getString("MARCA"));
					bean.setImmagine( rs.getString("IMMAGINE"));
					bean.setPrezzo(rs.getFloat("PREZZO"));
					bean.setPeso(rs.getFloat("PESO"));
					bean.setQuantita(rs.getInt("QUANTITA"));
					bean.setInEvidenza(rs.getBoolean("INEVIDENZA"));
					bean.setSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
								
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
		public void doUpdate(ProdottiData prodotti, int code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String updateSQL = "UPDATE  " +TABLE_NAME
					+ " SET  nome=?, descrizione=?,marca=?, immagine=?, prezzo=?,peso=?,quantita=?,inevidenza=?,idsottocategoria=? WHERE idprodotto=?";

			
			
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, prodotti.getNome());
				preparedStatement.setString(2, prodotti.getDescrizione());
				preparedStatement.setString(3, prodotti.getMarca());
				preparedStatement.setString(4,prodotti.getImmagine());
				preparedStatement.setFloat(5, prodotti.getPrezzo());
				preparedStatement.setFloat(6, prodotti.getPeso());
				preparedStatement.setInt(7, prodotti.getQuantita());
				preparedStatement.setBoolean(8, prodotti.isInEvidenza());
				preparedStatement.setInt(9, prodotti.getSottoCategoria());
				preparedStatement.setInt(10, code);

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


		
		public static Collection<ProdottiData> doRetrieveAllbySC(int SC) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<ProdottiData> products = new LinkedList<ProdottiData>();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idsottocategoria= ?";


			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, SC);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProdottiData bean = new ProdottiData();

					bean.setIdProdotto(rs.getInt("IDPRODOTTO"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setMarca(rs.getString("MARCA"));
					bean.setImmagine( rs.getString("IMMAGINE"));
					bean.setPrezzo(rs.getFloat("PREZZO"));
					bean.setPeso(rs.getFloat("PESO"));
					bean.setQuantita(rs.getInt("QUANTITA"));
					bean.setInEvidenza(rs.getBoolean("INEVIDENZA"));
					bean.setSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
		
					
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}

        @Override
		public ArrayList<ProdottiData> getProdotti() throws SQLException {
			// TODO Auto-generated method stub
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<ProdottiData> products = new ArrayList<ProdottiData>();

			String selectSQL = "SELECT * FROM " + TABLE_NAME;



			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProdottiData bean = new ProdottiData();

					bean.setIdProdotto(rs.getInt("IDPRODOTTO"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setMarca(rs.getString("MARCA"));
					bean.setImmagine( rs.getString("IMMAGINE"));
					bean.setPrezzo(rs.getFloat("PREZZO"));
					bean.setPeso(rs.getFloat("PESO"));
					bean.setQuantita(rs.getInt("QUANTITA"));
					bean.setInEvidenza(rs.getBoolean("INEVIDENZA"));
					bean.setSottoCategoria(rs.getInt("IDSOTTOCATEGORIA"));
		
					
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}

	}

