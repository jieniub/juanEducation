package com.ljj.admin.client;

import com.ljj.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "edu-vod",fallback = com.ljj.admin.client.hystrix.VodClientServiceBlow.class)
//@Component
public interface VodClient {
    @DeleteMapping("/vod/video/remove/{id}")
    public R remove(@PathVariable String id);

    @DeleteMapping("/vod/video/chapter")
    public boolean removeByIds(@RequestParam(value = "ids") List<String> ids);
}
