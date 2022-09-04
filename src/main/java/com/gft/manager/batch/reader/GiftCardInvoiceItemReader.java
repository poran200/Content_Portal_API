package com.gft.manager.batch.reader;

import com.gft.manager.batch.BatchUtil;
import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.service.impl.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.streaming.StreamingXlsxItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
@StepScope
public class GiftCardInvoiceItemReader extends StreamingXlsxItemReader<GiftCardInvoice> implements StepExecutionListener {
    @Autowired
    private FileStorageService fileStorageService;
    private String exclefilePath;
    private int cardIndex = 0;
    private List<GiftCardInvoice> giftCardInvoices = new LinkedList<>();

    @Override
    public void setResource(Resource resource) {
        try {
            super.setResource(new FileSystemResource(fileStorageService.loadFileAsResource(exclefilePath).getFile().getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





    @Override
    public void beforeStep(StepExecution stepExecution) {
        exclefilePath = stepExecution.getJobParameters().getString(BatchUtil.EXCEL_PATH_KEY);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }



    private GiftCardInvoice getGiftCardInvoice() {
        GiftCardInvoice invoice = null;
        if (!this.giftCardInvoices.isEmpty()){
            if (cardIndex < giftCardInvoices.size()) {
                invoice = giftCardInvoices.get(cardIndex);
                cardIndex++;
            } else {
                cardIndex = 0;
            }
            return invoice;
        }else {
            throw  new IllegalArgumentException("This file have no Invoices");
        }

    }
}
