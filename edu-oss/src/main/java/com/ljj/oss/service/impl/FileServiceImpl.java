package com.ljj.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.ljj.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${ali.oss.file.endpoint}")
    private String endpoint;
    @Value("${ali.oss.file.keyid}")
    private String keyId;
    @Value("${ali.oss.file.keysecret}")
    private String keySecret;
    @Value("${ali.oss.file.bucketname}")
    private String bucketName;
    @Value("${ali.oss.file.filehost}")
    private String fileHost;

    @Override
    public String upload(MultipartFile file) {
        String uploadUrl = null;

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint,keyId,keySecret);
            if (!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket("edu9605");
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            InputStream inputStream = file.getInputStream();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
            String filePath = ft.format(new Date());

            String original = file.getOriginalFilename();

            String newName = UUID.randomUUID().toString() + original.substring(original.lastIndexOf("."));

            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            ossClient.putObject(bucketName,fileUrl,inputStream);
            ossClient.shutdown();
            uploadUrl = "https://" + bucketName + "."  +endpoint + "/" + fileUrl;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return uploadUrl;

    }
}
