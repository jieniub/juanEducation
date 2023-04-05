package com.ljj.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.ljj.student.mapper")
@EnableDiscoveryClient
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}
