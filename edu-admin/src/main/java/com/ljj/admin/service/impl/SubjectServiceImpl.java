package com.ljj.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.common.exception.SPException;
import com.ljj.admin.entity.Subject;
import com.ljj.admin.entity.ExcelSubjectData;
import com.ljj.admin.listener.SubjectListener;
import com.ljj.admin.mapper.SubjectMapper;
import com.ljj.admin.service.SubjectService;
import com.ljj.admin.vo.SubjectNestedVo;
import com.ljj.admin.vo.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
//
//    @Override
//    public void batchImport() {
//
//    }

    @Override
    public void SaveSubject(MultipartFile file,SubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream,ExcelSubjectData.class,new SubjectListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
            throw new SPException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<SubjectNestedVo> getNestList() {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        queryWrapper.orderByAsc("sort","id");
        List<Subject> eduSubjects = baseMapper.selectList(queryWrapper);//第一层

        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<Subject> subSubjects = baseMapper.selectList(queryWrapper2);

        int count = eduSubjects.size();
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Subject subject = eduSubjects.get(i);
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                Subject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }
        return subjectNestedVoArrayList;

    }


//    @Override
//    public void importSubjectData(MultipartFile file, SubjectService subject) {
//        try {
//            InputStream inputStream = file.getInputStream();
//            EasyExcel.read(inputStream, ExcelSubjectData.class)
//        }
//    }
}
