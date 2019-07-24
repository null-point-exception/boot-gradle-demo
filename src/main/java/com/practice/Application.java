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
public class Application {

    @GetMapping
    public String test() {
        return "Success.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
