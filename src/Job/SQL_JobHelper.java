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

	public Job[] deleteJob(int JobID){
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;

		String deleteJob = String.format("DELETE FROM Jobs WHERE jobID = '%d'", JobID);
		Job[] out = null;

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(deleteJob);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	public Job[] deleteCompletedJob(int JobID){
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;

		String deleteJob = String.format("DELETE FROM CompletedJobs WHERE jobID = '%d'", JobID);
		Job[] out = null;

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(deleteJob);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	// Update all values in row by jobID
	// TODO: status -> tinyint
	public boolean updateCompletedJob(int jobID, String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status) {
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;

			// Update Row with new values
			String updateRow = String.format("UPDATE CompletedJobs SET jobType = '%s', duration = %f, dates = '%s', parts = '%s', motNo = '%s', mileage = %d, price = %f, additionalInfo = '%s', status = %d WHERE jobID = %d",
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



		} else {
			jStatus = 0;

			// Update Row with new values
			String deleteRow = String.format("DELETE FROM CompletedJobs WHERE jobID = '%d'", jobID);
			String sendJob = "INSERT INTO Jobs (jobID, jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, status)";
			String sendValues = String.format(" VALUES ('%d', '%s', '%f', '%s', '%s', '%s', '%d', '%f', '%s', '%s')", jobID, jobType, duration, dates, parts, motNO, mileage, price, additionalInfo, jStatus);

			try {
				Statement st = conn.createStatement();
				st.executeUpdate(deleteRow);
				st.executeUpdate(sendJob + sendValues);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	public boolean updateJob(int jobID, String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status, String regNo) {
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;

			// Update Row with new values
			String deleteRow = String.format("DELETE FROM Jobs WHERE jobID = '%d'", jobID);
			String sendJob = "INSERT INTO CompletedJobs (jobID, jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, status, registrationNo)";
			String sendValues = String.format(" VALUES ('%d', '%s', '%f', '%s', '%s', '%s', '%d', '%f', '%s', '%s')", jobID, jobType, duration, dates, parts, motNO, mileage, price, additionalInfo, jStatus, regNo);

			try {
				Statement st = conn.createStatement();
				st.executeUpdate(deleteRow);
				st.executeUpdate(sendJob + sendValues);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			jStatus = 0;
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

	}

	// TODO: Modify type / Add to controller
	public Job[] sendData(String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status, String regNo){
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;
		} else {
			jStatus = 0;
		}

		String sendJob = "INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, status, registrationNo)";
		String sendValues = String.format(" VALUES ('%s', '%f', '%s', '%s', '%s', '%d', '%f', '%s', '%s', '%s')",
				jobType,
				duration,
				dates,
				parts,
				motNO,
				mileage,
				price,
				additionalInfo,
				jStatus,
				regNo);

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
						Job.getStates()[rs.getInt("status")],
						rs.getString("registrationNo")
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

	public int getJobID(String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String additionalInfo, String completionStatus, String regNo) {
		String qur = String.format("SELECT jobID FROM Jobs WHERE jobType = '%s' AND duration = %f AND dates = '%s' AND motNo = '%s' AND mileage = %d AND price = %f AND additionalinfo = '%s' AND status = '%s' AND registrationNo = '%s'",
				jobType,
				duration,
				dates,
				motNo,
				mileage,
				price,
				additionalInfo,
				completionStatus,
				regNo);
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);
			rs.next();
			return rs.getInt("jobID");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getCompletedJobID(String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String additionalInfo, String completionStatus) {
		String qur = String.format("SELECT jobID FROM CompletedJobs WHERE jobType = '%s' AND duration = %f AND dates = '%s' AND motNo = '%s' AND mileage = %d AND price = %f AND additionalinfo = '%s' AND status = '%s'",
				jobType,
				duration,
				dates,
				motNo,
				mileage,
				price,
				additionalInfo,
				completionStatus);
		try {
			Statement st = conn.createStatement();
			System.out.println("DEB: " + qur);
			ResultSet rs = st.executeQuery(qur);
			rs.next();
			return rs.getInt("jobID");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public CompletedJob[] getCompletedJobs() {
		CompletedJob[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM CompletedJobs ORDER BY jobID DESC";
		String qur = "SELECT * FROM CompletedJobs";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new CompletedJob[size];

			// Get Job
			rs = st.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new CompletedJob(
						rs.getInt("jobID"),
						rs.getString("jobType"),
						rs.getFloat("duration"),
						rs.getString("dates"),
						rs.getString("parts"),
						rs.getString("motNo"),
						rs.getInt("mileage"),
						rs.getFloat("price"),
						rs.getString("additionalInfo"),
						Job.getStates()[rs.getInt("status")],
						rs.getString("registrationNo")
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

	// Create a job-part entry in the Job_SpareParts Table
	public void addToJob(int jobID, String partCode) {
		String qur = String.format("INSERT INTO Job_SpareParts(jobID, partCode) VALUES (%d,'%s')", jobID, partCode);
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(qur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}