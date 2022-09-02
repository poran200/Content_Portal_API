package com.gft.manager.batch.mappers;

import com.gft.manager.model.gft.GiftCardInvoice;
import com.gft.manager.support.poi.CellFactory;
import com.gft.manager.support.poi.RowMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.gft.manager.batch.BatchUtil.getUniqeInvoiceNo;
@Log4j2
public class GiftCardInvoiceRowMapper extends CellFactory implements RowMapper<GiftCardInvoice> {
    @Override
    public GiftCardInvoice transformerRow(Row row) {
        return GiftCardInvoice.builder()
                .invoiceNo((String) getCellValue(row.getCell(0)))
                .uniqueInvoiceNo(getUniqeInvoiceNo())
                .orderDate((String) getCellValue(row.getCell(2)))
                .orderTime((String) getCellValue(row.getCell(3)))
                .customerId((String) getCellValue(row.getCell(4)))
                .coustomerName((String) getCellValue(row.getCell(5)))
                .contactNo((String) getCellValue(row.getCell(6)))
                .coustomerAddress((String) getCellValue(row.getCell(7)))
                .orderQuantity( geCellIntValue(row.getCell(8)))
                .orderPrice( Double.parseDouble((String) getCellValue(row.getCell(9))))
                .actualPrice(Double.parseDouble((String) getCellValue(row.getCell(10))))
                .from((String) getCellValue(row.getCell(11)))
                .validityMonth(geCellIntValue(row.getCell(12)))
                .build();
    }

    private int geCellIntValue(Cell row) {

        Object cellValue = getCellValue(row);
        if (cellValue instanceof Double value){
            return value.intValue();
        }else if (cellValue instanceof Integer value){
            return value;
        }else if (cellValue instanceof String value){
            return Integer.parseInt(value);
        }else {
            log.warn("This cell can not convert to integer ");
            throw new  UnsupportedOperationException("This cell can not convert to integer");
        }

    }

    @Override
    public List<GiftCardInvoice> transformerRowList(Row row) throws ParseException {
        List<GiftCardInvoice> list = new ArrayList<>();
        Double quantity = (Double)getCellValue(row.getCell(8));
        int q = quantity.intValue();
        if (q < 1) {
            return List.of(transformerRow(row));
        } else {
            for (int i = 0; i < q; i++) {
                GiftCardInvoice invoice = transformerRow(row);
                invoice.setOrderQuantity(1);
                list.add(invoice);
            }
            return list;
        }

    }


}
