package com.jt.web.sso;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date:2019/7/2 Description:com.jt.sso.controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JedisCluster jedisCluster;
    //登出操作
    /*1、获取cookie
    * 2、清除redis缓存
    * 3、清除cookie
    * */
    @RequestMapping("/logout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if("JT_TICKET".equals(cookie.getName())){
                token =  cookie.getValue();
                break;
            }
        }
        if(!(StringUtils.isEmpty(token))){
            jedisCluster.del(token);
        }
        Cookie cookie = new Cookie("JT_TICKET", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index.html";
    }


    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult findUserByUP(User user,HttpServletResponse response) {
        try {
            String token = userService.findUserByUP(user);
            if (StringUtils.isEmpty(token)) {
                return SysResult.build(201, "用户名或密码错误");
            }
            Cookie cookie = new Cookie("JT_TICKET", token);
            cookie.setMaxAge(3600 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie); //将cookie写入浏览器
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户名或密码错误");
    }
    @RequestMapping("/{module}")
    public String module(@PathVariable String module) {
        return module;
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(User user) {
        try{
            userService.saveUser(user);

            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"用户新增失败");
    }
}
