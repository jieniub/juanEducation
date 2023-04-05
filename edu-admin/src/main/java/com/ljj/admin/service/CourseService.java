package com.ljj.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.admin.entity.Course;
import com.ljj.admin.query.CourseQuery;
import com.ljj.admin.vo.CourseInfoVo;
import com.ljj.admin.vo.CoursePublishVo;
import com.ljj.admin.vo.CourseVo;
import com.ljj.common.utils.R;

public interface CourseService extends IService<Course> {

    CoursePublishVo getCoursePublishVoById(String id);

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    void pageQuery(Page<Course> pageParam, Course course);

    CourseInfoVo getCourseInfoById(String id);

    boolean removeById(String id);

    boolean publish(String id);

    CourseVo getshowCourse(String id);

    void quertIndex(Page<Course> pageParam, CourseQuery search);

    R getCourseByStudent(Long page, Long limit, String id, Integer type);

    boolean addStudentCourse(String id, String courseId);

    boolean checkCourse(String id, String courseId);
}
