package Accounts;

public class User {

	protected long userID;
	protected String username;
	protected String email;
	protected String password;
	protected String name;

	/**
	 * 
	 * @param userID
	 * Primary key for User object
	 * @param username
	 * Username used to log into GARITS
	 * @param email
	 * Email associated to the account
	 * @param password
	 * Hashed password
	 * @param name
	 * Employee name
	 */
	public User(long userID, String username, String email, String password, String name) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public User() {

	}

	public long getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @param userID
	 * New userID
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 * New username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 * New email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 * New hashed password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 * New name
	 */
	public void setName(String name) {
		this.name = name;
	}

}