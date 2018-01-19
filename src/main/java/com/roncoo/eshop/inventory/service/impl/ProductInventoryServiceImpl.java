package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.dao.RedisDAO;
import com.roncoo.eshop.inventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品库存实现类
 *
 * @Author: cks
 * @Date: Created by 13:46 2018/1/12
 * @Package: com.roncoo.eshop.inventory.service.impl
 * @Description:
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    @Autowired
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    @Override
    public void removeCache(ProductInventory productInventory) {
        String key = "product:inventory" + productInventory.getProductId();
        redisDAO.delete(key);
    }


    @Override
    public void insertCache(ProductInventory productInventory) {
        String key = "product:inventory" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
    }

    @Override
    public ProductInventory findByProductId(Integer productId) {
        return productInventoryMapper.findByProductId(productId);
    }

    @Override
    public ProductInventory getProductInventory(Integer productId) {

        Long inventoryCnt = 0l;

        String key = "product:inventory" + productId;
        String result = redisDAO.get(key);
        if (!StringUtils.isEmpty(result)) {
            try {
                inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
