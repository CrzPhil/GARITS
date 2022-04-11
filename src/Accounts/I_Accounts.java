package Accounts;

import Accounts.*;

public interface I_Accounts {


    abstract Accounts.User createUser(String fname, String lname, String username, char[] password, String role, String mail, String rate);

    abstract boolean modifyAccount(String fname, String lname, String username, String role, String rate, String mail, long staffID);

    /**
     *
     * @param userID
     */
    abstract boolean removeAccount(long userID);

    /**
     *
     * @param userID
     */
    abstract boolean getAccount(long userID);

}