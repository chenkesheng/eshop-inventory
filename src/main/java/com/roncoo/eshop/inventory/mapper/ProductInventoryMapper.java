package com.roncoo.eshop.inventory.mapper;

import com.roncoo.eshop.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * 库存数量mapper
 *
 * @Author: cks
 * @Date: Created by 13:24 2018/1/12
 * @Package: com.roncoo.eshop.inventory.dao
 * @Description:
 */
public interface ProductInventoryMapper {
    /**
     * 更新库存数量
     */
    void updateProductInventory(ProductInventory productInventory);

    ProductInventory findByProductId(@Param("productId") Integer productId);
}
