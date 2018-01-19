package com.roncoo.eshop.inventory.controller;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.roncoo.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import com.roncoo.eshop.inventory.service.RequestAsyncProcessService;
import com.roncoo.eshop.inventory.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cks
 * @Date: Created by 14:51 2018/1/12
 * @Package: com.roncoo.eshop.inventory.controller
 * @Description:
 */
@RestController
public class ProductInventoryController {


    @Autowired
    private ProductInventoryService productInventoryService;
    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    /**
     * 更新商品库存
     *
     * @param productInventory
     * @return
     */
    @RequestMapping(value = "updateProductInventory")
    public Response updateProductInventory(ProductInventory productInventory) {
        Response response = null;
        try {
            Request request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }

    /**
     * 更新商品库存
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "getProductInventory")
    public ProductInventory getProductInventory(Integer productId) {
        ProductInventory productInventory = null;
        try {
            Request request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService);
            requestAsyncProcessService.process(request);
            //将请求扔给service异步处理的时候，就需要while(true)一会儿，即轮询。在这里hang住
            //尝试等待前面商品库存更新的操作，同时缓存刷新的操作将最新的数据刷新到缓存中去
            long starTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;
            //等待超过200毫秒在缓存中没有获取到结果，就尝试去数据库读取数据
            while (true) {
                if (waitTime > 200) {
                    break;
                }
                //尝试去redis读取缓存
                productInventory = productInventoryService.getProductInventory(productId);
                //如果读取到结果就返回
                if (productInventory != null) {
                    return productInventory;
                }
                //如果没有读取到结果，那么就等待一段时间
                Thread.sleep(20);
                endTime = System.currentTimeMillis();
                waitTime = endTime - starTime;
            }
            //数据库读取数据
            productInventory = productInventoryService.findByProductId(productId);
            if (productInventory != null) {
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductInventory(productId, -1L);
    }
}
