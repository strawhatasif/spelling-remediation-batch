package com.fun.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataHandler {

    private static final Logger logger = Logger.getLogger(DataHandler.class.getCanonicalName());

    /**
     * DISCLAIMER: DO NOT DO THIS IN A PRODUCTION APPLICATION, OR ONE THAT IS NOT A PROTOTYPE!
     *
     * This is to guarantee a clean database on each execution.
     */
    public static void deleteData() {
        try (var connection = connectToDatabase()) {

            var statement = connection.createStatement();
            statement.execute("DELETE FROM BATCH_JOB");
            statement.execute("DELETE FROM ACCOUNT");
        }
        catch (SQLException se) {
            logger.log(Level.SEVERE, "we have an exception connecting to the database: " + se);
        }
    }

    /**
     * DISCLAIMER: DO NOT DO THIS IN A PRODUCTION APPLICATION, OR ONE THAT IS NOT A PROTOTYPE!
     *
     * This is to prep the data required for the batch job execution.
     */
    public static void insertData() {
        try (var connection = connectToDatabase()) {
            var statement = connection.createStatement();
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (1,'John Doe','Platinum credit card','high roller - likes to go to vegas','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (2,'Rachel Sauce','Regular checking account','very angry on last call','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (3,'Billy Red','Special combined credit card','VIP customer','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (4,'Isabel Bella','Regular checking account','does not like being called izzy','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (5,'Rebecca Jones','VIP credit card','insider flag - is an Executive','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (6,'Jessica Carson','Student checking account','attends college','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (7,'Sam Kennison','Original checking account','customer since 1997','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (8,'Laura Peterson','Special combined credit card','pays on time','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (9,'Richard Dunham','Regular checking account','prefers being called Rich','SETTTLED');");
            statement.execute("INSERT INTO ACCOUNT (ID, NAME, PRODUCT, NOTES, STATUS) VALUES (10,'Chloe Jackson','Platinum credit card','frequent traveler','SETTTLED');");
        }
        catch (SQLException se) {
            logger.log(Level.SEVERE, "we have an exception connecting to the database: " + se);
        }
    }

    protected static Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:batch.db?journal_mode=WAL");
    }
}
