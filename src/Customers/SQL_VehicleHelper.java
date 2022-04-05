package Customers;

import Database.Database_Controller;

public class SQL_VehicleHelper extends Database_Controller {

	public SQL_VehicleHelper() { this.conn = super.connectToDb();}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		this.query = command;
		return this.query;
	}

}