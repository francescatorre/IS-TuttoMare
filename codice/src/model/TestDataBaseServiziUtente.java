package model;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import model.Account.UserData;
import model.ServiziUtente.OrderData;
import model.ServiziUtente.OrderDataDao;

public class TestDataBaseServiziUtente {

	@Test
	public void getOrder() throws SQLException {
		ArrayList<OrderData> list = OrderDataDao.getOrder();
		assertNotNull(list);
	}
	
	
	 	@Test
		public void testgetOrderByUser() throws SQLException {
	 		UserData u = new UserData ();
	 		u.setEmail("srossi@hotmail.it");
			ArrayList <OrderData> r = OrderDataDao.getOrderByUser(u);
			System.out.println("  " + r);
			assertNotNull(r);
		}
	 	
		@Test
		public void testgetOrdiniByNumeroOrdine() throws SQLException {
	 		int u=2;
			Collection <OrderData> r = OrderDataDao.getOrdiniByNumeroOrdine(u);
			System.out.println("  " + r);
			assertNotNull(r);
		}
	 	
}
	 

	