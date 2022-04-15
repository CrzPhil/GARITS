package Accounts;

public class Receptionist extends User {

	public Receptionist(long userID, String username, String email, String password, String name) {
		super(userID, username, email, password, name);
	}

	public String toString() {
		return super.toString();
	}

	public String getInstanceClass() {
		return "Receptionist";
	}
}