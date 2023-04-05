package com.ljj.admin.controller;


import com.ljj.common.utils.R;
import com.ljj.admin.service.SubjectService;
import com.ljj.admin.vo.SubjectNestedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin
@RequestMapping("/admin/subject")
@RestController
public class SubjectAdminController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addSubject")
    public R addSubject(@RequestBody MultipartFile file){


        System.out.println(file);
        subjectService.SaveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("/getSubject")
    public R getSubjectById(){
        System.out.println("请求成功");
        List<SubjectNestedVo> list = subjectService.getNestList();
        System.out.println(list);
        return R.ok().data("items",list);
    }
}
