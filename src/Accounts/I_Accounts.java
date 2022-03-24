package Accounts;

import Accounts.*;

public interface I_Accounts {

    /**
     *
     * @param userID
     * @param username
     * @param email
     * @param password
     * @param name
     */
    abstract Accounts.User createUser(long userID, String username, String email, String password, String name);

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