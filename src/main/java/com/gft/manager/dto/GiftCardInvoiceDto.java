package com.gft.manager.dto;

import com.gft.manager.model.gft.GiftCardStatus;
import lombok.Data;

@Data
public class GiftCardInvoiceDto {
    private String id;
    private String invoiceNo;
    private String uniqueInvoiceNo;
    private String orderDate;
    private String coustomerName;
    private String contactNo;
    private Double actualPrice;
    private GiftCardStatus status;
}
