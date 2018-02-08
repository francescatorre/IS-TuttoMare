package model.ServiziUtente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Amministratore.SottoCatData;

public interface SottoCatDataDaoInterface {

	
	public void doSave(SottoCatData sottoC) throws SQLException;

	public boolean doDelete(int idS) throws SQLException;
	
	public void doUpdate(SottoCatData sottoC,int code) throws SQLException;
	
	public SottoCatData doRetrieveByKey(int idS) throws SQLException;
	
	public SottoCatData doRetrieveByName(String name) throws SQLException;
	
	public Collection<SottoCatData> doRetrieveAll(String order) throws SQLException;
	
	public Collection<SottoCatData> doRetrieveAllbyC(int C) throws SQLException;

	public ArrayList<SottoCatData> getSottoCategorie() throws SQLException;

}