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

	public SparePart[] getByID(String partID) {
		String getsize = String.format("SELECT COUNT(*) AS Count FROM SpareParts WHERE code LIKE %s", partID);
		String qur = String.format("SELECT * FROM SpareParts WHERE code LIKE %s", partID);
		SparePart[] out = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(getsize);

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
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return out;
	}

	public SparePart[] getByName(String partName) {
		throw new UnsupportedOperationException();
	}

	public SparePart[] getByPart(String partType) {
		throw new UnsupportedOperationException();
	}
}