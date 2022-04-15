package Database;

import Database.*;

import java.sql.Connection;

public interface I_Database {

    abstract Connection connectToDb();

    abstract void closeConnection();

    abstract boolean backupDatabase();

    abstract boolean restoreDatabase(String filePath);

}