package com.roncoo.eshop.inventory.request;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;

/**
 * 重新加载商品库存的数据加入到缓存
 *
 * @Author: cks
 * @Date: Created by 14:01 2018/1/12
 * @Package: com.roncoo.eshop.inventory.request
 * @Description:
 */
public class ProductInventoryCacheRefreshRequest implements Request{

    /**
     * 库存model
     */
    private Integer productId;
    /**
     * 商品库存service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService){
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //将最新的数据库商品库存数量拿到
        ProductInventory productInventory = productInventoryService.findByProductId(productId);
        //将数据库最新的数据刷新到redis缓存
        productInventoryService.insertCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
