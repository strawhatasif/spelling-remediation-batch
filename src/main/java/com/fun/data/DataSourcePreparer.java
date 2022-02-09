package com.fun.data;

import org.sqlite.SQLiteDataSource;

public class DataSourcePreparer {

    public static SQLiteDataSource setDataSource(boolean writeAhead) {
        var dataSource = new SQLiteDataSource();
        var url = writeAhead ? "jdbc:sqlite:batch.db?journal_mode=WAL" : "jdbc:sqlite:batch.db";
        dataSource.setUrl(url);
        return dataSource;
    }
}
