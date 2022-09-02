package com.gft.manager.batch.reader;

import com.gft.manager.batch.BatchUtil;
import com.gft.manager.batch.mappers.GiftCardInvoiceRowMapper;
import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.service.impl.FileStorageService;
import com.gft.manager.support.poi.AbstractExcelPoi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class GiftCardInvoiceItemReader extends AbstractExcelPoi<GiftCardInvoice> implements ItemReader<GiftCardInvoice>, StepExecutionListener {
    @Autowired
    private FileStorageService fileStorageService;
    private String exclefilePath;
    private int cardIndex = 0;
    private List<GiftCardInvoice> giftCardInvoices = new LinkedList<>();

    @Override
    public void write(String filePath, List<GiftCardInvoice> aList) {
        throw new NotImplementedException("No need to implement this method in the context");

    }


    @Override
    public void beforeStep(StepExecution stepExecution) {
        exclefilePath = stepExecution.getJobParameters().getString(BatchUtil.EXCEL_PATH_KEY);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public GiftCardInvoice read() throws IOException {

        if (this.giftCardInvoices.isEmpty()) {
            log.info("Read the file ");
            String path = fileStorageService.loadFileAsResource(exclefilePath).getFile().getPath();
            this.giftCardInvoices = read(path, new GiftCardInvoiceRowMapper());
        }
        return getGiftCardInvoice();

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
