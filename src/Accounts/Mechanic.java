package Accounts;

import Job.*;

public class Mechanic extends Accounts.User {
	private int hourlyRate;
	private long userID;

	public Mechanic(long userID, String username, String email, String password, String name, int hourlyRate) {
		super(userID, username, email, password, name);
		this.hourlyRate = hourlyRate;
		this.userID = userID;
	}

	@Override
	public long getUserID() {
		return userID;
	}

	public JobCollection viewJobSheet() {
		// TODO - implement Mechanic.viewJobSheet
		throw new UnsupportedOperationException();
	}

	public Job findJob() {
		// TODO - implement Mechanic.findJob
		throw new UnsupportedOperationException();
	}

	public Job recieveJob() {
		// TODO - implement Mechanic.recieveJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param status
	 */
	public Job changeJobStatus(boolean status) {
		// TODO - implement Mechanic.changeJobStatus
		throw new UnsupportedOperationException();
	}

	public Mechanic allocateMechanic() {
		// TODO - implement Mechanic.allocateMechanic
		throw new UnsupportedOperationException();
	}

	public Job deleteJob() {
		// TODO - implement Mechanic.deleteJob
		throw new UnsupportedOperationException();
	}

	public Job searchJob() {
		// TODO - implement Mechanic.searchJob
		throw new UnsupportedOperationException();
	}

	public Job addJob() {
		// TODO - implement Mechanic.addJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param duration
	 */
	public Job alterJobDuration(double duration) {
		// TODO - implement Mechanic.alterJobDuration
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public int getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getInstanceClass() {
		return "Mechanic";
	}
}