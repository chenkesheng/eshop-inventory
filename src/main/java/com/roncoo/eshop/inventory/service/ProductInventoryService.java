package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.ProductInventory;

/**
 * 商品库存服务
 *
 * @Author: cks
 * @Date: Created by 13:37 2018/1/12
 * @Package: com.roncoo.eshop.inventory.service
 * @Description:
 */
public interface ProductInventoryService {
    /**
     * 更新库存
     *
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 删除redis缓存
     *
     * @param productInventory
     */
    void removeCache(ProductInventory productInventory);

    /**
     * 刷新redis缓存
     *
     * @param productInventory
     */
    void insertCache(ProductInventory productInventory);

    /**
     * 根据商品id查询库存
     *
     * @param productId
     * @return
     */
    ProductInventory findByProductId(Integer productId);

    /**
     * 获取商品库存
     *
     * @param productId
     * @return
     */
    ProductInventory getProductInventory(Integer productId);
}
