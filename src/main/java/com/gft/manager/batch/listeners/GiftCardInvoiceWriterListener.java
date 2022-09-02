package com.gft.manager.batch.listeners;

import com.gft.manager.model.gft.GiftCardInvoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class GiftCardInvoiceWriterListener implements ItemReadListener<GiftCardInvoice> {

    @Override
    public void beforeRead() {
        // TODO document why this method is empty
    }

    @Override
    public void afterRead(GiftCardInvoice giftCardInvoice) {
        log.info("After read ");
        log.info("Invoice no: {}", giftCardInvoice.getInvoiceNo());
    }

    @Override
    public void onReadError(Exception e) {
        log.error(" Excepting hapen on write: {}",e.getMessage());
    }
}
