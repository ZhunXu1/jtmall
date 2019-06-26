package com.jt.manage.service;
import com.jt.common.po.EasyUIResult;
import com.jt.common.po.Item;
import com.jt.manage.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @Date:2019/6/15 Description:com.jt.manage.service
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;

    @Override
    public EasyUIResult findItemByPage(int page, int rows) {
        int start ;

        start = (page-1)*rows;
        List<Item> itemByPage = itemDao.findItemByPage(start,rows);
        //获取rows
        int total = itemDao.selectCount(null);
        return new EasyUIResult(total,itemByPage);
    }


    @Override
    public String findItemCatById(Long itemId) {
        return itemDao.findItemCatById(itemId);
    }

    @Override
    public void saveItem(Item item) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemDao.insert(item);
    }
    @Override
    public void updateItem(Item item) {
        item.setStatus(1);
        item.setUpdated(new Date());
        itemDao.updateByPrimaryKeySelective(item);
    }

    @Override
    public void instock(Long[] ids) {
        int status = 2;
        itemDao.updateStatus(ids,status);
    }

    @Override
    public void reshelf(Long[] ids) {
        int status = 1;
        itemDao.updateStatus(ids,status);
    }
}
