package Accounts;

import java.util.HashMap;

public class UserCollection {

	private int index = 0;
	// Map userID to corresponding user
	static private HashMap<Long, User> userCollection;

	/**
	 * 
	 * @param userID Identifier of User
	 * @param user User Object (can be any subclass)
	 */
	public void addItem(long userID, User user) {
		userCollection.put(userID, user);
	}

	/**
	 * 
	 * @param userID
	 */
	public void removeItem(long userID) {
		userCollection.remove(userID);
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
		userCollection = new HashMap<Long, User>();
	}

}