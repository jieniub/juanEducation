package com.ljj.admin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.ljj.common.exception.SPException;
import com.ljj.admin.entity.Subject;
import com.ljj.admin.entity.ExcelSubjectData;
import com.ljj.admin.service.SubjectService;

import java.util.Map;

public class SubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    public SubjectService subjectService;

    public SubjectListener(){}

    public SubjectListener(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData user, AnalysisContext analysisContext) {
        if (user == null){
            throw new SPException(20001,"添加失败");
        }
        Subject existOneSubject = this.existOneSubject(subjectService, user.getOneSubjectName());
        if (existOneSubject == null){//没有相同的
            existOneSubject = new Subject();
            existOneSubject.setTitle(user.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }
        String pid = existOneSubject.getId();

        Subject existTwoSubject = this.existTwoSubject(subjectService, user.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            existTwoSubject = new Subject();
            existTwoSubject.setTitle(user.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext analysisContext){
        System.out.println("表头信息: " + headMap);
    }


    private Subject existOneSubject(SubjectService subjectService, String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject subject = subjectService.getOne(wrapper);
        return subject;
    }

    private Subject existTwoSubject(SubjectService subjectService, String name, String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject subject = subjectService.getOne(wrapper);
        return subject;
    }
}
