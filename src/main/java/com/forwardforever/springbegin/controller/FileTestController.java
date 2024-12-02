package com.forwardforever.springbegin.controller;

import com.forwardforever.springbegin.domain.dto.FileOtherData;
import com.forwardforever.springbegin.domain.dto.Result;
import com.forwardforever.springbegin.service.FileTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileTestController {
    private final FileTestService fileTestService;

    @PostMapping(value = "/upload")
    public Result uploadFileTest(
            // 必须添加消息转换器才能将multipart/form-data转换为对象
            @RequestPart(value = "otherData", required = false) FileOtherData otherData,  // 使用 @RequestParam 处理非文件参数
            @RequestPart("files") MultipartFile[] files  // 使用 @RequestPart 处理文件
    ) {
        // 处理 non-file 参数 (如果有)
        System.out.println("Other Data: " + otherData);

        // 处理文件
        for (MultipartFile file : files) {
            System.out.println("Uploaded file: " + file.getOriginalFilename());
            // 保存文件
            try {
                Boolean f = fileTestService.saveFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return Result.success("上传成功");
    }
}

