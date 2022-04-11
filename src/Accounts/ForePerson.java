package Accounts;

public class ForePerson extends Receptionist {
	private int hourlyRate;

	public ForePerson(long userID, String username, String email, String password, String name, int rate) {
		super(userID, username, email, password, name);
		this.hourlyRate = rate;
	}
	public String toString() {
		return super.toString();
	}

	public String getInstanceClass() {
		return "Foreperson";
	}

	public int getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
}