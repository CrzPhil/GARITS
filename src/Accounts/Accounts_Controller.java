package Accounts;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Accounts_Controller implements I_Accounts {
	private final SQL_UserHelper helper = new SQL_UserHelper();
	private final UserCollection collection = new UserCollection();

	public User createUser(String fname, String lname, String username, char[] password, String role, String mail, String rate) {
		// Create the user in the Database
		helper.createStaff(fname, lname, username, password, role, rate);
		long staffID;
		String hashpass;

		switch (role) {
			case "Administrator" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Administrator(staffID, username, null, hashpass, fname + " " + lname, Integer.parseInt(rate)));
			}
			case "Franchisee" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Franchisee(staffID, username, null, hashpass, fname + " " + lname));
			}
			case "Mechanic" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Mechanic(staffID, username, null, hashpass, fname + " " + lname));
			}
			case "Receptionist" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Receptionist(staffID, username, null, hashpass, fname + " " + lname));
			}
			case "Foreperson" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new ForePerson(staffID, username, null, hashpass, fname + " " + lname));
			}
			default -> {
			}
		}

		helper.closeConnection();
		return null;
	}

	/**
	 * 
	 * @param userID
	 */
	public User modifyAccount(long userID) {
		// TODO - implement Accounts_Controller.modifyAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * Delete user (staff) from Database
	 * @param userID
	 */
	public boolean removeAccount(long userID) {
		SQL_UserHelper helper = new SQL_UserHelper();
		boolean userDeleted = false;

		if (helper.getStaff(userID) != null) {
			helper.deleteStaff(userID);
		}

		helper.closeConnection();

		return userDeleted;
	}

	/**
	 * 
	 * @param userID
	 */
	public boolean getAccount(long userID) {
		// TODO - implement Accounts_Controller.getAccount
		throw new UnsupportedOperationException();
	}

	private boolean checkRegex(String regex, String text) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		return m.matches();
	}

	// Username has to start with a letter; be between 5-29 chars; No special chars;
	public boolean validateUsername(String username) {
		String usernameRegex = "^[A-Za-z]\\w{4,29}$";
		return checkRegex(usernameRegex, username);
	}

	// Generic email filter
	public boolean validateEmail(String email) {
		String emailRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		return checkRegex(emailRegex, email);
	}

	//    It contains at least 8 characters and at most 20 characters.
	//    It contains at least one digit.
	//    It contains at least one upper case alphabet.
	//    It contains at least one lower case alphabet.
	//    It contains at least one special character which includes !@#$%&*()-+=^.
	//    It doesn’t contain any white space.
	public boolean validatePassword(String password) {
		String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		return checkRegex(passRegex, password);
	}

	// Simple comparison against a set of Roles
	// TODO: Make some sort of check to see what roles exist
	public boolean validateRole(String role) {
		String[] tmp = {"Administrator","Franchisee","Receptionist","Foreperson","Mechanic"};
		Set<String> roles = new LinkedHashSet<String>(Arrays.asList(tmp));

		return roles.contains(role);
	}

	// Simple price matcher (float with 2 digits behind the comma)
	public boolean validateRate(String rate) {
		String rateRegex = "^[1-9]\\d*$";
		return checkRegex(rateRegex, rate);
	}
}