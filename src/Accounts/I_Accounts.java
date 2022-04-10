package Accounts;

import Accounts.*;

public interface I_Accounts {


    abstract Accounts.User createUser(String fname, String lname, String username, char[] password, String role, String mail, String rate);

    /**
     *
     * @param userID
     */
    abstract Accounts.User modifyAccount(long userID);

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