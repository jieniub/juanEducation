package com.ljj.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.admin.entity.Teacher;
import com.ljj.admin.query.CourseQuery;
import com.ljj.admin.service.*;
import com.ljj.admin.vo.*;
import com.ljj.common.utils.R;
import com.ljj.admin.entity.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;
//    分页
    @PostMapping("/{page}/{limit}")
    public R getCourseList(@PathVariable("page") int page,
                           @PathVariable("limit") int limit){
        Page<Course> pageParam1 = new Page<>(page,limit);
        courseService.pageQuery(pageParam1,null);
        long total = pageParam1.getTotal();
        List<Course> records = pageParam1.getRecords();
        List<CourseVo> data = new ArrayList<>();
        for (int i = 0;i < records.size();i++){
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(records.get(i), courseVo);
            String teacherId = records.get(i).getTeacherId();
            Teacher byId = teacherService.getById(teacherId);
            courseVo.setTeacher(byId.getName());
            data.add(courseVo);
        }
        return R.ok().data("total",total).data("row",data);
    }

    @GetMapping("/courseInfo/{id}")
    public R getCourseById(@PathVariable String id){
        CourseInfoVo courseInfo = courseService.getCourseInfoById(id);
        return R.ok().data("item",courseInfo);
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        boolean b = courseService.removeById(id);
        return b ? R.ok() : R.error();
    }

    @PostMapping("/saveCourseInfo")
    public R saveCourseInfo(
//            @ApiParam("couseInfoForm",value = "课程基本信息")
            @RequestBody CourseInfoVo courseInfoVo
    ){
        System.out.println(courseInfoVo);
        String courseInfoId = courseService.saveCourseInfo(courseInfoVo);
        System.out.println(courseInfoId);
        if (!StringUtils.isEmpty(courseInfoId)){
            return R.ok().data("id",courseInfoId);
        }else{
            return R.error().message("保存失败");
        }
    }

    @PutMapping("/update")
    public R update(@RequestBody CourseInfoVo courseInfoVo){
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        course.setGmtModified(new Date());
        courseService.updateById(course);
        return R.ok();
    }

    @GetMapping("/{id}")
    public R getByID(@PathVariable String id){
        System.out.println(id);
        Course byId = courseService.getById(id);
        System.out.println("controller" + byId);
        CourseInfoVo courseInfoById = courseService.getCourseInfoById(id);
        return R.ok().data("item",courseInfoById);
    }

    @GetMapping("/publishinfo/{id}")
    public R getCoursePublishById(@PathVariable String id){
        CoursePublishVo data = courseService.getCoursePublishVoById(id);
        return R.ok().data("item",data);
    }

    @GetMapping("course")
    public List<Course> course(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("start_num").last("limit 8");
        return courseService.list(wrapper);

    }

    @Cacheable(key = "#id",value = "courseInfo")
    @GetMapping("/info/{id}")
    public R getCourseInfo(@PathVariable String id){
        CourseVo courseVo = courseService.getshowCourse(id);
        List<ChapterVo> chapterList = chapterService.getChapterList(id);
        return R.ok().data("course",courseVo).data("chapter",chapterList);
    }

    @PostMapping("/index/{page}/{limit}")
    public R getCourseIndex(@PathVariable long page,
                            @PathVariable long limit,
                            @RequestBody CourseQuery searchObj){
//        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        Page<Course> coursePage = new Page<Course>(page,limit);
        courseService.quertIndex(coursePage,searchObj);
        List<Course> records = coursePage.getRecords();
        long total = coursePage.getTotal();
        return R.ok().data("total",total).data("items",records);
    }

    @GetMapping("/list2")
    public R getNestedTreeList2(){
        List<SubjectNestedVo> nestList = subjectService.getNestList();
        return R.ok().data("items",nestList);
    }

    @GetMapping("/{page}/{limit}/{id}/{type}")
    public R  getListByStudent(@PathVariable Long page,
                               @PathVariable Long limit,
                               @PathVariable String id,
                               @PathVariable Integer type){
        return courseService.getCourseByStudent(page-1,limit,id,type);

    }


    @GetMapping("/{id}/{courseId}")
    public R addCourse(@PathVariable String id,
                       @PathVariable String courseId){
        boolean  b = courseService.addStudentCourse(id,courseId);
        return b ? R.ok():R.error();
    }
    @GetMapping("/teacher/{id}")
    public R teacherCourse(@PathVariable String id){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id).ne("status","审核中").ne("status","已驳回");
        List<Course> courses = courseService.list(wrapper);
        QueryWrapper<Course> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("teacher_id",id).eq("status","审核中").or().eq("status","已驳回");
        List<Course> list = courseService.list(wrapper1);
        return R.ok().data("items",courses).data("apply",list);

    }

    @GetMapping("/check/{id}/{courseId}")
    public boolean checkCourse(@PathVariable String id,
                       @PathVariable String courseId){
        return courseService.checkCourse(id,courseId);
    }

    @PutMapping("off/{id}")
    public R offCourse(@PathVariable String id){
        Course byId = courseService.getById(id);
        byId.setStatus("未发布");
        courseService.updateById(byId);
        return R.ok();
    }

    @GetMapping("/uncheck/{page}/{limit}")
    public R getCourseUncheck(@PathVariable Long page,
                              @PathVariable Long limit){
        Page<Course> pageParam1 = new Page<>(page,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("status","审核中");
        courseService.page(pageParam1,wrapper);
        long total = pageParam1.getTotal();
        List<Course> records = pageParam1.getRecords();
        List<CourseVo> data = new ArrayList<>();
        for (int i = 0;i < records.size();i++){
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(records.get(i), courseVo);
            String teacherId = records.get(i).getTeacherId();
            Teacher byId = teacherService.getById(teacherId);
            courseVo.setTeacher(byId.getName());
            data.add(courseVo);
        }
        return R.ok().data("total",total).data("row",data);
    }
    @PutMapping("/back/{courseId}")
    public R back(@PathVariable String courseId){
        Course byId = courseService.getById(courseId);
        byId.setStatus("被驳回");
        boolean b = courseService.updateById(byId);
        return b ? R.ok() : R.error();
    }
    @PutMapping("/pass/{courseId}")
    public R pass(@PathVariable String courseId){
        Course byId = courseService.getById(courseId);
        byId.setStatus("已发布");
        boolean b = courseService.updateById(byId);
        return b ? R.ok() : R.error();
    }
}

