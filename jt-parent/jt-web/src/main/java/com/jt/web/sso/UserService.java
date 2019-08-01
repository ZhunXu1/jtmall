package com.jt.web.sso;

import com.jt.common.po.User;

/**
 * @Date:2019/7/27 Description:com.jt.web.sso
 */
public interface UserService {
    public void saveUser(User user);
    public String findUserByUP(User user);
}
