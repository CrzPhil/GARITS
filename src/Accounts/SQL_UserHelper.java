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

	public void getByName(String fname, String lname) {
		System.out.println("Querying user " + fname + " " + lname + " ... ");
		String qur = String.format("SELECT * FROM Staff WHERE firstName LIKE '%s' OR lastName LIKE '%s'", fname, lname);

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);
			while (rs.next()) {
				int s = rs.getInt("staffID");
				String f = rs.getString("firstName");
				String l = rs.getString("lastName");
				String r = rs.getString("role");
				String u = rs.getString("username");
				String p = rs.getString("password");
				int h = rs.getInt("hourlyRate");
				System.out.println("");
				System.out.println(f + " " + l + " " + r + " " + u + " " + p + " " + h);
			}
			rs.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public User[] getByRole(String role, boolean debug) {
		String sizequr = String.format("SELECT COUNT(*) AS Count FROM Staff WHERE role LIKE '%s'", role);
		String qur = String.format("SELECT * FROM Staff WHERE role LIKE '%s'", role);
		User[] out = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for the returned rows to match the array to that size (WIP)
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			// Get users
			rs = st.executeQuery(qur);
			out = new User[size];

			int i = 0;
			String name;

			while (rs.next()) {
				switch (role) {
					case "Administrator":
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Administrator(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name,
								rs.getInt("hourlyrate"));
						if (debug) {
							((Administrator) out[i]).printAccount();
						}
						break;
					case "Franchisee":
						break;
					case "Mechanic":
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Mechanic(rs.getInt("staffID"),
								rs.getString("username"),
								null, //rs.getString("email"),
								rs.getString("password"),
								name);
						break;
					case "Foreperson":
						break;
					case "Receptionist":
						break;
					default:
						if (debug)
							System.out.println("[DEBUG] Role not found!");
						break;
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