package Job;

import Database.Database_Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_PartsHelper extends Database_Controller {

	public SQL_PartsHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 * Match string with either partName, partCode, or vehicleType; Makes for a generic search.
	 * @param part detail about part
	 * @return SparePart array
	 */
	public SparePart[] getPartByIdName(String part) {
		String sizequr = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);
		String qur = String.format("SELECT * FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);

		return getParts(sizequr, qur);
	}

	/**
	 * Create new spare part with custom stock in DB
	 * @param code code
	 * @param name name
	 * @param make make
	 * @param model model
	 * @param year year
	 * @param stock stock
	 * @param price price
	 * @param threshold threshold
	 * @return Boolean if creation was successful
	 */
	public boolean createSparePart(String code, String name, String make, String model, int year, int stock, float price, int threshold) {

			try {
				//SQL sanitization to prevent SQL injection attacks
				PreparedStatement pSt;
				pSt = conn.prepareStatement("INSERT INTO SpareParts VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pSt.setString(1, code);
				pSt.setString(2, name);
				pSt.setString(3, make);
				pSt.setString(4, model);
				pSt.setInt(5, year);
				pSt.setInt(6, stock);
				pSt.setFloat(7, price);
				pSt.setInt(8, threshold);
				pSt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

	}

	/**
	 * Check if spare part with this code already exists
	 * @param code code
	 * @return boolean
	 */
	public boolean getCode(String code) {
		String qur = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE code = '%s'", code);
		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();

			rs.next();

			// If part exists return true
			if (rs.getInt("Count") == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Get all different types of Spare Part
	 * @return String Array of types
	 */
	public String[] getTypes() {
		String[] out = null;

		String sizequr = "SELECT COUNT(DISTINCT vehicleType) AS Count FROM SpareParts";
		String qur = "SELECT DISTINCT vehicleType FROM SpareParts";

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			// We add one entry for the default Select value in the GUI, in case the user wants to search
			out = new String[size+1];

			// Get spare parts
			rs = pSt.executeQuery(qur);

			out[0] = "";

			// Once again reserving out[0] for the default -> ""
			int i = 1;

			while (rs.next()) {
				out[i] = rs.getString("vehicleType");
				i++;
			}

			rs.close();
			pSt.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	/**
	 * Get all unique part names
	 * @return String Array of part names
	 */
	public String[] getPartNames() {
		String sizequr = "SELECT COUNT(DISTINCT partName) AS Count FROM SpareParts";
		String qur = "SELECT DISTINCT partName FROM SpareParts";

		String[] out;
		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new String[size];

			rs = pSt.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = rs.getString("partName");
				++i;
			}
			return out;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * Get all parts
	 * @return SparePart Array of all parts
	 */
	public SparePart[] getAllParts() {
		String sizequr = "SELECT COUNT(*) AS Count FROM SpareParts";
		String qur = "SELECT * FROM SpareParts";

		return getParts(sizequr, qur);
	}

	/**
	 * Get all spare parts relating to the Vehicle type/make
	 * @param regNo Vehicle's reg No to get make/model
	 * @return SparePart array of parts
	 */
	public SparePart[] getSpecificParts(String regNo) {
		SparePart[] out = null;
		// Query to get vehicle type and make from regNo
		String vehicTypeQur = String.format("SELECT make, model FROM Vehicles WHERE registrationNO = '%s'", regNo);

		try {
			// Get Vehicle make / model
			PreparedStatement pSt = conn.prepareStatement(vehicTypeQur);
			ResultSet rs = pSt.executeQuery();
			rs.next();

			String make = rs.getString("make");
			String model = rs.getString("model");

			// Get Parts corresponding to that make / model
			String sizequr = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE (manufacturer = '%s' OR manufacturer = 'All') AND (vehicleType = '%s' OR vehicleType = 'All')",
					make,
					model);
			String qur = String.format("SELECT * FROM SpareParts WHERE (manufacturer = '%s' OR manufacturer = 'All') AND (vehicleType = '%s' OR vehicleType = 'All')",
					make,
					model);

			out = getParts(sizequr, qur);

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return out;
	}

	/**
	 * Get spare parts by certain query (Has to be SELECT * [...])
	 * @param sizequr Size query (query with Count(*) to get rowcount)
	 * @param qur Actual query
	 * @return Array of Spare Parts with size of Count(*) generated through query.
	 */
	private SparePart[] getParts(String sizequr, String qur) {
		SparePart[] out = null;

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new SparePart[size];

			// Get spare parts
			rs = pSt.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new SparePart(
						rs.getString("code"),
						rs.getString("partName"),
						rs.getString("manufacturer"),
						rs.getString("vehicleType"),
						rs.getInt("year"),
						rs.getInt("stock"),
						rs.getDouble("price"),
						rs.getInt("threshold")
				);
				i++;
			}
			rs.close();
			pSt.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		this.closeConnection();
		return out;
	}

	/**
	 * Updates item stock by partID
	 * @param stock NewStock
	 * @param partID Code of Part
	 */
	public void updateStock(int stock, String partID) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("UPDATE SpareParts SET stock = ? WHERE code = ?");
			pSt.setInt(1, stock);
			pSt.setString(2, partID);
			pSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all parts used on a job (from Job_SpareParts) using jobID
	 * @param jobID jobID
	 * @return Return Spare Parts
	 */
	public SparePart[] getJobParts(int jobID) {
		String sizequr = "SELECT COUNT(*) AS Count FROM SpareParts t1 INNER JOIN Job_SpareParts JSP on t1.code = JSP.partCode WHERE jobID = " + jobID;
		String qur = "SELECT JSP.ID, t1.* FROM SpareParts t1 INNER JOIN Job_SpareParts JSP on t1.code = JSP.partCode WHERE jobID = " + jobID;

		SparePart[] out;

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new SparePart[size];

			rs = pSt.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new SparePart(
						rs.getInt("ID"),
						rs.getString("code"),
						rs.getString("partName"),
						rs.getString("manufacturer"),
						rs.getString("vehicleType"),
						rs.getInt("year"),
						rs.getInt("stock"),
						rs.getDouble("price")
				);
				++i;
			}
			return out;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * Delete a Part that's assigned to a job (from Job_SpareParts) using partID
	 * @param partID partID
	 */
	public void deleteJobPart(int partID) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("DELETE FROM Job_SpareParts WHERE ID = ?");
			pSt.setInt(1, partID);
			pSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update method for part editing gui
	 * @param code code
	 * @param name name
	 * @param make make
	 * @param model model
	 * @param year year
	 * @param stock stock
	 * @param price price
	 * @param threshold threshold
	 * @return whether update was successful
	 */
	public boolean updateSparePart(String code, String name, String make, String model, int year, int stock, float price, int threshold) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("UPDATE SpareParts SET partName = ?, manufacturer = ?, vehicleType = ?, year = ?, stock = ?, price = ?, threshold = ? WHERE code = '" + code +"'");
			pSt.setString(1, name);
			pSt.setString(2, make);
			pSt.setString(3, model);
			pSt.setInt(4, year);
			pSt.setInt(5, stock);
			pSt.setFloat(6, price);
			pSt.setInt(7, threshold);
			pSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}