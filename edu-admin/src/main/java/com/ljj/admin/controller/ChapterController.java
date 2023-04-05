package com.ljj.admin.controller;


import com.ljj.common.utils.R;
import com.ljj.admin.entity.Chapter;
import com.ljj.admin.service.ChapterService;
import com.ljj.admin.vo.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/nestedList/{courseId}")
    public R getChapterList(@PathVariable String courseId){

        List<ChapterVo> data =  chapterService.getChapterList(courseId);

        return R.ok().data("items",data);
    }

    @PostMapping("/save")
    public R saveChapter(@RequestBody Chapter eduChapter){
        boolean save = chapterService.save(eduChapter);
        return save ? R.ok() :R.error();
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id){
        Chapter byId = chapterService.getById(id);
        return R.ok().data("item",byId);
    }

    @PutMapping("{id}")
    public R Update(@PathVariable String id,
                    @RequestBody Chapter chapter){
        chapter.setId(id);
        boolean b = chapterService.updateById(chapter);
        return b?R.ok():R.error();
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        boolean success = chapterService.removeChapterById(id);
        return success ? R.ok(): R.error();

    }


}

