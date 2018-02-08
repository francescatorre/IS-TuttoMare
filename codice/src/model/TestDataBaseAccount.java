package model;

import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

import model.Account.UserData;
import model.Account.UserDataDao;



public class TestDataBaseAccount {
	/* Test inserisci utente
	 * Oracolo: Inserimento riuscito
	 */
	@Test
	public void testdoSave() {
		UserData u = new UserData ("Francesca", "Torre", "F", "1994-09-24", "Via Libero Grassi", "4", "84030", "Caselle in Pittati", "Salerno", "Italia", "3453556727", "franc.torre@hotmail.com", "sara", null, true);
		try{
			Boolean done = UserDataDao.doSave(u);
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Test inserisci utente ripetuto fallito
	 * Oracolo: inserimento fallito
	 */
	@Test
	public void testdoSaveUtenteFallito() {
		UserData u = new UserData ("Miriam", "Greco", "F", "1970-05-05", "Via Roma", "10", "81030", "Roma", "Roma", "Italia", "3394924190", "testemail@studenti.it", "testemail", null , false);
		try{
			Boolean done = UserDataDao.doSave(u);
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* Test elimina utente
	 * Oracolo: Utente eliminato
	 */
	@Test
	public void testdoDelete(){
	    String email= "testemail@studenti.it";
		UserData utente = new UserData();
               utente.setEmail(email);
				try{
			Boolean done = UserDataDao.doDelete(utente);
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testReturnUser() {
		ArrayList<UserData> array = null;
		try {
			array = UserDataDao.returnUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(array);
	}
	
	@Test
	public void testsearchUser() throws SQLException {
		String p = "franc.torre@hotmail.com";
		UserData r = UserDataDao.searchUser(p);
		assertNotNull(r);
	}
}