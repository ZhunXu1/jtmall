package com.jt.manage.web;

import com.jt.common.po.Item;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date:2019/6/26 Description:com.jt.manage.controller
 */
@Controller
@RequestMapping("/web")
public class WebItemController {
    @Autowired
    ItemService itemService;
    @ResponseBody
    @RequestMapping("/items/{itemId}")
    public Item findItemById(@PathVariable Long itemId){
        Item itemById = itemService.findItemById(itemId);
        return itemById;
    }
}
