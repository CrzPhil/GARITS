package src.Database;

import src.Database.*;

public interface I_Database {

    abstract String getQuery();

    /**
     *
     * @param query
     */
    abstract void setQuery(String query);

    /**
     *
     * @param obj
     * @param command
     */
    abstract String generateQuery(Object obj, String command);

    abstract void read();

    abstract boolean write();

}