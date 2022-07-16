package com.fun.listeners;

import javax.batch.api.listener.StepListener;
import java.util.logging.Logger;

public class CustomStepListener implements StepListener {
    private static final Logger logger = Logger.getLogger(CustomStepListener.class.getCanonicalName());

    @Override
    public void beforeStep() throws Exception {
        logger.warning("Beginning step!");
    }

    @Override
    public void afterStep() throws Exception {
        logger.warning("Step complete!");
    }
}
