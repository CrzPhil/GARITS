package Accounts;

public class User {

	private long userID;
	private string username;
	private string email;
	private string password;
	private string name;

	/**
	 * 
	 * @param userID
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 */
	public User(long userID, string username, string email, string password, string name) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	public long getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @param userID
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	public string getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(string username) {
		this.username = username;
	}

	public string getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(string email) {
		this.email = email;
	}

	public string getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(string password) {
		this.password = password;
	}

	public string getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
		this.name = name;
	}

}