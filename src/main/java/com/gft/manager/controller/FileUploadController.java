package com.gft.manager.controller;

import com.gft.manager.dto.Response;
import com.gft.manager.service.impl.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws JobInstanceAlreadyCompleteException, FileSystemException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        return fileUploadService.uploadFile(file);
    }
}
