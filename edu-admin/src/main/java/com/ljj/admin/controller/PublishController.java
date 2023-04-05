package com.ljj.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljj.admin.entity.Course;
import com.ljj.common.utils.R;
import com.ljj.admin.service.CourseService;
import com.ljj.admin.vo.CoursePublishVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/admin/publish")
public class PublishController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/publishinfo/{id}")
    public R getCoursePublishById(@PathVariable String id){
        CoursePublishVo data = courseService.getCoursePublishVoById(id);
        return R.ok().data("item",data);
    }

    @PutMapping("{id}")
    public R publish(@PathVariable String id){
        boolean b = courseService.publish(id);
        return b ? R.ok():R.error();
    }

}
