package com.fun.itemprocessor;

import com.fun.model.SettledAccount;

import javax.batch.api.chunk.ItemProcessor;
import java.util.logging.Logger;

public class CorrectStatusTypoProcessor implements ItemProcessor {
    private static final Logger logger = Logger.getLogger(CorrectStatusTypoProcessor.class.getCanonicalName());
    private static final String CORRECTED_STATUS = "SETTLED";

    @Override
    public Object processItem(Object item) throws Exception {
        var settledAccount = (SettledAccount) item;
        var correctedStatusAccount = new SettledAccount();

       logger.info("ACCOUNT WITH A TYPO IN STATUS? "
                + settledAccount.getName() + " " + settledAccount.getStatus());

        return correctedStatusAccount
                .toBuilder()
                .id(settledAccount.getId())
                .name(settledAccount.getName())
                .product(settledAccount.getProduct())
                .notes(settledAccount.getNotes())
                .status(CORRECTED_STATUS)
                .build();
    }
}
