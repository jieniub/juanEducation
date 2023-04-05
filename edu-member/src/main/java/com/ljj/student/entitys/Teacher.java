package com.ljj.student.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("edu_teacher")
public class Teacher {
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    private String phone;
    private String avatar;
    private Integer sort;
    @TableLogic
    private Integer isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}
