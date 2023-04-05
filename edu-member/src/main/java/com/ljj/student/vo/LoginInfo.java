package com.ljj.student.vo;

import lombok.Data;

@Data
public class LoginInfo {
    private String id;
    private String mobile;
    private String nickname;
    private Integer sex;
    private Integer age;
    private String avatar;
    private String className;
    private Integer role;//1 老师 0 学生
}
