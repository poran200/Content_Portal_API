package com.gft.manager.model.gft;

import com.gft.manager.model.audit.DateAudit;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Campaign extends DateAudit {

    @Id
    private String id;

    private String name;
    private String providerName;
    private LocalDate startDate;
    private LocalDate endDate;

}
