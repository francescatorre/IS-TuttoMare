package model.ServiziUtente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Account.UserData;

public interface OrderDataDaoInterface {

	public void doSave(OrderData ordine) throws SQLException;
	
	public void doUpdate( OrderData ordine, int code) throws SQLException;

	public static ArrayList<OrderData> getOrder() throws SQLException {
		return null;
	}
	
	public static ArrayList<OrderData> getOrderByUser(UserData user) throws SQLException {
		return null;
	}
	
	public static Collection<OrderData> getOrdiniByNumeroOrdine(int nOrdine) throws SQLException {
		return null;
	}

	public int getUltimoNumeroO() throws SQLException;

}