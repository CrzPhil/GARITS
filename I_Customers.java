import Customers.*;

public interface I_Customers {

	/**
	 * 
	 * @param customerID
	 * @param contactNumber
	 * @param regularCustomer
	 * @param valuedCustomer
	 */
	abstract Customer createCustomer(long customerID, string contactNumber, boolean regularCustomer, boolean valuedCustomer);

	abstract Customer getCustomer();

	abstract Vehicle createVehicle();

	abstract Vehicle getVehicle();

	abstract MOT_Reminder createReminder();

	abstract MOT_Reminder getReminder();

}