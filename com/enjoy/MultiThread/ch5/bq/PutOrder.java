package com.enjoy.MultiThread.ch5.bq;

import java.util.concurrent.DelayQueue;

//将订单放入队列
public class PutOrder implements Runnable{

    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //5秒到期
        Order orderTb = new Order("tb12345",999);
        ItemVo<Order> itemTb = new ItemVo<>(5000,orderTb);
        queue.offer(itemTb);
        System.out.println("订单5秒后到期" + orderTb.getOrderNo());
        //8秒到期
        Order orderJd = new Order("jd12345",888);
        ItemVo<Order> itemJd = new ItemVo<>(5000,orderJd);
        queue.offer(itemJd);
        System.out.println("订单5秒后到期" + orderJd.getOrderNo());
    }
}
