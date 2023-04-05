package com.ljj.admin.vo;


import lombok.Data;

@Data
public class VideoVo {
    private String chapterId;
    private String id;
    private String title;
    private boolean isFree;
    private String videoOriginalName;
    private String videoSourceId;
}
