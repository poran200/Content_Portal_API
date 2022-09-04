package com.gft.manager.service.impl;

import com.gft.manager.batch.BatchUtil;
import com.gft.manager.support.poi.ExcleFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class JobLauncherService {
    private final Job job;
    private final JobLauncher jobLauncher;




    void lunchGiftCardImportJob(Path filePath) throws FileSystemException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("Starting Job....");
        if (Strings.isBlank(filePath.getFileName().toString())){
            log.warn("file path cannot be empty");
            throw new FileSystemException(" file path cannot be empty");
        }
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong(BatchUtil.JOB_ID_KEY, System.currentTimeMillis())
                .addDate("currentTime", new Date())
                .addString(BatchUtil.EXCEL_PATH_KEY,filePath.toAbsolutePath().normalize().toString())
                .toJobParameters();
        jobLauncher.run(job,jobParameters);

        log.info("Stopping job");

    }
}
