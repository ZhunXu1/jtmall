package com.jt.sso.controller;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date:2019/7/8 Description:com.jt.sso.controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JedisCluster jedisCluster;


    //免登陆
    @RequestMapping("/query/{token}")
    @ResponseBody
    public MappingJacksonValue findUserByToken(@PathVariable String token,String callback){
        String userJSON = jedisCluster.get(token);
        MappingJacksonValue jacksonValue = null;
        if(StringUtils.isEmpty(userJSON)){
           jacksonValue = new MappingJacksonValue(SysResult.build(201,"获取token失败"));
        }else {
            jacksonValue = new MappingJacksonValue(SysResult.oK(userJSON));
        }
        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;
    }

    //输入密码登录
    @RequestMapping("/login")
    @ResponseBody
    public SysResult findUserByUP(User user){
        try {
            String token = userService.findUserByUP(user);
            if(StringUtils.isEmpty(token)){
               return  SysResult.build(201,"登录失败");
            }
            return SysResult.oK(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  SysResult.build(201,"登录失败");
    }
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue findCheckUser(@PathVariable String param,
                                             @PathVariable Integer type,String callback)
    {
        boolean flag = userService.findcheckUser(param,type);
        SysResult sysResult = SysResult.oK(flag);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(sysResult);
        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;

    }
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user,String callback){
        try {
            userService.saveUser(user);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201,"用户新增失败");
        }
    }

}
