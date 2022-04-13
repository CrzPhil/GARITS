package Accounts;

import Database.Database_Controller;

import java.sql.PreparedStatement;
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
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();

			rs.next();

			login = rs.getInt("Count") == 1;

			pSt.close();
			rs.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return login;
	}

	// Get the role of a username -> used for privileges
	public String getRole(String username) {
		String qur = String.format("SELECT role FROM Staff WHERE username LIKE '%s'", username);
		String role = null;

		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();

			rs.next();

			role = rs.getString("role");

			rs.close();
			pSt.close();
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
			PreparedStatement pSt = conn.prepareStatement(getsize);
			ResultSet rs = pSt.executeQuery();

			// Get count for the returned rows to match the array to that size (WIP)
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new User[size];

			// Get users
			rs = pSt.executeQuery(qur);

			int i = 0;
			String name;

			while (rs.next()) {
				switch (role) {
					case "Administrator" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Administrator(rs.getInt("staffID"),
								rs.getString("username"),
								rs.getString("email"),
								rs.getString("password"),
								name
								);
					}
					case "Franchisee" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Franchisee(rs.getInt("staffID"),
								rs.getString("username"),
								rs.getString("email"),
								rs.getString("password"),
								name);
					}
					case "Mechanic" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Mechanic(rs.getInt("staffID"),
								rs.getString("username"),
								rs.getString("email"),
								rs.getString("password"),
								name,
								rs.getInt("hourlyRate"));
					}
					case "Foreperson" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new ForePerson(rs.getInt("staffID"),
								rs.getString("username"),
								rs.getString("email"),
								rs.getString("password"),
								name,
								rs.getInt("hourlyRate"));
					}
					case "Receptionist" -> {
						// Combine first and last name
						name = rs.getString("firstName") + " " + rs.getString("lastName");
						out[i] = new Receptionist(rs.getInt("staffID"),
								rs.getString("username"),
								rs.getString("email"),
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
		String getsize = String.format("SELECT COUNT(*) AS Count FROM Staff WHERE firstName LIKE '%s' OR lastName LIKE '%s' OR username LIKE '%s'", any, any, any);
		String qur = String.format("SELECT firstName, lastName FROM Staff WHERE firstName LIKE '%s' OR lastName LIKE '%s' OR username LIKE '%s'", any, any, any);

		return getStaffName(getsize, qur);
	}

	// Get Staff by ID
	public String getStaff(long staffID) {
		String getsize = String.format("SELECT COUNT(*) AS Count FROM Staff WHERE staffID = %d", staffID);
		String qur = String.format("SELECT firstName, lastName FROM Staff WHERE staffID = %d", staffID);

		return getStaffName(getsize, qur);
	}

	// Get ID by username
	public long getID(String username) {
		String qur = String.format("SELECT staffID FROM Staff WHERE username LIKE '%s'", username);

		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();

			rs.next();

			long id = rs.getLong("staffID");

			rs.close();
			pSt.close();

			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// Get Hashed pass by username
	public String getPass(String username) {
		String qur = String.format("SELECT password FROM Staff WHERE username LIKE '%s'", username);

		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();

			rs.next();

			String hashedpass = rs.getString("password");

			rs.close();
			pSt.close();

			return hashedpass;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getStaffName(String getsize, String qur) {
		try {
			PreparedStatement pSt = conn.prepareStatement(getsize);
			ResultSet rs = pSt.executeQuery();

			// Get count for the returned rows to match the array to that size (WIP)
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			String name = null;

			rs = pSt.executeQuery(qur);

			rs.next();

			if (size == 1) {
				name = rs.getString("firstName") + " ";
				name += rs.getString("lastName");
			}

			rs.close();
			pSt.close();

			return name;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Create a Staff account in the database
	public void createStaff(String fname, String lname, String username, char[] password, String role, String rate, String mail) {
		HashClass hasher = new HashClass();
		String hashedpass = hasher.chartosha256(password);

		try {
			PreparedStatement pSt;
			pSt = conn.prepareStatement("INSERT INTO Staff(firstName, lastName, role, username, password, hourlyRate, email)" + " VALUES(?, ?, ?, ?, ?, ?, ?)");
			pSt.setString(1, fname);
			pSt.setString(2, lname);
			pSt.setString(3, role);
			pSt.setString(4, username);
			pSt.setString(5, hashedpass);
			pSt.setInt(6, Integer.valueOf(rate));
			pSt.setString(7, mail);
			pSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStaff(long userID) {
			String qur = String.format("DELETE FROM Staff WHERE staffID = %d", userID);
			try {
				//SQL sanitization to prevent SQL injection attacks
				PreparedStatement pSt;
				pSt = conn.prepareStatement("DELETE FROM Staff WHERE staffID = ?");
				pSt.setLong(1, userID);
				pSt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	public boolean updateStaff(String fname, String lname, String username, String role, String rate, String mail, long staffID)	{
		String qur;
		if (rate.equals("NULL")) {
			try {
				PreparedStatement pSt;
				pSt = conn.prepareStatement("UPDATE Staff SET firstName = ?, lastName = ?, role = ?, username = ?, hourlyRate = NULL, email = ? WHERE staffID = ?");
				pSt.setString(1, fname);
				pSt.setString(2, lname);
				pSt.setString(3, role);
				pSt.setString(4, username);
				pSt.setString(5, mail);
				pSt.setLong(6, staffID);
				pSt.executeUpdate();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				PreparedStatement pSt;
				pSt = conn.prepareStatement("UPDATE Staff SET firstName = ?, lastName = ?, role = ?, username = ?, hourlyRate = ?, email = ? WHERE staffID = ?");
				pSt.setString(1, fname);
				pSt.setString(2, lname);
				pSt.setString(3, role);
				pSt.setString(4, username);
				pSt.setInt(5, Integer.parseInt(rate));
				pSt.setString(6, mail);
				pSt.setLong(7, staffID);
				pSt.executeUpdate();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	public boolean updatePassword(long staffID, char[] newpass) {
		HashClass hasher = new HashClass();
		String hashedpass = hasher.chartosha256(newpass);

		String qur = String.format("UPDATE Staff SET password = '%s' WHERE staffID = %d", hashedpass, staffID);

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("UPDATE Staff SET password = ? WHERE staffID = ?");
			pSt.setString(1, hashedpass);
			pSt.setLong(2, staffID);
			pSt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}