package com.ljj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.front.entity.Comment;
import com.ljj.front.mapper.CommentMapper;
import com.ljj.front.service.CommentService;
import com.ljj.front.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public List<CommentVo> getComments(String id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.eq("parent_id","");
        List<CommentVo> comments = baseMapper.selectList(wrapper).stream().map(e ->{
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(e,commentVo);
            return commentVo;
        }).collect(Collectors.toList());

        for (CommentVo commentVo : comments){
            QueryWrapper<Comment> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("parent_id",commentVo.getCommentId());
            List<CommentVo> comments1 = baseMapper.selectList(wrapper1).stream().map(e->{
                CommentVo commentVo1 = new CommentVo();
                BeanUtils.copyProperties(e,commentVo1);
                return commentVo1;
            }).collect(Collectors.toList());
            commentVo.setChildren(comments1);
        }
        return comments;
    }
}
