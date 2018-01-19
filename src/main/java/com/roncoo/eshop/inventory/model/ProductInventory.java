package com.roncoo.eshop.inventory.model;

/**
 * 库存数量model
 *
 * @Author: cks
 * @Date: Created by 13:30 2018/1/12
 * @Package: com.roncoo.eshop.inventory.model
 * @Description:
 */
public class ProductInventory {
    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 库存
     */
    private Long inventoryCnt;

    public ProductInventory() {
    }

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
