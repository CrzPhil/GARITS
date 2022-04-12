package Job;

import Database.Database_Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_PartsHelper extends Database_Controller {

	public SQL_PartsHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 *
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		// TODO - implement SQL_PartsHelper.generateQuery
		throw new UnsupportedOperationException();
	}

	// Match string with either partName, partCode, or vehicleType; Makes for a generic search.
	public SparePart[] getPartByIdName(String part) {
		// TODO: Get rid of SQLi
		String sizequr = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);
		String qur = String.format("SELECT * FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);

		return getParts(sizequr, qur);
	}


	// Get all different types
	public String[] getTypes() {
		String[] out = null;

		String sizequr = "SELECT COUNT(DISTINCT vehicleType) AS Count FROM SpareParts";
		String qur = "SELECT DISTINCT vehicleType FROM SpareParts";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			// We add one entry for the default Select value in the GUI, in case the user wants to search
			out = new String[size+1];

			// Get spare parts
			rs = st.executeQuery(qur);

			out[0] = "";

			// Once again reserving out[0] for the default -> ""
			int i = 1;

			while (rs.next()) {
				out[i] = rs.getString("vehicleType");
				i++;
			}

			rs.close();
			st.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	// Get all unique part names
	public String[] getPartNames() {
		String sizequr = "SELECT COUNT(DISTINCT partName) AS Count FROM SpareParts";
		String qur = "SELECT DISTINCT partName FROM SpareParts";

		String[] out;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new String[size];

			rs = st.executeQuery(qur);

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

	// Get all parts
	public SparePart[] getAllParts() {
		String sizequr = "SELECT COUNT(*) AS Count FROM SpareParts";
		String qur = "SELECT * FROM SpareParts";

		return getParts(sizequr, qur);
	}

	// Get spare parts by certain query (Has to be SELECT * [...])
	private SparePart[] getParts(String sizequr, String qur) {
		SparePart[] out = null;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new SparePart[size];

			// Get spare parts
			rs = st.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new SparePart(
						rs.getString("code"),
						rs.getString("partName"),
						rs.getString("manufacturer"),
						rs.getString("vehicleType"),
						rs.getInt("year"),
						rs.getInt("stock"),
						rs.getDouble("price")
				);
				i++;
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		this.closeConnection();
		return out;
	}

	// Updates item stock by partID
	public void updateStock(int stock, String partID) {
		String qur = String.format("UPDATE SpareParts SET stock = %d WHERE code = '%s'", stock, partID);
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(qur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get all parts used on a job (from Job_SpareParts) using jobID
	public SparePart[] getJobParts(int jobID) {
		String sizequr = "SELECT COUNT(*) AS Count FROM SpareParts t1 INNER JOIN Job_SpareParts JSP on t1.code = JSP.partCode WHERE jobID = " + jobID;
		String qur = "SELECT JSP.ID, t1.* FROM SpareParts t1 INNER JOIN Job_SpareParts JSP on t1.code = JSP.partCode WHERE jobID = " + jobID;

		SparePart[] out;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new SparePart[size];

			rs = st.executeQuery(qur);

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

	// Delete a Part that's assigned to a job (from Job_SpareParts) using partID
	public void deleteJobPart(int partID) {
		String qur = "DELETE FROM Job_SpareParts WHERE ID = " + partID;
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(qur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}