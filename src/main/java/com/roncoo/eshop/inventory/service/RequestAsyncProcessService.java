package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.request.Request;

/**
 * 请求异步执行的service
 *
 * @Author: cks
 * @Date: Created by 14:25 2018/1/12
 * @Package: com.roncoo.eshop.inventory.service
 * @Description:
 */
public interface RequestAsyncProcessService {
    void process(Request request);
}
