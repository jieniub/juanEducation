package com.ljj.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CourseInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String teacherId;
    private String courseDescription;
    private String subjectId;
    private String subjectParentId;
    private String title;
    private Integer lessonNum;
    private String cover;

}
