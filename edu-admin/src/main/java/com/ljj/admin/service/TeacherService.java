package com.ljj.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.admin.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.admin.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ljj
 * @since 2023-03-04
 */
public interface TeacherService extends IService<Teacher> {
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);

}
