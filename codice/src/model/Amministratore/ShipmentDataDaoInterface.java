package model.Amministratore;

import java.sql.SQLException;
import java.util.Collection;

public interface ShipmentDataDaoInterface {

	public void doDelete(ShipmentData spedizione) throws SQLException;

	public void doSave(ShipmentData spedizione) throws SQLException;

	public Collection<ShipmentData>getShipment() throws SQLException;
	
	public ShipmentData getShipmentDataById(ShipmentData spedizione) throws SQLException;

}
