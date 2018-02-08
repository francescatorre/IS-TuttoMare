
	package model;
	import static org.junit.Assert.*;


	import java.sql.SQLException;
	import java.util.Collection;

	import org.junit.Test;

	import model.Amministratore.ProdottiData;

	import model.Amministratore.ProdottiDataDao;






	public class TestDataBaseAmministratore {


		 	@Test
		public void doSave() {
			ProdottiData d = new ProdottiData(0011,"CannaDaPesca", "Canna da Pesca estensibile", "takestop", null, 11, 5, 4, false, 0);
				try{
				ProdottiData done = ProdottiDataDao.doSave(d);
				assertEquals(true, done);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		 	}

		 	@Test
		 	public void TestDelete() {
		 		ProdottiData d = new ProdottiData(0011,"CannaDaPesca", "Canna da Pesca estensibile", "takestop", null, 11, 5, 4, false, 0);
				int i=d.getIdProdotto();
				try {
					assertEquals(ProdottiDataDao.doDelete(i), false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Test
			public void testdoRetrieveByKey() throws SQLException {
				int u = 0011;
				ProdottiData r = ProdottiDataDao.doRetrieveByKey(u);
				System.out.println("  " + r);
				assertNotNull(r);
			}
			@Test
			public void testdoRetrieveByName() throws SQLException {
				String u = "CannaDaPesca";
				ProdottiData r = ProdottiDataDao.doRetrieveByName(u);
				System.out.println("  " + r);
				assertNotNull(r);
			}
			@Test
			public void testdoRetrieveAllbySC() throws SQLException {
				int u = 1;
				Collection<ProdottiData> r = ProdottiDataDao.doRetrieveAllbySC(u);
				System.out.println("  " + r);
				assertNotNull(r);
			}
	}


