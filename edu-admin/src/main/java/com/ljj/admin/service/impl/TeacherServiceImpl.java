package com.ljj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.admin.entity.Teacher;
import com.ljj.admin.mapper.TeacherMapper;
import com.ljj.admin.query.TeacherQuery;
import com.ljj.admin.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ljj
 * @since 2023-03-04
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("sort");
        if (teacherQuery == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("begin",begin);
        }if (!StringUtils.isEmpty(end)){
            queryWrapper.le("end",end);
        }
        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
