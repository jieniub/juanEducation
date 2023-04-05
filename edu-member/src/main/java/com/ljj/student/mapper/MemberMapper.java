package com.ljj.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljj.student.entitys.Member;

public interface MemberMapper extends BaseMapper<Member> {
    String getClassName(String memberId);
}
