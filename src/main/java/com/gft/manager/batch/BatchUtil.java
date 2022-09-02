package com.gft.manager.batch;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

public  class BatchUtil {

    public static final String   JOB_ID_KEY = "jobId";
    public static final String EXCEL_PATH_KEY = "excelPath";
    public static String getUniqeInvoiceNo() {
        Date date = Date.from(Instant.now());
        return "GFT"+date.getYear()+date.getSeconds()+getRandomNumber();
    }
    private static int getRandomNumber(){
        return new Random().nextInt(50);
    }
}
