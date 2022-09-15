package com.gft.manager.service.impl;

import com.gft.manager.dto.GiftCardInvoiceDto;
import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.model.gft.GiftCardStatus;
import com.gft.manager.repository.GiftCardInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCardService {
    private final GiftCardInvoiceRepository invoiceRepository;
    private final ModelMapper mapper;

    public List<GiftCardInvoiceDto> getByInvoiceOrUniqueId(String invoiceId, String uniqueId){
       return invoiceRepository.findByUniqueInvoiceNoOrInvoiceNo(invoiceId,uniqueId)
                .stream().map( giftCardInvoice -> mapper.map(giftCardInvoice, GiftCardInvoiceDto.class))
                .toList();
    }

    public Page<GiftCardInvoiceDto> getNotIssueInvoices(Pageable pageable){
        return  invoiceRepository.findAllByStatus(GiftCardStatus.NOT_ISSUE,pageable)
                .map(giftCardInvoice -> mapper.map(giftCardInvoice, GiftCardInvoiceDto.class));
    }
}
