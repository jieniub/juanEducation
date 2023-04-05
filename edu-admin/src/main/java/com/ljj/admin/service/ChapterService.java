package com.ljj.admin.service;

import com.ljj.admin.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.admin.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterList(String courseId);

    boolean removeChapterById(String id);

    boolean removeByCourseId(String id);


}
