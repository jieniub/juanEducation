package com.ljj.student.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljj.common.exception.SPException;
import com.ljj.common.utils.JwtUtils;
import com.ljj.common.utils.MD5;
import com.ljj.student.entitys.Member;
import com.ljj.student.entitys.Teacher;
import com.ljj.student.mapper.MemberMapper;
import com.ljj.student.mapper.TeacherMapper;
import com.ljj.student.service.MemberService;
import com.ljj.student.service.TeacherService;
import com.ljj.student.vo.LoginInfo;
import com.ljj.student.vo.LoginVo;
import com.ljj.student.vo.RegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


}
