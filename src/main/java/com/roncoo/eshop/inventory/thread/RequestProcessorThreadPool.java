package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求处理线程池:单例
 *
 * @Author: cks
 * @Date: Created by 10:35 2018/1/12
 * @Package: com.roncoo.eshop.inventory.threadPool
 * @Description:
 */
public class RequestProcessorThreadPool {
    /**
     * 实际项目中你设置的线程池大小，是根据你的每个线程监控那个内存队列的大小是多少
     * 都做到一个外部的配置文件中
     * 这边先弄个简化版的，写死
     */
    //线程池
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    //内存队列
//    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    public RequestProcessorThreadPool() {
        RequestQueue requestQueue = RequestQueue.getInstance();
        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     * 绝对线程安全的单例
     * 静态内部类的方式:去初始化单例
     */
    private static class Singleton {
        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }
    }

    /**
     * JVM的机制来保证并发线程安全
     * 内部类的初始化，一定只会发生一次，不管多少个线程并发初始化
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化的便捷方法
     */
    public static void init() {
        getInstance();
    }
}
