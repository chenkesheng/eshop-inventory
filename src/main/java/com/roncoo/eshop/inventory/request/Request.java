package com.roncoo.eshop.inventory.request;

/**
 * 请求接口
 *
 * @Author: cks
 * @Date: Created by 10:58 2018/1/12
 * @Package: com.roncoo.eshop.inventory.request
 * @Description:
 */
public interface Request {
    void process();
    Integer getProductId();
}
