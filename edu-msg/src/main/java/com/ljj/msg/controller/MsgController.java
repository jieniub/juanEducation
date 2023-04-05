package com.ljj.msg.controller;

import com.ljj.common.utils.R;
import com.ljj.common.utils.RandomUtil;
import com.ljj.msg.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/msg")
@CrossOrigin
public class MsgController {

    @Autowired
    private MsgService msgService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsg(@PathVariable String phone){
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        HashMap<String, String> param = new HashMap<>();
        param.put("code",code);

        boolean flag = msgService.sendMsg(code,phone);
        if (flag){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error().message("短信发送失败");
        }
    }
}
