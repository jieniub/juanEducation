package com.ljj.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljj.front.client.AdminClient;
import com.ljj.front.entity.Banner;
import com.ljj.front.service.BannerService;
import com.ljj.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.events.Namespace;
import java.util.List;

@RestController
@RequestMapping("/front")
@CrossOrigin()
public class BannerFrontController {

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private BannerService service;

    @Cacheable(value = "banner",key = "'list'")
    @GetMapping("/list")
    public R getList(){
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<Banner> list = service.list(queryWrapper);
        return R.ok().data("items",list);
    }


    @Cacheable(value = "index",key="'list'")
    @GetMapping("index")
    public R index(){
        List course = adminClient.course();
        return R.ok().data("courses",course);
    }
}
