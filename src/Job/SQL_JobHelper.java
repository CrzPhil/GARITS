package Job;

import Database.Database_Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SQL_JobHelper extends Database_Controller {

	public SQL_JobHelper() {
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

	// Update all values in row by jobID
	// TODO: status -> tinyint
	public boolean updateJob(long jobID, String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status) {
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;
		} else {
			jStatus = 0;
		}
		// Update Row with new values
		String updateRow = String.format("UPDATE Jobs SET jobType = '%s', duration = %f, dates = '%s', parts = '%s', motNo = '%s', mileage = %d, price = %f, additionalInfo = '%s', status = %d WHERE jobID = %d",
				jobType,
				duration,
				dates,
				parts,
				motNO,
				mileage,
				price,
				additionalInfo,
				jStatus,
				jobID);
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(updateRow);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// TODO: Modify type
	public Job[] sendData(String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status){
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;
		} else {
			jStatus = 0;
		}

		String sendJob = "INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, status)";
		String sendValues = String.format(" VALUES ('%s', '%f', '%s', '%s', '%s', '%d', '%f', '%s', '%s')", jobType, duration, dates, parts, motNO, mileage, price, additionalInfo, jStatus);
		Job[] out = null;

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sendJob + sendValues);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	// Get all jobs from database
	public Job[] getJobs() {
		Job[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs ORDER BY jobID DESC";
		String qur = "SELECT * FROM Jobs";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Job[size];

			// Get Job
			rs = st.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new Job(
						rs.getInt("jobID"),
						rs.getString("jobType"),
						rs.getFloat("duration"),
						rs.getString("dates"),
						rs.getString("parts"),
						rs.getString("motNo"),
						rs.getInt("mileage"),
						rs.getFloat("price"),
						rs.getString("additionalInfo"),
						Job.getStates()[rs.getInt("status")]
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