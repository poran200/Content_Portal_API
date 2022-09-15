package com.gft.manager.batch.listeners;

import com.gft.manager.model.gft.GiftCardInvoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Log4j2
public class GiftCardInvoiceItemReaderListener implements ItemReadListener<GiftCardInvoice> , JobExecutionListener {
    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(GiftCardInvoice giftCardInvoice) {

    }

    @Override
    public void onReadError(Exception e) {
      log.error(e.getMessage());
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
