package com.ljj.front.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Data
public class CommentVo {
    private String courseId;

    private String commentId;
    private String memberId;
    private String nickname;
    private String avatar;
    private String content;
    private Date gmtCreate;
    private List<CommentVo> children;
}
