package com.ljj.oss.controller;

import com.ljj.common.utils.R;
import com.ljj.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin
@RequestMapping("/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R upload(
            @RequestParam("file")MultipartFile file){
        String uploadUrl = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url",uploadUrl);
    }
}
