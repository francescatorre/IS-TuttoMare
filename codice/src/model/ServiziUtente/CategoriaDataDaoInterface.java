
	package model.ServiziUtente;

	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Amministratore.CategoriaData;

	public interface CategoriaDataDaoInterface {
		
		public void doSave(CategoriaData categoria) throws SQLException;

		public boolean doDelete(int codiceC) throws SQLException;
		
		public void doUpdate(CategoriaData categoria,int id) throws SQLException;

		public CategoriaData doRetrieveByKey(int codiceC) throws SQLException;
		
		public CategoriaData doRetrieveByName(String name) throws SQLException;
		
		public Collection<CategoriaData> doRetrieveAll(String order) throws SQLException;
		public ArrayList<CategoriaData> getCategorie() throws SQLException;


}
