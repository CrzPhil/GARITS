package Accounts;

public class User {

	private long userID;
	private String username;
	private String email;
	private String password;
	private String name;

	/**
	 * 
	 * @param userID
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 */
	public User(long userID, String username, String email, String password, String name) {
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

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
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
	 */
	public void setName(String name) {
		this.name = name;
	}

}