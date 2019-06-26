package com.jt.web.controller;

import com.jt.common.po.EasyUIResult;
import com.jt.web.service.ItemService;
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
    public EasyUIResult ListItem(int page, int rows){

        return itemService.findItemByPage(page,rows);
    }

}
