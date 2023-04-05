package com.ljj.admin.controller;


import com.ljj.common.utils.R;
import com.ljj.admin.entity.Video;
import com.ljj.admin.service.VideoService;
import com.ljj.admin.vo.VideoInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/save")
    public R save(@RequestBody VideoInfoVo videoInfoVo){

        Video eduVideo = new Video();
        BeanUtils.copyProperties(videoInfoVo,eduVideo);
        boolean save = videoService.save(eduVideo);
        return save ? R.ok() : R.error();
    }

    @PutMapping("/update")
    public R update(@RequestBody VideoInfoVo videoInfoVo){
        Video eduVideo = new Video();
        BeanUtils.copyProperties(videoInfoVo,eduVideo);
        boolean b = videoService.updateById(eduVideo);
        return b ? R.ok() : R.error();
    }

    @GetMapping("{id}")
    public R byId(@PathVariable String id){
        Video byId = videoService.getById(id);
        return R.ok().data("item",byId);
    }


    @DeleteMapping("{id}")
    public R remove(@PathVariable String id){
        boolean b = videoService.removeVideoById(id);
        return b ? R.ok() : R.error();
    }
}

