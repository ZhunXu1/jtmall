package com.jt.sso.dao;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Date:2019/7/8 Description:com.jt.sso.dao
 */
public interface UserMapper  extends SysMapper<User>{
    @Select("select count(*) from tb_user where ${cloumn} = #{param}")
    public int findcheckUser(@Param("cloumn")String cloumn,@Param("param")String param);
    @Select("select * from tb_user where username = #{username} and password = #{password}")
     User findUserByUP(User user);
}
