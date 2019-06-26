package com.jt.web.service;


import com.jt.common.po.EasyUIResult;

/**
 * @Date:2019/6/15 Description:com.jt.manage.service
 */
public interface ItemService {
    EasyUIResult findItemByPage(int page, int rows);
}
