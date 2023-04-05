package com.ljj.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.front.entity.Comment;
import com.ljj.front.vo.CommentVo;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<CommentVo> getComments(String id);
}
