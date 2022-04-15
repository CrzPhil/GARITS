package Accounts;

public class Administrator extends Accounts.User {
	
	public Administrator(long userID, String username, String email, String password, String name) {
		super(userID, username, email, password, name);
	}

	// toString method in order for the admin's names to be displayed in the JTree
	@Override
	public String toString() {
		return super.toString();
	}

	public String getInstanceClass() {
		return "Administrator";
	}
}