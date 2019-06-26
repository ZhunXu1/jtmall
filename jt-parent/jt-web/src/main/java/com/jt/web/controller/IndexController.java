package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date:2019/6/15 Description:com.jt.manage.controller
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("aaaa");
        return "index";
    }
    @RequestMapping("/page/{modules}")
    public String item(@PathVariable String modules){
        return modules;
    }

}
