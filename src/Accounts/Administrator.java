package Accounts;

public class Administrator extends Accounts.User {

	public Accounts.User createUserAccount(long userID, String username, String email, String password, String name) {
		return new User(userID, username, email, password, name);
	}

	public Accounts.User modifyAccount() {
		// TODO - implement Administrator.modifyAccount
		throw new UnsupportedOperationException();
	}

	public boolean removeAccount(long userID) {
		// TODO - implement Administrator.removeAccount
		throw new UnsupportedOperationException();
	}

	public Accounts.User searchAccount() {
		// TODO - implement Administrator.searchAccount
		throw new UnsupportedOperationException();
	}

	public Administrator(long userID, String username, String email, String password, String name) {
		super(userID, username, email, password, name);
	}

}