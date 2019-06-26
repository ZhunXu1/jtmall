package com.jt.manage.dao;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.ItemCat;
import org.apache.ibatis.annotations.Select;

/**
 * @Date:2019/6/21 Description:com.jt.manage.dao
 */
public interface ItemCatDao extends SysMapper<ItemCat> {
    @Select("select name from  tb_item_cat where id=#{itemId}")
    public String queryItemName(Long itemId);
}
