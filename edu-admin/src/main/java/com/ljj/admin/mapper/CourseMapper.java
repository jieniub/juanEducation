package com.ljj.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.admin.entity.Course;
import com.ljj.admin.vo.CoursePublishVo;

import java.util.List;


public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo getCoursePublishVoById(String id);

    List<Course> selectAllbyStudent(Long page,Long limit, String id);

    List<Course> selectSelfbyStudent(Long page,Long limit, String id);

    List<Course> selectClassbyStudent(Long page,Long limit, String id);

    int selectCouseCount(String id);

    int selectCouseselfCount(String id);

    int selectCouseClassCount(String id );

    void addStudentCourse(String id, String courseId);

    int checkCourse(String id,String courseId);
}
