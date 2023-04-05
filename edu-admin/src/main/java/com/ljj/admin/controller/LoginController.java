package com.ljj.admin.controller;


import com.ljj.common.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {


    @PostMapping("/admin/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/admin/info")
    public R info(){
        return R.ok().data("role","admin").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
