package com.jt.web.sso;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.HttpClientService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Date:2019/7/27 Description:com.jt.web.sso
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    HttpClientService clientService;
    ObjectMapper objectMapper = new ObjectMapper();
    public String findUserByUP(User user){
        String url = "http://sso.jt.com/user/login";
        HashMap<String, String> params = new HashMap<>();
        String token = null;
        String md5Pass = DigestUtils.md5Hex(user.getPassword());;
        params.put("username", user.getUsername());
        params.put("password", md5Pass);
        String sysResultJSON = clientService.doPost(url,params);
        try {
            SysResult sysResult = objectMapper.readValue(sysResultJSON,SysResult.class);
            if(sysResult.getStatus() == 200){
                return (String)sysResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return token;
    }
   public void saveUser(User user){

       String url = "http://sso.jt.com/user/doRegister";
       HashMap<String, String> params = new HashMap<>();
       params.put("username", user.getUsername());
       params.put("password", user.getPassword());
       params.put("phone", user.getPhone());
       params.put("email", user.getEmail());
       String sysResultJSON  = clientService.doPost(url, params);
       //获取后台程序执行结果的返回信息 200/201
       try {
           SysResult sysResult = objectMapper.readValue(sysResultJSON,SysResult.class);
           if(sysResult.getStatus() != 200){
               throw new RuntimeException();
           }
       } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException();
       }
   }
}
