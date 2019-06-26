package com.jt.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date:2019/6/20 Description:com.jt.sso.controller
 */
@Controller
@ResponseBody
public class index {
    @RequestMapping("/index")
    public String index(){
        return "aaa";
    }
}
