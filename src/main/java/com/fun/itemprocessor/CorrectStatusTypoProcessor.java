package com.fun.itemprocessor;

import com.fun.model.SettledAccount;

import javax.batch.api.chunk.ItemProcessor;
import java.util.logging.Logger;

public class CorrectStatusTypoProcessor implements ItemProcessor {
    private static final Logger logger = Logger.getLogger(CorrectStatusTypoProcessor.class.getCanonicalName());
    private static final String CORRECTED_STATUS = "SETTLED";

    @Override
    public Object processItem(Object item) throws Exception {
        if (item instanceof SettledAccount settledAccount) {

            logger.warning("ACCOUNT WITH A TYPO IN STATUS? "
                    + settledAccount.name() + " " + settledAccount.status());

            return new SettledAccount(settledAccount.id(),
                    settledAccount.name(),
                    settledAccount.product(),
                    settledAccount.notes(),
                    CORRECTED_STATUS);
        }

        return null;
    }
}
