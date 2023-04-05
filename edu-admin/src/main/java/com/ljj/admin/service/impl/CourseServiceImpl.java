package com.ljj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.admin.entity.Course;
import com.ljj.admin.entity.Teacher;
import com.ljj.admin.mapper.CourseMapper;
import com.ljj.admin.query.CourseQuery;
import com.ljj.admin.service.ChapterService;
import com.ljj.admin.service.CourseService;
import com.ljj.admin.service.TeacherService;
import com.ljj.admin.service.VideoService;
import com.ljj.admin.vo.CourseInfoVo;
import com.ljj.admin.vo.CoursePublishVo;
import com.ljj.admin.vo.CourseVo;
import com.ljj.common.exception.SPException;
import com.ljj.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Autowired
    private VideoService videoService;
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return courseMapper.getCoursePublishVoById(id);
    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        course.setIsDeleted(0);
        course.setStartNum(0);
        int insert = baseMapper.insert(course);
        if (insert == 0){
            throw new SPException(20001,"课程信息保存失败");
        }

        return course.getId();
    }

    @Override
    public void pageQuery(Page<Course> pageParam, Course eduCourse) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("id");
        if (eduCourse == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }

    }

    @Override
    public CourseInfoVo getCourseInfoById(String id) {
        Course course = this.getById(id);

        if (course == null){
            throw new SPException(20001,"数据不存在");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);

        return courseInfoVo;
    }


    @Transactional
    @Override
    public boolean removeById(String id) {
        boolean b = chapterService.removeByCourseId(id);
        boolean a = videoService.removeByCourseId(id);
        Integer c = baseMapper.deleteById(id);
        return a && b && (null != c && c > 0);
    }

    @Override
    public boolean publish(String id) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Course normal = new Course().setStatus("审核中");
        boolean update = this.update(normal, wrapper);

        return update;
    }

    /**
     * 前端显示Course详细信息
     * @param id
     * @return
     */
    @Override
    public CourseVo getshowCourse(String id) {
        Course course = this.getById(id);
        Teacher byId = teacherService.getById(course.getTeacherId());
        if (course == null){
            throw new SPException(20001,"数据不存在");
        }
        CourseVo courseInfoVo = new CourseVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        courseInfoVo.setAvatar(byId.getAvatar());
        courseInfoVo.setTeacher(byId.getName());
        return courseInfoVo;
    }

    @Override
    public void quertIndex(Page<Course> pageParam, CourseQuery search) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        System.out.println(search);

        if (null != search.getStartNumSort() && search.getStartNumSort() == 1){
            wrapper.orderByDesc("start_num");
        }
        if (null != search.getGmtCreateSort() && search.getGmtCreateSort() == 1){
            wrapper.orderByAsc("gmt_create");
        }
        if(null != search.getSubjectParentId() && search.getSubjectParentId() != "" ){
            wrapper.eq("subject_parent_id",search.getSubjectParentId());
        }
        if (null != search.getSubjectParentId() && search.getSubjectId() != ""  ){
            wrapper.eq("subject_id",search.getSubjectId());
        }

        baseMapper.selectPage(pageParam,wrapper);
        return ;

    }

    @Override
    public R getCourseByStudent(Long page, Long limit, String id, Integer type) {
        page *= limit;
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        List<Course> records = null;
        int total = 0;
        if (type == 1){
//            courseMapper.selectPage(pageParam,wrapper);
            records = courseMapper.selectAllbyStudent(page,limit,id);
            total = courseMapper.selectCouseCount(id);
        }else if (type == 2){
            records = courseMapper.selectSelfbyStudent(page,limit,id);
            total = courseMapper.selectCouseselfCount(id);
        }else{
            records = courseMapper.selectClassbyStudent(page,limit,id);
            total = courseMapper.selectCouseClassCount(id);
        }
        return R.ok().data("total",total).data("items",records);
    }

    @Override
    public boolean addStudentCourse(String id, String courseId) {
        courseMapper.addStudentCourse(id,courseId);
        return true;
    }

    @Override
    public boolean checkCourse(String id, String courseId) {
        int i = courseMapper.checkCourse(id,courseId);
        return i == 0;
    }


}
