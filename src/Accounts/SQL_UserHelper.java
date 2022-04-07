package Accounts;

import Database.Database_Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_UserHelper extends Database_Controller {

	public SQL_UserHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		this.query = command;
		return this.query;
	}

	// Might be unnecessary
	public boolean compareCredentials(String username, String password) {
		String qur = String.format("SELECT Count(*) AS Count FROM Staff WHERE username LIKE '%s' AND password LIKE '%s'", username, password);

		boolean login = false;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);

			rs.next();

			login = rs.getInt("Count") == 1;

			st.close();
			rs.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return login;
	}

	public User[] getByRole(String role) {
		String getsize = String.format("SELECT COUNT(*) AS Count FROM Staff WHERE role LIKE '%s'", role);
		String qur = String.format("SELECT * FROM Staff WHERE role LIKE '%s'", role);
		User[] out = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(getsize);

			// Get count for the returned rows to match the array to that size (WIP)
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new User[size];

			// Get users
			rs = st.executeQuery(qur);

			int i = 0;
			String name;

			while (rs.next()) {
				switch (role) {
					case "Administrator" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Administrator(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name,
								rs.getInt("hourlyrate"));
					}
					case "Franchisee" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Franchisee(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name);
					}
					case "Mechanic" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Mechanic(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name);
					}
					case "Foreperson" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new ForePerson(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name);
					}
					case "Receptionist" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Receptionist(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name);
					}
					default -> System.out.println("[DEBUG] Role not found!");
				}
				i++;
			}
			rs.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return out;
	}

}