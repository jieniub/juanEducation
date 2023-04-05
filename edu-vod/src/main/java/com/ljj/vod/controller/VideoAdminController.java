package com.ljj.vod.controller;

import com.ljj.common.utils.R;

import com.ljj.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import javax.xml.ws.Service;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vod/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public R upload(@PathParam("file") MultipartFile file){
        String id = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", id);
    }
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        boolean b = videoService.removeById(id);
        return b ? R.ok().message("删除成功"):R.error().message("删除失败");
    }

    @DeleteMapping("/chapter")
    public boolean removeByIds(@RequestParam(value = "ids") List<String> ids){
        boolean flag  = videoService.removeByIds(ids);
//        return flag ? R.ok() : R.error();
        return flag;
    }


    @GetMapping("/auth/{id}")
    public R getAuth(@PathVariable String id){
        System.out.println(id);
        String auth = videoService.getAuth(id);
        return R.ok().data("auth",auth);
    }

}
