package com.ljj.front.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("edu_comment")
public class Comment {
    @TableId(value = "comment_id",type = IdType.ID_WORKER_STR)
    private String commentId;
    private String courseId;
    private String memberId;
    private String nickname;
    private String avatar;
    private String content;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}
