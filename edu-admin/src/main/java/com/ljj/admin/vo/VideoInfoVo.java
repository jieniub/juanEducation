package com.ljj.admin.vo;

import lombok.Data;

@Data
public class VideoInfoVo {
    private String courseId;
    private String chapterId;
    private String id;
    private String title;
    private String videoSourceId;
    private Integer sort;
    private boolean isFree;
    private String videoOriginalName;
}
