package com.ljj.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("crm_banner")
public class Banner {
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String title;
    private String imageUrl;
    private String linkUrl;
    private Integer sort;
    private Integer isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}
