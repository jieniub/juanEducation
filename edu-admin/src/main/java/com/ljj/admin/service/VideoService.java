package com.ljj.admin.service;

import com.ljj.admin.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String id);

    boolean removeVideoById(String id);

    List<String> getByChapterId(String id);

    boolean removeByChapterId(String id);

    boolean removeByCourseId(String id);
}
