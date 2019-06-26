package com.jt.manage.service;


import com.jt.common.po.EasyUIResult;
import com.jt.common.po.Item;

/**
 * @Date:2019/6/15 Description:com.jt.manage.service
 */
public interface ItemService {
    EasyUIResult findItemByPage(int page, int rows);
    String findItemCatById(Long itemId);

    void saveItem(Item item);

    void updateItem(Item item);

    void instock(Long[] ids);

    void reshelf(Long[] ids);
}
