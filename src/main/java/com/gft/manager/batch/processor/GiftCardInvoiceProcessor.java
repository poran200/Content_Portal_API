package com.gft.manager.batch.processor;

import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.repository.GiftCardInvoiceRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GiftCardInvoiceProcessor implements ItemProcessor<GiftCardInvoice,GiftCardInvoice> {
    @Autowired
    private GiftCardInvoiceRepository giftCardInvoiceRepository;
    private List<GiftCardInvoice> giftCardInvoices;

    @Override
    public GiftCardInvoice process(GiftCardInvoice giftCardInvoice) throws Exception {
        giftCardInvoices = new ArrayList<>();
        int orderQuantity = giftCardInvoice.getOrderQuantity();
        if (orderQuantity > 1) {
            for (int i = 0; i < orderQuantity; i++) {
                giftCardInvoice.setOrderQuantity(1);
            }
            giftCardInvoiceRepository.saveAll(giftCardInvoices);
        }
        return giftCardInvoice;
    }
}
