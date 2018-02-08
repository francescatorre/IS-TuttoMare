package model.Account;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDataDaoInterace {
	public static boolean doSave(UserData user) throws SQLException {
		return true;
		}

		public void doUpdate(UserData user) throws SQLException;

		public static boolean doDelete(UserData user) throws SQLException {
			return false;
		}

		public static UserData searchUser(String email) throws SQLException{
			return null;
		}

		public static ArrayList<UserData> returnUser() throws SQLException {
			return null;
		}
}