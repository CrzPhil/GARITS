package Accounts;

public class UserCollection {

	private int index = 0;

	/**
	 * 
	 * @param userID
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 */
	public void addItem(long userID, String username, String email, String password, String name) {
		// TODO - implement UserCollection.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public boolean removeItem(long userID) {
		// TODO - implement UserCollection.removeItem
		throw new UnsupportedOperationException();
	}

	public int getIndex() {
		return this.index;
	}

	/**
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public UserCollection() {
		// TODO - implement UserCollection.UserCollection
		throw new UnsupportedOperationException();
	}

}