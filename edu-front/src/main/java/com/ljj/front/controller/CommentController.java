package com.ljj.front.controller;

import com.ljj.common.utils.R;
import com.ljj.front.entity.Comment;
import com.ljj.front.service.CommentService;
import com.ljj.front.service.impl.CommentServiceImpl;
import com.ljj.front.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/front/comment")
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("{id}")
    public R getCommentsByCourseId(@PathVariable String id){
        List<CommentVo> list =  commentService.getComments(id);

        return R.ok().data("list",list);
    }
    @PostMapping("save")
    public R saveComment(@RequestBody CommentVo comment){
        System.out.println(comment);
        Comment comment1 = new Comment();
        BeanUtils.copyProperties(comment,comment1);
        comment1.setGmtCreate(new Date());
        comment1.setGmtModified(new Date());
        boolean save = commentService.save(comment1);
        return save ? R.ok() : R.error();
    }
}
