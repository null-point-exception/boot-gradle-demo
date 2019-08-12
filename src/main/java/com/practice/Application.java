package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 程序主入口.
 *
 * @author kexin.ding
 */
@RestController
@RequestMapping
@SpringBootApplication
public class Application {

    @GetMapping
    void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
