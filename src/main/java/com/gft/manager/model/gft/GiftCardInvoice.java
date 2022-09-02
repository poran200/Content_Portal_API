package com.gft.manager.model.gft;

import com.gft.manager.model.audit.DateAudit;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "giftCardInvoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftCardInvoice extends DateAudit {
    @Id
    private String id;
    @Indexed(name = "invoice_index")
    private String invoiceNo;

    private String uniqueInvoiceNo;
    private String orderDate;
    private String orderTime;
    private String customerId;
    private String coustomerName;
    private String contactNo;
    private String coustomerAddress;
    private int orderQuantity;
    private Double orderPrice;
    private Double actualPrice;
    private String from;
    private int validityMonth;
    private GiftCardStatus status;
    private String issueForm;
    private String outLet;
    private LocalDateTime redeemedTime;
    private Boolean smsSentStatus;
    private String trackingNumber;
    @DBRef
    private Campaign campaign;
}
