package Job;

public class MOT extends Job {

	private boolean pass;
	private long mileage;
	private long MOTTestNumber;
	private date expiryDate;
	private string testLocation;
	private string defects;
	private date dateTested;

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

	public date getExpirtDate() {
		// TODO - implement MOT.getExpirtDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param expirtDate
	 */
	public void setExpirtDate(date expirtDate) {
		// TODO - implement MOT.setExpirtDate
		throw new UnsupportedOperationException();
	}

	public string getTestLocation() {
		return this.testLocation;
	}

	/**
	 * 
	 * @param testLocation
	 */
	public void setTestLocation(string testLocation) {
		this.testLocation = testLocation;
	}

	public string getDefects() {
		return this.defects;
	}

	/**
	 * 
	 * @param Defects
	 */
	public void setDefects(string Defects) {
		this.defects = Defects;
	}

	public date getDateTested() {
		return this.dateTested;
	}

	/**
	 * 
	 * @param dateTested
	 */
	public void setDateTested(date dateTested) {
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
	public MOT(boolean pass, long mileage, long testNo, date expiry, string loc, string defects, date dateTested) {
		// TODO - implement MOT.MOT
		throw new UnsupportedOperationException();
	}

}