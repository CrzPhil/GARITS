package Job;

import Database.Database_Controller;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
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


		Job[] out = null;

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("delete from Jobs where jobID = ?");
			pSt.setInt(1, JobID);
			pSt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	// Update job with new values
	public boolean updateJob(int jobID, String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status, long mechanicID) {
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;

		} else {
			jStatus = 0;
		}
			try {
				//SQL sanitization to prevent SQL injection attacks
				PreparedStatement pSt;
				pSt = conn.prepareStatement("UPDATE Jobs SET jobType = ?, duration = ?, dates = ?, parts = ?, motNo = ?, mileage = ?, price = ?, additionalInfo = ?, status = ?, mechanicID = ? WHERE jobID = ?");
				pSt.setString(1, jobType);
				pSt.setFloat(2, duration);
				pSt.setString(3, dates);
				pSt.setString(4, parts);
				pSt.setString(5, motNO);
				pSt.setInt(6, mileage);
				pSt.setFloat(7, price);
				pSt.setString(8, additionalInfo);
				pSt.setInt(9, jStatus);
				pSt.setLong(10, mechanicID);
				pSt.setInt(11, jobID);

				pSt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}


	// TODO: Modify type / Add to controller
	public Job[] createJob(String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String additionalInfo, String status, String regNo){
		// Since we store status as a tinyint, 1 -> Complete 0 -> Incomplete
		int jStatus;
		if (Objects.equals(status, "Complete")) {
			jStatus = 1;
		} else {
			jStatus = 0;
		}

		Job[] out = null;

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, status, registrationNo)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pSt.setString(1, jobType);
			pSt.setFloat(2, duration);
			pSt.setString(3, dates);
			pSt.setString(4, parts);
			pSt.setString(5, motNO);
			pSt.setInt(6, mileage);
			pSt.setFloat(7, price);
			pSt.setString(8, additionalInfo);
			pSt.setInt(9, jStatus);
			pSt.setString(10, regNo);
			pSt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}

	// Get all jobs from database
	public Job[] getJobs() {
		Job[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs WHERE status = 0 ORDER BY jobID DESC";
		String qur = "SELECT * FROM Jobs WHERE status = 0";

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Job[size];

			// Get Job
			rs = pSt.executeQuery(qur);

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
			pSt.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		this.closeConnection();
		return out;
	}

	// Get Completed jobs from database
	public Job[] getCompletedJobs() {
		Job[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs WHERE status = 1 ORDER BY jobID DESC";
		// Status = 1 means job is complete
		String qur = "SELECT * FROM Jobs WHERE status = 1";

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Job[size];

			// Get Job
			rs = pSt.executeQuery(qur);

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
			pSt.close();
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

	// Create a job-part entry in the Job_SpareParts Table
	public void addToJob(int jobID, String partCode) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("INSERT INTO Job_SpareParts(jobID, partCode) VALUES (?,?)");
			pSt.setInt(1, jobID);
			pSt.setString(2, partCode);
			pSt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}