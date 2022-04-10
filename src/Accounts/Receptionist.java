package Accounts;

import Database.Alert;
import Job.*;
import Customers.*;
import Payment.*;

public class Receptionist extends User {

	public Booking bookMOTBay() {
		// TODO - implement Receptionist.bookMOTBay
		throw new UnsupportedOperationException();
	}

	public Booking bookRepairBay() {
		// TODO - implement Receptionist.bookRepairBay
		throw new UnsupportedOperationException();
	}

	public Booking SearchBooking() {
		// TODO - implement Receptionist.SearchBooking
		throw new UnsupportedOperationException();
	}

	public Booking editBooking() {
		// TODO - implement Receptionist.editBooking
		throw new UnsupportedOperationException();
	}

	public Payment recordPayment() {
		// TODO - implement Receptionist.recordPayment
		throw new UnsupportedOperationException();
	}

	public Customer addRegularCustomer() {
		// TODO - implement Receptionist.addRegularCustomer
		throw new UnsupportedOperationException();
	}

	public Customer addValuedCustomer() {
		// TODO - implement Receptionist.addValuedCustomer
		throw new UnsupportedOperationException();
	}

	public Job recieveNewJobs() {
		// TODO - implement Receptionist.recieveNewJobs
		throw new UnsupportedOperationException();
	}

	public Alert LowStockAlert() {
		// TODO - implement Receptionist.LowStockAlert
		throw new UnsupportedOperationException();
	}

	public Invoice PrintInvoice() {
		// TODO - implement Receptionist.PrintInvoice
		throw new UnsupportedOperationException();
	}

	public Invoice sendEmail() {
		// TODO - implement Receptionist.sendEmail
		throw new UnsupportedOperationException();
	}

	public SparePartsCollection searchSpareParts() {
		// TODO - implement Receptionist.searchSpareParts
		throw new UnsupportedOperationException();
	}

	public SparePartsCollection recordSparePartsUsed() {
		// TODO - implement Receptionist.recordSparePartsUsed
		throw new UnsupportedOperationException();
	}

	public Receptionist(long userID, String username, String email, String password, String name) {
		super(userID, username, email, password, name);
	}

	public String toString() {
		return super.toString();
	}
}