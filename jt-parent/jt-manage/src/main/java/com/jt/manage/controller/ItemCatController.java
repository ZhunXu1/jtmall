package com.jt.manage.controller;

import com.jt.common.vo.EasyUITree;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date:2019/6/19 Description:com.jt.manage.controller
 */
@Controller
@RequestMapping("/item")
public class ItemCatController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemCatService itemCatService;




    /**
     * 根据商品分类id查询商品分类名称  iso-8859-1
     *
     * 使用@ResponseBody注解时,
     * 	  如果回传数据是String 则默认的解析规则ISO-8859-1
     * 	  如果回传的数据是对象 则采用UTF-8格式编码
     *
     * 原因:
     * 	public class StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

     public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

     public abstract class AbstractJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object>
     implements GenericHttpMessageConverter<Object> {
     public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
     * @param
     * @return
     */

    @RequestMapping(value="/cat/queryItemName",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String queryItemName(Long itemId){
        StringUtils
                .trimToEmpty("aaa");
        return itemCatService.queryItemName(itemId);
    }
    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EasyUITree> findItemCatById(@RequestParam(value="id",defaultValue="0") Long parentId){
        //查询一级商品分类目录
        //Long parentId = 0L;
        return itemCatService.findItemCatById(parentId);
    }


}


