package com.fun.listeners;

import com.fun.data.DataHandler;
import com.fun.data.MetadataHandler;

import javax.batch.api.listener.JobListener;
import java.util.logging.Logger;

public class CustomJobListener implements JobListener {
    private static final Logger logger = Logger.getLogger(CustomJobListener.class.getCanonicalName());

    @Override
    public void beforeJob() throws Exception {
        DataHandler.deleteData();
        DataHandler.insertData();
        logger.info("Beginning job execution.");
    }

    @Override
    public void afterJob() throws Exception {
        MetadataHandler.updateStatus();
        logger.info("Job execution complete!");
    }
}
