package com.enjoy.MultiThread.ch8a;

import com.enjoy.MultiThread.ch5.bq.ItemVo;

import java.util.concurrent.DelayQueue;

/*
* 任务完成后,在一定的时间供查询，之后为释放资源节约内存，需要定期处理过期的任务
* */
public class CheckJobProcesser {

    private static DelayQueue<ItemVo<String>> queue = new DelayQueue<>();//存放已完成任务，超时过期

    //单例模式------
    private CheckJobProcesser(){}

    private static class ProcesserHolder{
        public static CheckJobProcesser processer = new CheckJobProcesser();
    }

    public static CheckJobProcesser getInstance(){
        return ProcesserHolder.processer;
    }
    //单例模式------

    //处理队列中到期任务的实行
    private static class FetchJob implements Runnable{

        @Override
        public void run() {
            while (true){
                try{
                    ItemVo<String> item = queue.take();
                    String jobName = (String)item.getDate();
                    PendingJobPool.getMap().remove(jobName);
                    System.out.println(jobName + "is out of date , remove from map");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /*任务完成后，放入队列，经过expireTime时间后，从整个框架中移除*/
    public void putJob(String jobName,long expireTime){
        ItemVo<String> item = new ItemVo<>(expireTime,jobName);
        queue.offer(item);
        System.out.println("job[" + jobName + "已经放入过期检查缓存，过期时长：" + expireTime);
    }

    //类初始化的时候就运行线程
    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        System.out.println("开启守护线程");
    }
}
