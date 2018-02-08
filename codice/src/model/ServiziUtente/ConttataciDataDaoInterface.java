package model.ServiziUtente;

import java.sql.SQLException;
import java.util.Collection;

public interface ConttataciDataDaoInterface {
	
	public void doSave(ContattaciData messaggio) throws SQLException;

	public boolean doDelete(int idMessaggio) throws SQLException;

	public Collection<ContattaciData> doRetrieveAll(String order) throws SQLException;

}
