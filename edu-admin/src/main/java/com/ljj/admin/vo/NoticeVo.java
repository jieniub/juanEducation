package com.ljj.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVo {
    private String id;
    private String title;
    private String date;
    private Integer type;
}
