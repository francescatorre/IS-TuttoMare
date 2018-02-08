package model.Account;

import java.sql.SQLException;
import java.util.Collection;

public interface AddressDataDaoInterface {

	public void doDelete(AddressData address) throws SQLException;

	public void doSave(AddressData address) throws SQLException;
	
	public AddressData doSaveAndReturn(AddressData address) throws SQLException;
	
	public  Collection<AddressData> searchAddress(UserData user) throws SQLException;

	public AddressData searchAddressByKey(AddressData adderess) throws SQLException;

}
