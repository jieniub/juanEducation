package com.ljj.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljj.front.client.AdminClient;
import com.ljj.front.entity.Banner;
import com.ljj.front.service.BannerService;
import com.ljj.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/front/admin")
public class AdminController {
    @Autowired
    private BannerService bannerService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private AdminClient client;

    @Cacheable(value = "banner",key = "#page")
    @GetMapping("/{limit}/{page}")
    public R getPage(
            @PathVariable Integer limit,
            @PathVariable Integer page){
        Page<Banner> bannerPage = new Page<>(page,limit);
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        bannerService.page(bannerPage,queryWrapper);
        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());

    }

    @CacheEvict(value = "banner",allEntries = true)
    @PostMapping("/save")
    public R save(@RequestBody Banner banner){
        boolean save = bannerService.save(banner);
        return save?R.ok():R.error();

    }

    @CacheEvict(value = "banner",allEntries = true)
    @PutMapping("/update")
    public R update(@RequestBody Banner banner){
        System.out.println(banner);
        System.out.println("test");
        boolean b = bannerService.updateById(banner);
        return b?R.ok():R.error();
    }

    @CacheEvict(value = "banner",allEntries = true)
    @DeleteMapping("{id}")
    public R remove(@PathVariable String id){
        boolean b = bannerService.removeById(id);
        return b?R.ok():R.ok();
    }


    @Cacheable(value = "course",key = "#id")
    @GetMapping("/course/info/{id}")
    public R getInfo(@PathVariable String id){
        return client.getCourseInfo(id);
    }
}
