package model.ServiziUtente;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Account.UserData;

public interface CarrelloDataDaoInterface {

	public void doDelete(CarrelloData carrello) throws SQLException;

	public void doSave(CarrelloData carrello) throws SQLException;
	
	public void doUpdate(CarrelloData carrello) throws SQLException;

	public ArrayList<CarrelloData> getCarrello(UserData user) throws SQLException;
	
	public CarrelloData selectCarrello(String email,int idProdotto) throws SQLException;

}
