package com.whl.tlias.controller;

import com.whl.tlias.pojo.Result;
import com.whl.tlias.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/1 11:58
 */
@RestController
public class UploadController {


    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    /*
     * 本地磁盘存储方案
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        //获取文件原始名
//        String originalFilename = file.getOriginalFilename();
//        //新的文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString()+extension;
//        //保存文件
//        file.transferTo(new File("D:\\JAVA学习项目\\后端\\SpringBootWeb案例\\tlias\\src\\main\\resources\\static\\img"+newFileName));
//        return Result.success();
//    }

    /*
     * 阿里云OSS存储方案
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}
