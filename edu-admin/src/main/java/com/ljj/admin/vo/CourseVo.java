package com.ljj.admin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseVo {
    private String id;
    private String cover;
    private String title;
    private String teacher;
    private String courseDescription;
    private Integer lessonNum;
    private Integer startNum;
    private String status;
    private Date gmtCreate;
    private String avatar;
}
