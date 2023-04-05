package com.ljj.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Notice {
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String title;
    private String contant;
    private String cover;
    private Date date;

    private Boolean isShow;
    private Integer type;
    private Boolean status;
}
