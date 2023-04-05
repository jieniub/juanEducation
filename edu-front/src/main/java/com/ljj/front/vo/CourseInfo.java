package com.ljj.front.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseInfo {
    private String title;
    private String courseDescription;
    private String cover;
    private Integer startNum;
    private Integer lessonNum;
    private Long viewCount;
    List<ChapterVo> children = new ArrayList<>();
}
