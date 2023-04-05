package com.ljj.student.controller;


import com.ljj.common.exception.SPException;
import com.ljj.common.utils.JwtUtils;
import com.ljj.common.utils.R;
import com.ljj.student.service.MemberService;
import com.ljj.student.vo.LoginInfo;
import com.ljj.student.vo.LoginVo;
import com.ljj.student.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/ucenter")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }

    @PostMapping("/regist")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    @GetMapping("/getLoginInfo")
    public R getLoginnfo(HttpServletRequest request){
        try{
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfo loginVo =  memberService.getLoginInfoVo(memberId);

            return R.ok().data("item",loginVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new SPException(20001,"error");
        }
    }
}
