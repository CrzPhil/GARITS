package Customers;

import Customers.*;

public interface I_Customers {

    abstract boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount);

    abstract Customer[] getCustomers();

    abstract boolean createVehicle(Vehicle vehicle);

    abstract boolean vehicleExists(String regNo);

}