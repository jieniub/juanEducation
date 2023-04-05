package com.ljj.student.vo;

import lombok.Data;

@Data
public class LoginVo {
    private String mobile;
    private String password;
    private boolean role;
}
