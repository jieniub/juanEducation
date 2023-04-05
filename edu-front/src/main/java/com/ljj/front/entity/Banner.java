package com.ljj.front.entity;

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
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
