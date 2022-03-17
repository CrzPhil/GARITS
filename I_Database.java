public interface I_Database {

	abstract string getQuery();

	/**
	 * 
	 * @param query
	 */
	abstract void setQuery(string query);

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	abstract string generateQuery(Object obj, string command);

	abstract void read();

	abstract boolean write();

}