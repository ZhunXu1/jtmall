package com.jt.manage.service;

import com.jt.common.vo.EasyUITree;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date:2019/6/21 Description:com.jt.manage.service
 */
public interface ItemCatService {
    List<EasyUITree> findItemCatById(Long id);
    @Select("select name from tb_item_cat where id = #{itemId}")
    String queryItemName(Long itemId);
}
