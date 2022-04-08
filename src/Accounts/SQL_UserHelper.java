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

	// Authentication function
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

	// Get the role of a username
	public String getRole(String username) {
		String qur = String.format("SELECT role FROM Staff WHERE username LIKE '%s'", username);
		String role = null;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);

			rs.next();

			role = rs.getString("role");

			rs.close();
			st.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return role;
	}

	// Get a user by role
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

	// Matches First name, last name, username for easy recognition; returns full name
	public String getStaff(String any) {
		String getsize = String.format("SELECT COUNT(*) AS Count FROM Staff FROM Staff WHERE firstName LIKE '%s' OR lastName LIKE '%s' OR username LIKE '%s' OR ", any, any, any);
		String qur = String.format("SELECT firstName, lastName FROM Staff WHERE firstName LIKE '%s' OR lastName LIKE '%s' OR username LIKE '%s' OR ", any, any, any);

		return getStaffName(getsize, qur);
	}

	// Get Staff by ID
	public String getStaff(long staffID) {
		String getsize = String.format("SELECT COUNT(*) AS Count FROM Staff FROM Staff WHERE staffID LIKE %d", staffID);
		String qur = String.format("SELECT firstName, lastName FROM Staff WHERE staffID LIKE %d", staffID);

		return getStaffName(getsize, qur);
	}

	private String getStaffName(String getsize, String qur) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(getsize);

			// Get count for the returned rows to match the array to that size (WIP)
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			String name = null;

			rs = st.executeQuery(qur);

			if (size == 1) {
				name = rs.getString("firstName") + " ";
				name += rs.getString("lastName");
			}

			rs.close();
			st.close();

			return name;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteStaff(long userID) {
			String qur = String.format("DELETE FROM Staff WHERE staffID LIKE '%d'", userID);
			try {
				Statement st = conn.createStatement();
				st.executeUpdate(qur);
				st.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}