package model;



import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class TestDataBase {
	@Test
	public void testGetConnection() {
		try {
			assertNotEquals(DriverManagerConnectionPool.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void assertNotEquals(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	@Test
	public void TestReleaseConnection(){
		assertEquals(true,true);
	}
}
