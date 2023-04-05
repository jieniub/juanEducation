package com.ljj.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.admin.entity.Banner;


import java.io.Serializable;

public interface BannerService extends IService<Banner> {

    @Override
    public IPage<Banner> page(IPage<Banner> page, Wrapper<Banner> queryWrapper);

    @Override
    public boolean save(Banner entity);

    @Override
    public boolean removeById(Serializable id);

    @Override
    public boolean updateById(Banner entity);
}
