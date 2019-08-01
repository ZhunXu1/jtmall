package com.jt.manage.controller;

import com.jt.common.po.EasyUIResult;
import com.jt.common.po.Item;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date:2019/6/15 Description:com.jt.manage.controller
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;



    @ResponseBody
    @RequestMapping("/query")
    public EasyUIResult ListItem(int page, int rows) {
        return itemService.findItemByPage(page, rows);
    }

    //实现商品新增
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item) {
        try {
            itemService.saveItem(item);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品新增失败");
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item) {
        try {
            itemService.updateItem(item);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "商品修改失败");
        }
    }
    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelf(Long[] ids) {

        try {
            itemService.reshelf(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "上下架修改失败");
        }
    }

    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instock(Long[] ids) {

        try {
            itemService.instock(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "上下架修改失败");
        }
    }
}
