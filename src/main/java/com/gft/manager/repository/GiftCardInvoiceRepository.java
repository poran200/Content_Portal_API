package com.gft.manager.repository;

import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.model.gft.GiftCardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftCardInvoiceRepository extends MongoRepository<GiftCardInvoice,String> {

    List<GiftCardInvoice> findByInvoiceNo(String uniqInvoiceNo);

    Page<GiftCardInvoice> findAllByCampaignId(String id, Pageable pageable);
    Page<GiftCardInvoice> findAllByStatus(GiftCardStatus status,Pageable pageable);
}
