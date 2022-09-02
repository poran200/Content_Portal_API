package com.gft.manager.service.impl;

import com.gft.manager.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final FileStorageService fileStorageService;
    private final JobLauncherService jobLauncherService;

    public Response uploadFile(MultipartFile file) throws JobInstanceAlreadyCompleteException, FileSystemException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Response response = fileStorageService.storeFile(file);
        if (response.getStatusCode() == 201){
            String filePath = (String) response.getContent();
            jobLauncherService.lunchGiftCardImportJob(filePath);
        }
        return response;

    }
}
