package com.ljj.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.admin.entity.Banner;
import com.ljj.admin.mapper.BannerMapper;
import com.ljj.admin.service.BannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {


    @Override
    public IPage<Banner> page(IPage<Banner> page, Wrapper<Banner> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    @CacheEvict(value = "banner",allEntries = true)
    @Override
    public boolean save(Banner entity) {
        return super.save(entity);
    }
    @CacheEvict(value = "banner",allEntries = true)
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
    @CacheEvict(value = "banner",allEntries = true)
    @Override
    public boolean updateById(Banner entity) {
        return super.updateById(entity);
    }
}
