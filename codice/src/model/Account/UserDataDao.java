package model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.DriverManagerConnectionPool;



public class UserDataDao implements UserDataDaoInterace {

	private static final String TABLE_NAME = "`GuerrieroSara`.`Utente`";
	private static final String TABLE_NAME_INDIRIZZO = "`GuerrieroSara`.`Indirizzo`";

	private static String doSave;
	private static String doUpdate;
	private static String doDeleteUser;
	private static String doRetrieveByKey;
	private static String doRetrieveByEmail;
	private static String getUserList;
	private static String selectSQL;
	static {
		getUserList="SELECT * FROM "+TABLE_NAME+";";

		doSave="INSERT INTO "+TABLE_NAME+" (`Nome`, `Cognome`, `Sesso`, `DataNascita`, `Via`, `NumeroCivico`, `CAP`, `Citta`, `Provincia`, `Stato`, `telefono`, `Email`, `Password`, `Avatar`, `Admin`) VALUES(?, ?,?,?, ?,?,?,?,?,?,?,?,?,?,?);";

		doUpdate="UPDATE "+TABLE_NAME+" SET Nome=?,Cognome=?,Sesso=?,DataNascita=?,Via=?,NumeroCivico=?,CAP=?,Citta=?,Provincia=?,Stato=?,telefono=?, Password=?,Avatar=?,Admin=? WHERE Email=?";
		doDeleteUser="DELETE FROM "+TABLE_NAME+" WHERE email = ?;";
	
		doRetrieveByEmail="SELECT * FROM"+TABLE_NAME+"WHERE email=?";
	
	selectSQL = "SELECT * FROM "+TABLE_NAME+"  WHERE email = ?";
	
	}
	
	
	public synchronized static boolean doSave(UserData user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("user");

		Connection connection = null;

		PreparedStatement psAddUtente = null;
		connection = DriverManagerConnectionPool.getConnection();
		psAddUtente = connection.prepareStatement(doSave);


		
		
		psAddUtente.setString(1, user.getNome());
	psAddUtente.setString(2, user.getCognome());
	psAddUtente.setString(3, user.getSesso());
psAddUtente.setString(4, user.getDataNascita());
psAddUtente.setString(5, user.getVia());
psAddUtente.setString(6, user.getNumeroCivico());
psAddUtente.setString(7, user.getCap());
psAddUtente.setString(8, user.getCitta());
psAddUtente.setString(9, user.getProvincia());
psAddUtente.setString(10, user.getStato());
psAddUtente.setString(11, user.getTelefono());

psAddUtente.setString(12, user.getEmail());
psAddUtente.setString(13, user.getPassword());
psAddUtente.setString(14, user.getAvatar());
psAddUtente.setBoolean(15, user.isAdmin());

psAddUtente.executeUpdate();
		connection.commit();
		
		System.out.println("salvato");
		AddressData address=new AddressData();
		address.setNome(user.getNome());
		address.setCognome(user.getCognome());
		address.setStato(user.getStato());
		address.setProvincia(user.getProvincia());
		address.setCitta(user.getCitta());
		address.setVia(user.getVia());

		address.setNumeroCivico(user.getNumeroCivico());

		address.setCap(user.getCap());

		address.setEmail(user.getEmail());
		address.setTelefono(user.getTelefono());

		
		AddressDataDao databaseAddress=new AddressDataDao();

		databaseAddress.doSave(address);
		
		try {
			if ((psAddUtente != null))
	psAddUtente.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		
	}
		return true;
	}

	@Override
	public void doUpdate(UserData user) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		connection = DriverManagerConnectionPool.getConnection();

		PreparedStatement psAddUtente = null;
		psAddUtente = connection.prepareStatement(doUpdate);

		System.out.println("Nel database ricevo user:"+user.toString());
	
		psAddUtente.setString(1, user.getNome());
		psAddUtente.setString(2, user.getCognome());
		psAddUtente.setString(3, user.getSesso());
		psAddUtente.setString(4, user.getDataNascita());
		psAddUtente.setString(5, user.getVia());
		psAddUtente.setString(6, user.getNumeroCivico());
		psAddUtente.setString(7, user.getCap());
		psAddUtente.setString(8, user.getCitta());
		psAddUtente.setString(9, user.getProvincia());
		psAddUtente.setString(10, user.getStato());
		psAddUtente.setString(11, user.getTelefono());
		psAddUtente.setString(12, user.getPassword());
		psAddUtente.setString(13, user.getAvatar());
		psAddUtente.setBoolean(14, user.isAdmin());
		psAddUtente.setString(15, user.getEmail());
			
		
		
psAddUtente.executeUpdate();
		connection.commit();

		try {
			if ((psAddUtente != null))
	psAddUtente.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		
	}
	
	
	
	}

	public synchronized static boolean doDelete(UserData utente) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Stai effettuando l'eliminazione dell'account:"+utente.getEmail());
		Connection connection = null;

		PreparedStatement psAddUtente = null;
		connection = DriverManagerConnectionPool.getConnection();
		psAddUtente = connection.prepareStatement(doDeleteUser);
		psAddUtente.setString(1, utente.getEmail());
		psAddUtente.executeUpdate();
		connection.commit();

		try {
			if ((psAddUtente != null))
	psAddUtente.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);

		
	}
		return true;

	}

	
	public static ArrayList<UserData> returnUser() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

	connection = DriverManagerConnectionPool.getConnection();
	preparedStatement = connection.prepareStatement(getUserList);
	

	ResultSet rset = preparedStatement.executeQuery();
	connection.commit();
String out = null;
ArrayList<UserData> userList=new ArrayList<>();

while (rset.next()) {
UserData user=new UserData();
		user.setNome(rset.getString("Nome"));
user.setCognome(rset.getString("Cognome"));
user.setSesso(rset.getString("sesso"));
user.setDataNascita(rset.getDate("DataNascita").toString());
user.setVia(rset.getString("Via"));
user.setNumeroCivico(rset.getString("NumeroCivico"));
user.setCap(rset.getString("Cap"));
user.setCitta(rset.getString("Citta"));
user.setProvincia(rset.getString("Provincia"));
user.setStato(rset.getString("Stato"));
user.setTelefono(rset.getString("Telefono"));
user.setEmail(rset.getString("Email"));
user.setPassword(rset.getString("Password"));
user.setAvatar(rset.getString("Avatar"));
user.setAdmin(rset.getBoolean("Admin"));

	
userList.add(user);


	}
	return userList;
		
		
		}






public static UserData searchUser(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserData user = new UserData();


		try {

			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setSesso(rs.getString("sesso"));
				user.setDataNascita(rs.getDate("DataNascita").toString());
				user.setVia(rs.getString("Via"));
				user.setNumeroCivico(rs.getString("NumeroCivico"));
				user.setCap(rs.getString("Cap"));
				user.setCitta(rs.getString("Citta"));
				user.setProvincia(rs.getString("Provincia"));
				user.setStato(rs.getString("Stato"));
				user.setTelefono(rs.getString("Telefono"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAvatar(rs.getString("Avatar"));
				user.setAdmin(rs.getBoolean("Admin"));

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return user;

	}
}