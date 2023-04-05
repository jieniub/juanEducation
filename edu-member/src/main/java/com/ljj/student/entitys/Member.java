package com.ljj.student.entitys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("edu_member")
public class Member {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String classId;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private Integer isDisabled;
    private Integer isDeleted;
    private Integer sex;
    private Integer age;
    private String sign;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer role;//1 老师 0 学生
}
