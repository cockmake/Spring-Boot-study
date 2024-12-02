package com.forwardforever.springbegin.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileTestService {
    Boolean saveFile(MultipartFile file) throws IOException;
}
