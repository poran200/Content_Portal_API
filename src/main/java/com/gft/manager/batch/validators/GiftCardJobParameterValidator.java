package com.gft.manager.batch.validators;

import com.gft.manager.batch.BatchUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

@Log4j2
public class GiftCardJobParameterValidator implements JobParametersValidator {
    @Override
    public void validate(final JobParameters jobParameters) throws JobParametersInvalidException {
        String fileName = jobParameters != null ? jobParameters.getString(BatchUtil.EXCEL_PATH_KEY) : null;
        log.info("filename:"+fileName);
        if (fileName == null){
            throw new JobParametersInvalidException("This file type must be null format");
        }
    }
}
