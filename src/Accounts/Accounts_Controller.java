package Accounts;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
	Controller for everything account-related.
	Creating / Modifying / Deleting accounts runs through this class.
 */
public class Accounts_Controller implements I_Accounts {
	private final SQL_UserHelper helper = new SQL_UserHelper();
	private final UserCollection collection = new UserCollection();

	/**
	 * Method to create a user inside the DB and collection class
	 *
	 * @param fname Staff First Name
	 * @param lname Staff Last Name
	 * @param username Staff Username
	 * @param password Staff password -> passed as char[] from passwordField.getPassword()
	 * @param role Staff role
	 * @param mail Staff email
	 * @param rate Staff hourly rate
	 * @return User object
	 */
	public User createUser(String fname, String lname, String username, char[] password, String role, String mail, String rate) {
		// Create the user in the Database
		helper.createStaff(fname, lname, username, password, role, rate, mail);
		long staffID;
		String hashpass;

		switch (role) {
			case "Administrator" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Administrator(staffID, username, mail, hashpass, fname + " " + lname));
			}
			case "Franchisee" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Franchisee(staffID, username, mail, hashpass, fname + " " + lname));
			}
			case "Mechanic" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Mechanic(staffID, username, mail, hashpass, fname + " " + lname, Integer.parseInt(rate)));
			}
			case "Receptionist" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new Receptionist(staffID, username, mail, hashpass, fname + " " + lname));
			}
			case "Foreperson" -> {
				// Query DB for ID & hashed pass
				staffID = helper.getID(username);
				hashpass = helper.getPass(username);

				// Create a local object & store object in collection
				collection.addItem(staffID, new ForePerson(staffID, username, mail, hashpass, fname + " " + lname, Integer.parseInt(rate)));
			}
			default -> {
			}
		}

		helper.closeConnection();
		return null;
	}

	/**
	 * Method to modify a user in the DB and collection class.
	 * @param fname Staff First Name
	 * @param lname Staff Last Name
	 * @param username Staff Username
	 * @param role Staff role
	 * @param rate Staff hourly rate
	 * @param mail Staff email
	 * @param staffID ID of the member being modified. (This doesn't get modified, but serves as the PK to access the user)
	 * @return Boolean: whether edit was successful or not.
	 */
	public boolean modifyAccount(String fname, String lname, String username, String role, String rate, String mail, long staffID) {
		return helper.updateStaff(fname, lname, username, role, rate, mail, staffID);
	}

	/**
	 * Delete user (staff) from Database
	 * @param userID staffID to delete the user from.
	 */
	public boolean removeAccount(long userID) {
		SQL_UserHelper helper = new SQL_UserHelper();
		boolean userDeleted = false;

		if (helper.getStaff(userID) != null) {
			helper.deleteStaff(userID);
			userDeleted = true;
		}

		helper.closeConnection();

		return userDeleted;
	}

	/**
	 *
	 * @param userID get User by ID
	 */
	public boolean getAccount(long userID) {
		// TODO - implement Accounts_Controller.getAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * Check regular expression for matches. Used in field validation.
	 * @param regex Custom regex
	 * @param text Text to check regex against
	 * @return Boolean: whether a match was found in the text.
	 */
	private boolean checkRegex(String regex, String text) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * Username has to start with a letter; be between 5-29 chars; No special chars;
	 * @param username username to check against regex
	 * @return Boolean: whether a match was found in the text.
	 */
	public boolean validateUsername(String username) {
		String usernameRegex = "^[A-Za-z]\\w{4,29}$";
		return checkRegex(usernameRegex, username);
	}

	/**
	 * Email regex filter.
	 * @param email email to check.
	 * @return Boolean: whether a match was found in the text.
	 */
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
	//	  ! Demo values are way simpler than this, so this function will not be used !
	public boolean validatePassword(String password) {
		String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		return checkRegex(passRegex, password);
	}

	/**
	 * Simple password checker as opposed to validatePassword(), to allow for simple demo values.
	 * @param password password to check
	 * @return Boolean: whether a match was found in the text.
	 */
	public boolean simplePasswordCheck(String password) {
		String passRegex = "[A-Z]\\w+";
		return checkRegex(passRegex, password);
	}

	/**
	 * Check for a valid name. No numbers, etc.
	 * @param name name to check.
	 * @return Boolean: whether a match was found in the text.
	 */
	public boolean validateName(String name) {
		String nameRegex = "(?i)[a-z](.{1,}[a-z])?";
		return checkRegex(nameRegex, name);
	}

	/**
	 * Check whether a role exists
	 * @param role role to check
	 * @return Boolean: whether a role exists
	 */
	public boolean validateRole(String role) {
		String[] tmp = {"Administrator","Franchisee","Receptionist","Foreperson","Mechanic"};
		Set<String> roles = new LinkedHashSet<String>(Arrays.asList(tmp));

		return roles.contains(role);
	}

	/**
	 * Regex for price (two digits after comma)
	 * @param rate Rate as price i.e 105.50
	 * @return Boolean: whether it's a valid price
	 */
	public boolean validateRate(String rate) {
		String rateRegex = "^[1-9]\\d*$";
		return checkRegex(rateRegex, rate);
	}

	/**
	 * Get all mechanics
	 * @return array of all mechanics in DB
	 */
	public User[] getMechanics() {
		return helper.getByRole("Mechanic");
	}
}