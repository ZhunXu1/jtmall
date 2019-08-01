package com.jt.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.web.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Date:2019/7/1 Description:com.jt.web.manage.service
 */
@Service
public class ItemService {
    @Autowired
    HttpClientService httpClient;
    public Item findItemById(Long itemId) {
        String url = "http://manage.jt.com/web/items/"+itemId;
        String result = httpClient.doGet(url);
        ObjectMapper objectMapper = new ObjectMapper();
        Item item = null;
        try {
            item = objectMapper.readValue(result,Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }


}
