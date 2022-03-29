package Accounts;

public class Administrator extends Accounts.User {

	private int hourlyrate;

	// Administrator method to create a new User - To be used in Account management
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

	// toString method in order for the admin's names to be displayed in the JTree
	@Override
	public String toString() {
		return "Administrator{" +
				"name='" + name + '\'' +
				'}';
	}

	// Debug method to print account details
	public void printAccount() {
		System.out.println("[DEBUG]");
		System.out.println(this.userID);
		System.out.println(this.name);
		System.out.println(this.email);
		System.out.println(this.password);
		System.out.println(this.hourlyrate);
	}

	public Administrator(long userID, String username, String email, String password, String name, int hourlyrate) {
		super(userID, username, email, password, name);
		this.hourlyrate = hourlyrate;
	}

	public int getHourlyrate() {
		return hourlyrate;
	}

	public void setHourlyrate(int hourlyrate) {
		this.hourlyrate = hourlyrate;
	}

}