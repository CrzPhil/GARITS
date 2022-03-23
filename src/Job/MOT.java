package Job;

import java.util.Date;

public class MOT extends Job {

	private boolean pass;
	private long mileage;
	private long MOTTestNumber;
	private Date expiryDate;
	private String testLocation;
	private String defects;
	private Date dateTested;

	public boolean getPass() {
		return this.pass;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public long getMileage() {
		return this.mileage;
	}

	/**
	 * 
	 * @param mileage
	 */
	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public long getMOTTestNumber() {
		// TODO - implement MOT.getMOTTestNumber
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param MOTTestNumber
	 */
	public void setMOTTestNumber(long MOTTestNumber) {
		// TODO - implement MOT.setMOTTestNumber
		throw new UnsupportedOperationException();
	}

	public Date getExpirtDate() {
		// TODO - implement MOT.getExpirtDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param expirtDate
	 */
	public void setExpirtDate(Date expirtDate) {
		// TODO - implement MOT.setExpirtDate
		throw new UnsupportedOperationException();
	}

	public String getTestLocation() {
		return this.testLocation;
	}

	/**
	 * 
	 * @param testLocation
	 */
	public void setTestLocation(String testLocation) {
		this.testLocation = testLocation;
	}

	public String getDefects() {
		return this.defects;
	}

	/**
	 * 
	 * @param Defects
	 */
	public void setDefects(String Defects) {
		this.defects = Defects;
	}

	public Date getDateTested() {
		return this.dateTested;
	}

	/**
	 * 
	 * @param dateTested
	 */
	public void setDateTested(Date dateTested) {
		this.dateTested = dateTested;
	}

	/**
	 * 
	 * @param pass
	 * @param mileage
	 * @param testNo
	 * @param expiry
	 * @param loc
	 * @param defects
	 * @param dateTested
	 */
	public MOT(boolean pass, long mileage, long testNo, Date expiry, String loc, String defects, Date dateTested) {
		// TODO - implement MOT.MOT
		throw new UnsupportedOperationException();
	}

}