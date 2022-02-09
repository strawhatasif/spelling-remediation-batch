package com.fun.data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetadataHandler {

    private static final Logger logger = Logger.getLogger(MetadataHandler.class.getCanonicalName());

    public static void updateStatus() {
        var statement = "UPDATE BATCH_JOB SET JOB_STATUS = ? WHERE JOB_NM = ?";

        PreparedStatement preparedStatement;

        try (var connection = DriverManager.getConnection("jdbc:sqlite:batch.db?journal_mode=WAL")) {

            preparedStatement = connection.prepareStatement(statement);

            preparedStatement.setString(1, "COMPLETED");
            preparedStatement.setString(2, "dataCorrectionBatch");

            preparedStatement.executeUpdate();
        }
        catch (SQLException se) {
            logger.log(Level.SEVERE, "we have an exception connecting to the database: " + se);
        }
    }
}
