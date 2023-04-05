package com.ljj.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.common.utils.R;
import com.ljj.admin.entity.Teacher;
import com.ljj.admin.query.TeacherQuery;
import com.ljj.admin.service.TeacherService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2023-03-04
 */
@RestController
@CrossOrigin//跨域
@RequestMapping("/admin/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findAll")
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    @PostMapping("{page}/{limit}")
    public R pageList(@PathVariable("page") Long page,
                      @PathVariable("limit") Long limit,
                      @RequestBody(required = false) TeacherQuery teacher){
        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.pageQuery(pageParam,teacher);
        long total = pageParam.getTotal();
        List<Teacher> records = pageParam.getRecords();
        return R.ok().data("total",total).data("row",records);
    }

    @DeleteMapping("{id}")
    public R removeById(@PathVariable("id") String id){
        teacherService.removeById(id);
        return R.ok();
    }

    @PostMapping("/add")
    public R save(
        @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @GetMapping("{id}")
    public R getById(
            @PathVariable String id){
        Teacher byId = teacherService.getById(id);
        return R.ok().data("teacher",byId);
    }

    @PutMapping("{id}")
    public R updateById(
            @PathVariable String id,
            @RequestBody Teacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }


}

