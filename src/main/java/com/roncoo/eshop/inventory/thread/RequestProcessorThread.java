package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求线程
 *
 * @Author: cks
 * @Date: Created by 11:05 2018/1/12
 * @Package: com.roncoo.eshop.inventory.thread
 * @Description:
 */
public class RequestProcessorThread implements Callable<Boolean> {
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                //ArrayBlockingQueue
                //blocking就是说明队列满了，或者是空的，那么都会在执行操作的时候阻塞住
                Request request = queue.take();
                //执行这个request操作
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
