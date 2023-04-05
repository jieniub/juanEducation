package com.ljj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljj.common.exception.SPException;
import com.ljj.admin.entity.Chapter;
import com.ljj.admin.entity.Video;
import com.ljj.admin.mapper.ChapterMapper;
import com.ljj.admin.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.admin.service.VideoService;
import com.ljj.admin.vo.ChapterVo;
import com.ljj.admin.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;


    @Override
    public List<ChapterVo> getChapterList(String courseId) {
        List<ChapterVo> chapterVos = new ArrayList<>();

        QueryWrapper<Chapter> chapterqw = new QueryWrapper<>();
        chapterqw.eq("course_id",courseId);
        chapterqw.orderByAsc("sort","course_id");
        List<Chapter> chapterList = this.list(chapterqw);

        QueryWrapper<Video> videoqw = new QueryWrapper<>();
        videoqw.eq("course_id",courseId);
        videoqw.orderByAsc("sort","course_id");
        List<Video> videoList = videoService.list(videoqw);

        int size = chapterList.size();
        for (int i = 0;i < size; i++){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapterList.get(i),chapterVo);
            chapterVos.add(chapterVo);

            int size2 = videoList.size();
            List<VideoVo> child = new ArrayList<>();

            for (int j = 0; j < size2; j++){
                if (chapterVo.getId().equals(videoList.get(j).getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(videoList.get(j),videoVo);
                    child.add(videoVo);
                }
            }
            chapterVo.setChildren(child);
        }
        return chapterVos;


    }

    @Override
    public boolean removeChapterById(String id) {
        if (videoService.getCountByChapterId(id)){
            if (videoService.removeByChapterId(id)){
                throw new SPException(20001,"视频删除失败");
            }
        }
        Integer i = baseMapper.deleteById(id);
        return null != i && i >0;
    }

    @Override
    public boolean removeByCourseId(String id) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        List<String> chapters = this.baseMapper
                .selectList(queryWrapper).stream().map(e-> e.getId())
                .collect(Collectors.toList());
        if (chapters.size()!= 0){
            Integer i = this.baseMapper.deleteBatchIds(chapters);
            return null != i && i > 0;
        }
        return true;
    }

}
