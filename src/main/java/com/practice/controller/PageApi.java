package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器.
 *
 * @author kexin.ding
 */
@Controller
public class PageApi {

    @RequestMapping("vue-thymeleaf")
    public String vueThymeleaf(Model model) {
        model.addAttribute("title", "vue-thymeleaf页面");
        return "vue-thymeleaf";
    }
}
