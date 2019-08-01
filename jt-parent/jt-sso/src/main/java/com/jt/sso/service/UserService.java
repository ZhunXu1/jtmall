package com.jt.sso.service;

import com.jt.common.po.User; /**
 * @Date:2019/7/8 Description:com.jt.sso.service
 */
public interface UserService {
    public boolean findcheckUser(String parem,Integer type);

    boolean saveUser(User user);

    String findUserByUP(User user);
}
