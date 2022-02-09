package com.fun;

import javax.batch.runtime.BatchRuntime;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getCanonicalName());

    private static final String DATABASE_JOB_NAME = "dataCorrectionBatch";

    public static void main(String[] args) {
        var jobOperator = BatchRuntime.getJobOperator();
        var properties = new Properties();

        var executionId = jobOperator.start(DATABASE_JOB_NAME, properties);
        persistMetadata(executionId);

    }

    private static void persistMetadata(long executionId) {
        String statement = "INSERT INTO BATCH_JOB (JOB_NM, EXECUTION_ID, JOB_DATE, JOB_STATUS) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement;

        try (var connection = DriverManager.getConnection("jdbc:sqlite:batch.db?journal_mode=WAL")) {

            preparedStatement = connection.prepareStatement(statement);

            preparedStatement.setString(1, DATABASE_JOB_NAME);
            preparedStatement.setLong(2, executionId);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(4,"RUNNING");

            preparedStatement.executeUpdate();
        }
        catch (SQLException se) {
            logger.log(Level.SEVERE, "we have an exception connecting to the database: " + se);
        }
    }
}
