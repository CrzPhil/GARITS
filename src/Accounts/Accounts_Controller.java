package Accounts;

import Accounts.*;

public class Accounts_Controller implements I_Accounts {

	/**
	 *  @param userID
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 * @return
	 */
	public Accounts.User createUser(long userID, String username, String email, String password, String name) {
		return new User(userID, username, email, password, name);
	}

	/**
	 * 
	 * @param userID
	 */
	public Accounts.User modifyAccount(long userID) {
		// TODO - implement Accounts_Controller.modifyAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * Delete user (staff) from Database
	 * @param userID
	 */
	public boolean removeAccount(long userID) {
		SQL_UserHelper helper = new SQL_UserHelper();
		boolean userDeleted = false;

		if (helper.getStaff(userID) != null) {
			helper.deleteStaff(userID);
		}

		helper.closeConnection();

		return userDeleted;
	}

	/**
	 * 
	 * @param userID
	 */
	public boolean getAccount(long userID) {
		// TODO - implement Accounts_Controller.getAccount
		throw new UnsupportedOperationException();
	}

}