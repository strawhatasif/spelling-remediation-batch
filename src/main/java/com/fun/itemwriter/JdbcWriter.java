package com.fun.itemwriter;

import com.fun.data.DataSourcePreparer;
import com.fun.model.SettledAccount;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.util.ObjectUtils;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemWriter;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcWriter implements ItemWriter {

    @BatchProperty
    @Inject
    private String sqlStatement;

    private JdbcBatchItemWriter<SettledAccount> writer;
    private ExecutionContext ec = null;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        ec = (ExecutionContext) checkpoint;

        if (ObjectUtils.isEmpty(ec))  ec = new ExecutionContext();

        writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(DataSourcePreparer.setDataSource(false));
        writer.setSql(sqlStatement);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setAssertUpdates(false);
        writer.afterPropertiesSet();
    }

    @Override
    public void close() throws Exception {
        //does nothing
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        var settledAccounts =
                items.stream()
                        .map(SettledAccount.class::cast)
                        .collect(Collectors.toList());

        writer.write(settledAccounts);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return ec;
    }
}
