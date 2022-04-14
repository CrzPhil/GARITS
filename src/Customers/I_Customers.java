package Customers;

import Customers.*;

public interface I_Customers {

    /**
     *
     * @param customerID
     * @param contactNumber
     * @param regularCustomer
     * @param valuedCustomer
     */
    abstract Customers.Customer createCustomer(long customerID, String contactNumber, boolean regularCustomer, boolean valuedCustomer);

    abstract Customers.Customer getCustomer();

    abstract Customers.Vehicle createVehicle();

    abstract Customers.Vehicle getVehicle();

}