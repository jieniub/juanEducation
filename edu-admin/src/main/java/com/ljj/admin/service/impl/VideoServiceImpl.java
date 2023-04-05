package com.ljj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljj.admin.client.VodClient;
import com.ljj.admin.entity.Video;
import com.ljj.admin.mapper.VideoMapper;
import com.ljj.admin.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient client;

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<Video> videoqw = new QueryWrapper<>();
        videoqw.eq("chapter_id",id);
        Integer integer = baseMapper.selectCount(videoqw);
        return null != integer && integer > 0;
    }

    @Override
    public boolean removeVideoById(String id) {
        Video byId = this.getById(id);
        String videoSourceId = byId.getVideoSourceId();
        System.out.println("edu:-------------" + videoSourceId);
        if (!StringUtils.isEmpty(videoSourceId)){
            client.remove(videoSourceId);
        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public List<String> getByChapterId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",id);
        List<Video> videos = baseMapper.selectList(queryWrapper);
        List<String> ans = new ArrayList<>();
        for (Video item : videos){
            ans.add(item.getVideoSourceId());
        }
        return ans;
    }

    @Override
    public boolean removeByChapterId(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id",id);
        List<Video> list = baseMapper.selectList(queryWrapper);
        List<String> re = new ArrayList<>();
        for (Video tmp : list){
            re.add(tmp.getId());
        }
        client.removeByIds(re);
        Integer i = baseMapper.deleteBatchIds(re);
        return null != i && i > 0;
    }

    @Override
    public boolean removeByCourseId(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",id);
        List<Video> list = baseMapper.selectList(queryWrapper);
        List<String> re = list.stream().map(e->e.getVideoSourceId()).collect(Collectors.toList());
        List<String> de = list.stream().map(e->e.getId()).collect(Collectors.toList());
        client.removeByIds(re);
        if (de.size() != 0) baseMapper.deleteBatchIds(de);
        return true;
    }

}
