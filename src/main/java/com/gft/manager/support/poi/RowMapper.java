package com.gft.manager.support.poi;

import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.util.List;

public interface RowMapper<T> {

    T transformerRow(Row row) throws ParseException;
    List<T> transformerRowList(Row row) throws ParseException;
}
