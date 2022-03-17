import Accounts.*;

public interface I_Accounts {

	/**
	 * 
	 * @param userID
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 */
	abstract User createUser(long userID, string username, string email, string password, string name);

	/**
	 * 
	 * @param userID
	 */
	abstract User modifyAccount(long userID);

	/**
	 * 
	 * @param userID
	 */
	abstract boolean removeAccount(long userID);

	/**
	 * 
	 * @param userID
	 */
	abstract boolean getAccount(long userID);

}