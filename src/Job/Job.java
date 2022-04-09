package Job;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Job {



	private int jobID;
	private final Set<String> jobTypes =
			Stream.of("MOT", "Service")
					.collect(Collectors.toCollection(HashSet::new));
	private String jobType;
	private String dates;
	private float price;
	private String motNO;
	private String parts;
	private float duration;
	private int mileage;
	private String additionalInfo;
	private String status;

	/**
	 *
	 * @param jobID
	 * @param jobType
	 * @param dates
	 * @param price
	 * @param motNo
	 * @param parts
	 * @param duration
	 * @param mileage
	 * @param additionalInfo
	 */
	public Job(int jobID, String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String additionalInfo) {
		this.jobID = jobID;
		this.jobType = jobType;
		this.duration = duration;
		this.dates = dates;
		this.parts = parts;
		this.motNO = motNo;
		this.mileage = mileage;
		this.price = price;
		this.additionalInfo = additionalInfo;
		this.status = "Incomplete";
	}

    public Job() {

    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.jobID + "    " + this.dates + "    " + this.jobType + "    ￡" + this.price;
	}

	public void update_on_job() {
		// TODO - implement Job.update_on_job
		throw new UnsupportedOperationException();
	}

	public void show_job_status() {
		// TODO - implement Job.show_job_status
		throw new UnsupportedOperationException();
	}



	public void partsAvaliable() {
		// TODO - implement Job.partsAvaliable
		throw new UnsupportedOperationException();
	}

	public int getJobID() {
		return jobID;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getMotNO() {
		return motNO;
	}

	public void setMotNO(String motNO) {
		this.motNO = motNO;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Set<String> getJobTypes() {
		return jobTypes;
	}
}