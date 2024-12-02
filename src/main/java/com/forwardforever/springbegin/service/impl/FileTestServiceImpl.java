package com.forwardforever.springbegin.service.impl;

import com.forwardforever.springbegin.service.FileTestService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@Data
@ConfigurationProperties(prefix = "host")
public class FileTestServiceImpl implements FileTestService {
    private String FILE_SAVE_ROOT_PATH;
    @Override
    public Boolean saveFile(MultipartFile file) throws IOException {
        System.out.println(FILE_SAVE_ROOT_PATH);
        InputStream inputStream = file.getInputStream();
        // 保存文件
        File targetFile = new File(FILE_SAVE_ROOT_PATH + file.getOriginalFilename());
        file.transferTo(targetFile);
        return true;
    }
}
