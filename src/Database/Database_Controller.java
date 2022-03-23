package Database;

public class Database_Controller implements I_Database {

	private string query;

	public string getQuery() {
		return this.query;
	}

	/**
	 * 
	 * @param query
	 */
	public void setQuery(string query) {
		this.query = query;
	}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public string generateQuery(Object obj, string command) {
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