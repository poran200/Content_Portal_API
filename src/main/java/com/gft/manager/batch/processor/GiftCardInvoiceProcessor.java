package com.gft.manager.batch.processor;

import com.gft.manager.batch.BatchUtil;
import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.repository.GiftCardInvoiceRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GiftCardInvoiceProcessor implements ItemProcessor<GiftCardInvoice,GiftCardInvoice> {

    private final GiftCardInvoiceRepository giftCardInvoiceRepository;
//    private final Campaign campaign;
    private List<GiftCardInvoice> giftCardInvoices;

    public GiftCardInvoiceProcessor(GiftCardInvoiceRepository giftCardInvoiceRepository) {
        this.giftCardInvoiceRepository = giftCardInvoiceRepository;
    }

    @Override
    public GiftCardInvoice process(GiftCardInvoice giftCardInvoice) throws Exception {
        giftCardInvoices = new ArrayList<>();
        int orderQuantity = giftCardInvoice.getOrderQuantity();
        if (orderQuantity > 1) {
            for (int i = 0; i < orderQuantity; i++) {
                giftCardInvoice.setOrderQuantity(1);
//                giftCardInvoice.setCampaign(campaign);
                giftCardInvoice.setUniqueInvoiceNo(BatchUtil.getUniqueInvoiceNo());
                giftCardInvoices.add(giftCardInvoice);
            }
            giftCardInvoiceRepository.saveAll(giftCardInvoices);
        }
//        giftCardInvoice.setCampaign(campaign);
        giftCardInvoice.setUniqueInvoiceNo(BatchUtil.getUniqueInvoiceNo());
        return giftCardInvoice;
    }
}
