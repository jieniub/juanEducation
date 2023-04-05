package com.ljj.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.admin.entity.Banner;
import com.ljj.admin.entity.Chapter;
import com.ljj.admin.entity.Notice;
import com.ljj.admin.service.BannerService;
import com.ljj.admin.service.ChapterService;
import com.ljj.admin.service.NoticeService;
import com.ljj.admin.vo.ChapterVo;
import com.ljj.admin.vo.NoticeVo;
import com.ljj.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/admin/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    BannerService bannerService;

    @GetMapping("/{page}/{limit}/{type}")
    public R getNoticePage(@PathVariable Long page,
                           @PathVariable Long limit,
                           @PathVariable Integer type){
        Page<Notice> noticePage = new Page<>(page,limit);
        QueryWrapper<Notice> type1 = new QueryWrapper<Notice>();
        if (type != 9){
            type1.eq("type", type);
        }
        type1.orderByDesc("date");
        noticeService.page(noticePage,type1);
        List<NoticeVo> records = noticePage.getRecords().stream().map(e ->{
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setId(e.getId());
            noticeVo.setTitle(e.getTitle());
            noticeVo.setType(e.getType());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            noticeVo.setDate(dateFormat.format(e.getDate()));
            return noticeVo;
        }).collect(Collectors.toList());
        long total = noticePage.getTotal();
        return R.ok().data("items",records).data("total",total);
    }

    @GetMapping("/{page}/{limit}")
    public R getAdminNoticePage(@PathVariable Long page,
                           @PathVariable Long limit){
        Page<Notice> noticePage = new Page<>(page,limit);
        QueryWrapper<Notice> type1 = new QueryWrapper<Notice>();
        type1.orderByDesc("date");
        noticeService.page(noticePage,type1);
        List<Notice> records = noticePage.getRecords();
        long total = noticePage.getTotal();
        return R.ok().data("items",records).data("total",total);
    }
    @GetMapping("/info/{id}")
    public R getInfo(@PathVariable String id){
        Notice byId = noticeService.getById(id);
        return R.ok().data("info",byId);
    }

    @PostMapping("/add")
    public R addNotice(@RequestBody Notice notice){
        notice.setDate(new Date());
        System.out.println(notice);
        boolean save = noticeService.save(notice);
        return save ? R.ok() : R.error();
    }
    @PostMapping("/publish")
    public R publishNotice(@RequestBody Notice notice){
        if (notice.getCover() != null && notice.getIsShow()){
            Banner banner = new Banner();
            banner.setTitle(notice.getTitle());
            banner.setImageUrl(notice.getCover());
            banner.setGmtCreate(new Date());
            banner.setGmtModified(new Date());
            bannerService.save(banner);
        }
        notice.setDate(new Date());
        notice.setStatus(true);
        boolean save = noticeService.save(notice);
        return save ? R.ok() : R.error();
    }
    @PostMapping("/publish/{id}")
    public R publishNotice(@PathVariable String id){
        Notice byId = noticeService.getById(id);
        if (byId.getCover() != null && byId.getIsShow()){
            Banner banner = new Banner();
            banner.setTitle(byId.getTitle());
            banner.setImageUrl(byId.getCover());
            banner.setGmtCreate(new Date());
            banner.setGmtModified(new Date());
            bannerService.save(banner);
        }
        byId.setStatus(true);
        boolean save = noticeService.save(byId);
        return save ? R.ok() : R.error();
    }
}

