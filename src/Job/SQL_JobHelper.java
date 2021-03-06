package Job;

import Database.Database_Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SQL_JobHelper extends Database_Controller {

	public SQL_JobHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 * Delete a job by job ID
	 * @param JobID jobID
	 */
	public void deleteJob(int JobID){
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

	}

	/**
	 * Update job with new values
	 * @param jobID jobID
	 * @param jobType jobType
	 * @param duration duration
	 * @param dates dates
	 * @param motNO motNO
	 * @param mileage mileage
	 * @param price price
	 * @param additionalInfo Tasks done
	 * @param status status
	 * @param mechanicID ID of Mechanic who worked on the job
	 * @return Whether update was successful
	 */
	public boolean updateJob(int jobID, String jobType, float duration, String dates, String motNO, int mileage, float price, String additionalInfo, String status, long mechanicID) {
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
				pSt = conn.prepareStatement("UPDATE Jobs SET jobType = ?, duration = ?, dates = ?, motNo = ?, mileage = ?, price = ?, additionalInfo = ?, status = ?, mechanicID = ? WHERE jobID = ?");
				pSt.setString(1, jobType);
				pSt.setFloat(2, duration);
				pSt.setString(3, dates);
				pSt.setString(4, motNO);
				pSt.setInt(5, mileage);
				pSt.setFloat(6, price);
				pSt.setString(7, additionalInfo);
				pSt.setInt(8, jStatus);
				pSt.setLong(9, mechanicID);
				pSt.setInt(10, jobID);

				pSt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}


	/**
	 * Create new Job
	 * @param jobType        jobType
	 * @param dates          dates
	 * @param motNO          motNO
	 * @param mileage        mileage
	 * @param additionalInfo Tasks done
	 * @param status         status
	 * @param regNo          registration Number of Vehicle worked on
	 */
	public void createJob(String jobType, float duration, String dates, String motNO, int mileage, String additionalInfo, String status, String regNo){
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
			pSt = conn.prepareStatement("INSERT INTO Jobs (jobType, duration, dates, motNo, mileage, additionalInfo, status, registrationNo)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			pSt.setString(1, jobType);
			pSt.setFloat(2, duration);
			pSt.setString(3, dates);
			pSt.setString(4, motNO);
			pSt.setInt(5, mileage);
			pSt.setString(6, additionalInfo);
			pSt.setInt(7, jStatus);
			pSt.setString(8, regNo);
			pSt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

	}

	/**
	 * Get all incomplete jobs from database
	 * @return Job Array of all Jobs
	 */
	public Job[] getJobs() {
		Job[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs WHERE status = 0 ORDER BY jobID DESC";
		String qur = "SELECT * FROM Jobs WHERE status = 0";

		return getJobs(out, sizequr, qur);
	}

	/**
	 * Get All Jobs depending on query
	 * @param out Array of Jobs
	 * @param sizequr Length of Rows returned + Size of out array
	 * @param qur Actual Query
	 * @return Job Array of Jobs
	 */
	private Job[] getJobs(Job[] out, String sizequr, String qur) {
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

	/**
	 * Get Completed jobs from database
	 * @return Job Array of completed jobs
	 */
	public Job[] getCompletedJobs() {
		Job[] out = null;

		// Order Jobs, starting from the most recent so it's listed at the top in the gui
		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs WHERE status = 1 ORDER BY jobID DESC";
		// Status = 1 means job is complete
		String qur = "SELECT * FROM Jobs WHERE status = 1";

		return getJobs(out, sizequr, qur);
	}

	/**
	 * Get ID of a newly created Job
	 * Matches all the details to a jobID
	 * @param jobType jobType
	 * @param duration duration
	 * @param dates dates
	 * @param motNo motNo
	 * @param mileage mileage
	 * @param additionalInfo additionalInfo
	 * @param completionStatus completionStatus
	 * @param regNo Registration number of vehicle worked on
	 * @return jobID
	 */
	public int getJobID(String jobType, float duration, String dates, String motNo, int mileage, String additionalInfo, String completionStatus, String regNo) {
		String qur = String.format("SELECT jobID FROM Jobs WHERE jobType = '%s' AND duration = %f AND dates = '%s' AND motNo = '%s' AND mileage = %d AND additionalinfo = '%s' AND status = '%s' AND registrationNo = '%s'",
				jobType,
				duration,
				dates,
				motNo,
				mileage,
				additionalInfo,
				completionStatus,
				regNo);
		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();
			rs.next();
			return rs.getInt("jobID");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Create a job-part entry in the Job_SpareParts Table
	 * @param jobID jobID
	 * @param partCode partCode
	 */
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