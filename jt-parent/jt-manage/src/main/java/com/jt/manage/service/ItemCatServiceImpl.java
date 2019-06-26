package com.jt.manage.service;
import com.jt.common.po.ItemCat;
import com.jt.common.vo.EasyUITree;
import com.jt.manage.dao.ItemCatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date:2019/6/21 Description:com.jt.manage.service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    ItemCatDao itemCatDao;

    @Override
    public List<EasyUITree> findItemCatById(Long parentId) {

        ItemCat itemCat = new ItemCat();
        List<EasyUITree> easyUITrees = new ArrayList<>();
        itemCat.setParentId(parentId);
        List<ItemCat> Intems = itemCatDao.select(itemCat);
        for (ItemCat Intem:Intems) {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(Intem.getId());
            easyUITree.setText(Intem.getName());
            easyUITree.setState(String.valueOf(Intem.getStatus()));
            //如果是父级则应该closed,如果不是父级 open
            String state =
                    Intem.getIsParent() ? "closed" : "open";
            easyUITree.setState(state);
            easyUITrees.add(easyUITree);
        }
        return easyUITrees;
    }
    @Override
    public String queryItemName(Long itemId) {

        String s = itemCatDao.queryItemName(itemId);
        return s;
    }
}
