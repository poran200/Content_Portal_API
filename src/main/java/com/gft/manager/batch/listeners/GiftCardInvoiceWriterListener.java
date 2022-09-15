package com.gft.manager.batch.listeners;

import com.gft.manager.model.gft.GiftCardInvoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class GiftCardInvoiceWriterListener implements ItemWriteListener<GiftCardInvoice>, StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        return null;
    }

    @Override
    public void beforeWrite(List<? extends GiftCardInvoice> list) {

    }

    @Override
    public void afterWrite(List<? extends GiftCardInvoice> list) {

    }

    @Override
    public void onWriteError(Exception e, List<? extends GiftCardInvoice> list) {

    }
}
