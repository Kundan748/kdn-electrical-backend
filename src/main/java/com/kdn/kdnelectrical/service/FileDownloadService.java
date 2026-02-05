package com.kdn.kdnelectrical.service;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {

    public FileSystemResource getInvoiceFile(String path) {

        File file = new File(path);

        if (!file.exists()) {
            throw new RuntimeException("Invoice file not found");
        }

        return new FileSystemResource(file);
    }
}
