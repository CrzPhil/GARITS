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

	public SparePart[] getPartByIdName(String part) {
		// TODO: Get rid of SQLi
		String sizequr = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);
		String qur = String.format("SELECT * FROM SpareParts WHERE code LIKE '%s' OR partName LIKE '%s' OR vehicleType LIKE '%s'", part, part, part);

		return getParts(sizequr, qur);
	}

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
}