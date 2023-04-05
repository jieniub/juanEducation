package com.ljj.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import com.aliyuncs.vod.model.v20170321.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljj.common.exception.SPException;
import com.ljj.common.utils.R;
import com.ljj.vod.service.VideoService;
import com.ljj.vod.utils.AliyunVodSDKUtils;
import com.ljj.vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.reflections.Reflections.log;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try{
            String name = file.getOriginalFilename();
            String title = name.substring(0,name.lastIndexOf("."));
            String accountId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String secret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest uploadStreamRequest = new UploadStreamRequest(accountId,secret,title,name,inputStream);
            UploadVideoImpl uploadVideo = new UploadVideoImpl();

            UploadStreamResponse uploadVideoResponse = uploadVideo.uploadStream(uploadStreamRequest);
            String videoId = uploadVideoResponse.getVideoId();

            if (!uploadVideoResponse.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" +
                        uploadVideoResponse.getCode() + ", message：" + uploadVideoResponse.getMessage();
                log.warn(errorMessage);
//                System.out.println("错误信息"+errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new SPException(20001, errorMessage);
                }
            }
            return videoId;

        }catch(IOException e){
            throw new SPException(20001,"vod上传服务失败");
        }

    }

    @Override
    public boolean removeById(String id) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(id);
            DeleteVideoResponse acsResponse = client.getAcsResponse(deleteVideoRequest);
            System.out.println("RequestId= "+ acsResponse.getRequestId());
        } catch (ClientException e) {
            throw new SPException(20001,"视频删除失败");
        }
        return true;
    }

    @Override
    public boolean removeByIds(List<String> ids) {
        String requestMsg = String.join(",",ids);
        System.out.println("-------" + requestMsg);
        if (requestMsg.length() != 0) return removeById(requestMsg);
        return true;
    }

    @Override
    public String getAuth(String id) {

        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                                        ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            GetVideoPlayAuthRequest authRequest = new GetVideoPlayAuthRequest();
            authRequest.setVideoId(id);


            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(authRequest);

            String playAuth = acsResponse.getPlayAuth();
            return playAuth;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
