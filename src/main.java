import Customers.Customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args) {
        //JobsMenuGUI.main();
        //AdminDashboardGUI.main();
        //ManageDatabaseGUI.main();
        //CustomerAccountMenuGUI.main();
        //ViewCustomerGUI.main();
        CreateVehicleGUI.main(new Customer(1234567891, "x", true));
    }
}
