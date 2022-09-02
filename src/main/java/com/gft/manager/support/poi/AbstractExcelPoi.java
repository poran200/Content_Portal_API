package com.gft.manager.support.poi;

import com.monitorjbl.xlsx.StreamingReader;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public abstract class AbstractExcelPoi<T> {

    public abstract void write(String filePath, List<T> aList);

    public List<T> read(String filePath, final RowMapper<T> rowMapper) throws ExcleFileException {
        log.info("File Read start..........");
        List<T> tList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            try (Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(200)
                    .bufferSize(4096)
                    .open(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                sheet.forEach(row -> {
                    if (row.getRowNum() != 0) {
                        try {
                            tList.addAll(rowMapper.transformerRowList(row));
                        } catch (ParseException e) {
                            log.warn("Row mapper parse exception");
                        }
                    }

                });
                log.info("File Rad End .............");
                return tList;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        try (Workbook workbook = getWorkBook(filePath)) {
//            assert workbook != null;
//            Sheet sheet = workbook.getSheetAt(0);
//            sheet.forEach(row -> {
//                if (row.getRowNum() != 0) {
//                    try {
//                        tList.addAll(rowMapper.transformerRowList(row));
//                    } catch (ParseException e) {
//                        log.warn("Row mapper parse exception");
//                    }
//                }
//
//            });
//            log.info("File Rad End .............");
//            return tList;
//        } catch (IOException e) {
//            throw new ExcleFileException(String.format("Can not rad the file: %s", e.getMessage()));
//        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//    private Workbook getWorkBook(String filePath) throws ExcleFileException {
//        Workbook workbook;
//        try {
//            try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
//                if (filePath.endsWith("xlsx")) {
//                    workbook = WorkbookFactory.create(inputStream);
//                } else {
//                    throw new ExcleFileException(" The specified file is not excel file");
//                }
//            }
//        } catch (IOException e) {
//            throw new ExcleFileException(" The specified file is not excel file");
//        }
//
//        return workbook;
//    }
    }
}