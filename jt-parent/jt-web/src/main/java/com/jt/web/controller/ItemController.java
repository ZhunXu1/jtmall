package com.jt.web.controller;

import com.jt.common.po.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date:2019/7/1 Description:com.jt.web.manage.controller
 */
@Controller
@RequestMapping("/items")
public class ItemController {

   @Autowired
    ItemService itemService;

   @RequestMapping("/{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){
        Item item = itemService.findItemById(itemId);
        model.addAttribute("item",item);

       /* ItemDesc itemDesc = itemService.findItemDescById(itemId);
        model.addAttribute("itemDesc", itemDesc);*/

        //跳转到商品展现页面
        return "item";
    }
}
