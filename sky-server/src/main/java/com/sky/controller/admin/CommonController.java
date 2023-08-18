package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.S3FileUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags="通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private S3FileUploader s3FileUploader;


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);
        try {
            String originalFilename=file.getOriginalFilename();
            String extension=originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= UUID.randomUUID()+extension;
            String filePath=s3FileUploader.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return  Result.error("文件上传失败");
    }
}