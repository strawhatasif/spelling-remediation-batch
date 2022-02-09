package com.fun.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataSourcePreparerTest {

    @Test
    public void givenWriteAheadLoggingTrue_expectWALInConnectionURL() {
        var dataSource = DataSourcePreparer.setDataSource(true);
        assertEquals("jdbc:sqlite:batch.db?journal_mode=WAL", dataSource.getUrl());
    }

    @Test
    public void givenWriteAheadLoggingFalse_doNotExpectWALInConnectionURL() {
        var dataSource = DataSourcePreparer.setDataSource(false);
        assertEquals("jdbc:sqlite:batch.db", dataSource.getUrl());
    }
}