package com.gft.manager.repository;

import com.gft.manager.model.gft.GiftCardInvoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardInvoiceRepository extends MongoRepository<GiftCardInvoice,String> {
}
