package com.gft.manager.controller;

import com.gft.manager.dto.CardSearchReq;
import com.gft.manager.dto.GiftCardInvoiceDto;
import com.gft.manager.service.impl.GiftCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class GiftCardInvoiceController {
    private final GiftCardService giftCardService;

    @PostMapping
    public ResponseEntity<List<GiftCardInvoiceDto>> searchByInvoiceId(@RequestBody CardSearchReq req){
        return ResponseEntity.ok(giftCardService.getByInvoiceOrUniqueId(req.getInvoiceId(),req.getUniqInvoiceId()));
    }

    @GetMapping
    public ResponseEntity<Page<GiftCardInvoiceDto>> getAllInvoices(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "100") int itemPerPage){
        PageRequest request = PageRequest.of(page, itemPerPage);
        return ResponseEntity.ok(giftCardService.getNotIssueInvoices(request));
    }
}
