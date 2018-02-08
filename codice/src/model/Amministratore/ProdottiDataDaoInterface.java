package model.Amministratore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface ProdottiDataDaoInterface {

	public static ProdottiData doSave(ProdottiData prodotti) throws SQLException{
		return prodotti;}

	public void doUpdate(ProdottiData prodotti, int code) throws SQLException;

	public static boolean doDelete(int code) throws SQLException{
		return true;
		}
	
	public static ProdottiData doRetrieveByKey(int code) throws SQLException {return null;
	}

	public static ProdottiData doRetrieveByName(String name) throws SQLException{return null;
			}

	public Collection<ProdottiData> doRetrieveAll(String order) throws SQLException;

	public ArrayList<ProdottiData> getProdotti() throws SQLException;
		
	

	public static Collection<ProdottiData> doRetrieveAllbySC(int SC) throws SQLException{return null;
	}

}

