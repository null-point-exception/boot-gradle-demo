package com.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 程序主入口.
 *
 * @author kexin.ding
 */
@RestController
@SpringBootApplication
@MapperScan("com.practice.dao*")
public class Application {

    @GetMapping("test")
    public String test() {
        return "This is a test.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
