package Accounts;

public class ForePerson extends Receptionist {

	public ForePerson(long userID, String username, String email, String password, String name) {
		super(userID, username, email, password, name);
	}
	public String toString() {
		return super.toString();
	}
}