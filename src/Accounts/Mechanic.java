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