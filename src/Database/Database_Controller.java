package Database;

import Database.*;

public class Database_Controller implements I_Database {

	private String query;

	public String getQuery() {
		return this.query;
	}

	/**
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		// TODO - implement Database_Controller.generateQuery
		throw new UnsupportedOperationException();
	}

	public void read() {
		// TODO - implement Database_Controller.read
		throw new UnsupportedOperationException();
	}

	public boolean write() {
		// TODO - implement Database_Controller.write
		throw new UnsupportedOperationException();
	}

}