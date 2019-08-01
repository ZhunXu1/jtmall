package com.jt.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Date:2019/7/1 Description:com.jt.common.po
 */
@Table(name="tb_item_desc")
public class ItemDec extends BasePojo {
    @Id
    private Long itemId;			//商品Id
    private String itemDesc;		//商品详情

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
