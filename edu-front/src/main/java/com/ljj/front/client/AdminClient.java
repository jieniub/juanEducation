package com.ljj.front.client;

import com.ljj.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("edu-admin")
@Component
public interface AdminClient {

    @GetMapping("/admin/course/course")
    List course();


    @GetMapping("admin/course/info/{id}")
    R getCourseInfo(@PathVariable String id);
}
