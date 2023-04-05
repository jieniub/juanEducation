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
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String code = registerVo.getCode();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(password))
            throw new SPException(20001, "ERROR");

        String mobilecode = redisTemplate.opsForValue().get(mobile).toString();
        if (!code.equals(mobilecode))
            throw new SPException(20001, "error");

        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(wrapper);
        if (integer > 0) throw new SPException(20001, "ERROR");

        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setIsDeleted(0);
        member.setIsDisabled(0);
        member.setAvatar("test");
        member.setGmtCreate(new Date());
        member.setGmtModified(new Date());
        member.setId(mobile);
        System.out.println(member);
//        this.save(member);
        baseMapper.insert(member);

    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) throw new SPException(20001, "error");
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(wrapper);

        if (null == member){
            if (loginVo.isRole()){
                QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
                teacherWrapper.eq("id",mobile);
                Teacher teacher = teacherMapper.selectOne(teacherWrapper);
                if (null == teacher){
                    throw new SPException(20001, "error");
                }else{
                    if (!password.equals("123456")) throw new SPException(20001, "error");
                    else{
                        member = new Member();
                        member.setId(teacher.getId());
                        member.setAvatar(teacher.getAvatar());
                        member.setNickname(teacher.getName());
                        member.setRole(1);
                        member.setClassId("0");
                        member.setGmtCreate(new Date());
                        member.setGmtModified(new Date());
                        if(null == baseMapper.selectOne(new QueryWrapper<Member>().eq("id",teacher.getId()))){
                            System.out.println(member);
                            baseMapper.insert(member);
                        }

                    }
                }
            }else{
                throw new SPException(20001, "error");
            }

        }
        else {
            if (!MD5.encrypt(password).equals(member.getPassword())) throw new SPException(20001, "error");
            if (member.getIsDisabled() == 1) throw new SPException(20001, "error");
        }

        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public LoginInfo getLoginInfoVo(String memberId) {
        Member byId = this.getById(memberId);
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(byId, loginInfo);
        loginInfo.setClassName(memberMapper.getClassName(byId.getClassId()));
        return loginInfo;
    }
}
