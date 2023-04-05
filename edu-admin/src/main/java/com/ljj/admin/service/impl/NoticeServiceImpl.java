package com.ljj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.admin.entity.Chapter;
import com.ljj.admin.entity.Notice;
import com.ljj.admin.entity.Video;
import com.ljj.admin.mapper.ChapterMapper;
import com.ljj.admin.mapper.NoticeMapper;
import com.ljj.admin.service.ChapterService;
import com.ljj.admin.service.NoticeService;
import com.ljj.admin.service.VideoService;
import com.ljj.admin.vo.ChapterVo;
import com.ljj.admin.vo.VideoVo;
import com.ljj.common.exception.SPException;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
