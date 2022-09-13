package com.gft.manager.batch.listeners;

import com.gft.manager.batch.BatchUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobId = jobExecution.getJobParameters().getString(BatchUtil.JOB_ID_KEY);
        String filePath = jobExecution.getJobParameters().getString(BatchUtil.EXCEL_PATH_KEY);
        Date createTime = jobExecution.getCreateTime();
        Date endTime = jobExecution.getEndTime();

        if (jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("==========JOB FINISHED=======");
            log.info("JobId      : {}",jobId);
            log.info("file Path      : {}",filePath);
            log.info("Start Date: {}", createTime);
            log.info("End Date: {}", endTime);
            log.info("==============================");
        }
    }
}
