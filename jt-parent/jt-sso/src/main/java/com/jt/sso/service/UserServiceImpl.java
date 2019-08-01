package com.jt.sso.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.sso.dao.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

/**
 * @Date:2019/7/8 Description:com.jt.sso.service
 */
@Service
public class UserServiceImpl implements UserService{
        @Autowired
        UserMapper userMapper;
        @Autowired
        ObjectMapper objectMapper;
        @Autowired
        private JedisCluster jedisCluster;
        public boolean findcheckUser(String parem,Integer type){
            String cloumn = null;
            switch (type){
                case 1:
                    cloumn = "username";
                break;
                case 2:
                    cloumn = "phone";
                    break;
                case 3:
                    cloumn = "email";
                    break;
            }
            if(!StringUtils.isEmpty(cloumn)){
                int count = userMapper.findcheckUser(cloumn,parem);
                return count == 1 ? true : false;
            }else{
                return false;
            }
        }

    @Override
    public boolean saveUser(User user) {
        String md5Pass = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Pass);
        user.setEmail(user.getPhone());//暂时代替
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        int i = userMapper.insert(user);
        boolean flag;
        if(i == 1){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }

    @Override
    public String findUserByUP(User user) {
           //查询数据库是否有该用户信息
        User userDb = userMapper.findUserByUP(user);
        if(userDb == null){
            throw new RuntimeException();
        }
        String token = DigestUtils.md5Hex("JT_TICKET" + System.currentTimeMillis() + user.getUsername());
        try {
            String userJSON = objectMapper.writeValueAsString(userDb);
            jedisCluster.setex(token,3600 * 24 * 7,userJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return token;
    }
}
