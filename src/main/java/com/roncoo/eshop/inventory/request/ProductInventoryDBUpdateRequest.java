package com.roncoo.eshop.inventory.request;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;

/**
 * 商品发生交易，就要修改库存
 *
 * @Author: cks
 * @Date: Created by 13:14 2018/1/12
 * @Package: com.roncoo.eshop.inventory.request
 * @Description:cache asize pattern (1)删除缓存、（2）更新数据库
 */
public class ProductInventoryDBUpdateRequest implements Request {
    /**
     * 库存model
     */
    private ProductInventory productInventory;
    /**
     * 商品库存service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
                                           ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //删除redis缓存
        productInventoryService.removeCache(productInventory);
        //更新数据库
        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }


}
