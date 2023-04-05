package com.ljj.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SPException extends RuntimeException{
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "SPException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}