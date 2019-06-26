package com.jt.manage.dao;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Date:2019/6/15 Description:com.jt.manage.dao
 */

public interface ItemDao extends SysMapper<Item>{
    List<Item> findItemByPage(@Param("start") int start, @Param("rows") int rows);
   /* @Select("select count(*) from tb_item")
    int findItemCount();*/
   @Select("select name from tb_item_cat where id=#{id}")
    String findItemCatById(Long id);

   void updateStatus(
           @Param("ids") Long[] ids,
           @Param("status") int status);
/*  @Update("update tb_item set status = #{status} where id = #{ids}")
  void updateStatus( Long ids,int status)*/;
}
